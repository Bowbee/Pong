package com.massey.id16107190.pong;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JOptionPane;

/**
 * Trivial client for the date server.
 */
public class Client extends Thread {
	
	private GameState gs = GameState.getInstance();
	private String serverAddress;
	private int socketNum;
	private NetPacket np;
	private boolean ready = true;
	private boolean readySent = false;

    public Client(String hostIP, int port) {
		serverAddress = hostIP;
		socketNum = port;
	}
    public void run() {
    	try{
	        Socket socket = new Socket(serverAddress, socketNum);
        	ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            NetPacket answer = null;
			try {
				answer = (NetPacket) input.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(answer != null){
	            np = answer;
	            gs.updateNP(np);
			}
			else{
				// No data
			}
			
			try{
            	ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            	
            	if(ready == true && readySent == false){
            		readySent = true;
            		out.writeObject(ready);
            	}
            	else{
            		out.writeObject(gs.GetPlayer2Paddle());
            	}
            	

            } finally {
                socket.close();
            }
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			run();

            
    	} catch (SocketException e){
    		gs.setNetReady(0);
    	} catch (IOException e){
    		e.printStackTrace();
    	} 
    }
	public synchronized void setReady(boolean ready) {
		this.ready = ready;
	}
}
