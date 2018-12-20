package com.massey.id16107190.pong;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;


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
    		while(gs.isOnline() == true){
	        Socket socket = new Socket(serverAddress, socketNum);
        	ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        	
        		NetPacket answer = null;
    			try {
    				answer = (NetPacket) input.readObject();
    			} catch (ClassNotFoundException e) {
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
                	out.flush();
                	
                	if(ready == true && readySent == false){
                		readySent = true;
                		out.writeObject(ready);
                	}
                	else{
                		out.writeObject(new ClientPacket(gs.GetPlayer2Paddle(), gs.getP2Color()));
                	}
                	

                } finally {
                    socket.close();
                }
    			/*
    			try {
    				Thread.sleep(7);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
    			*/
        		
        	}

            
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
