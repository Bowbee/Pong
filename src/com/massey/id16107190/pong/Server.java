package com.massey.id16107190.pong;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * A TCP server that runs on port 9090.  When a client connects, it
 * sends the client the current date and time, then closes the
 * connection with that client.  Arguably just about the simplest
 * server you can write.
 */
public class Server extends Thread {
	
	public int port;
	private NetPacket np = null;
	private boolean changed = false;
	private GameState gs = GameState.getInstance();
	private NetworkSystem ns = NetworkSystem.getInstance();

    public Server(int p) {
		port = p;
	}

	/**
     * Runs the server.
     */
    public void run() {
    	try{
	        ServerSocket listener = new ServerSocket(port);
	        try {
	        	System.out.println("Running Server!");
	            while (true) {
	            	
	                Socket socket = listener.accept();
	                try{
	                	ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
	                	out.writeObject(np);
	                	
	                	ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

	                    Box paddle = null;
	        			try {
	        				paddle = (Box) input.readObject();
	        			} catch (ClassNotFoundException e) {
	        				// TODO Auto-generated catch block
	        				e.printStackTrace();
	        			} catch (ClassCastException e){
	        				
	        			}
	        			
	        			if(paddle != null){
	        	            gs.setPlayer2Paddle(paddle);
	        			}
	        			else{
	        				// No data
	        			}

	                } finally {
	                    socket.close();
	                }
	            }
	        }
	        finally {
	            listener.close();
	        }
    	} catch (IOException e){
    		e.printStackTrace();
    	}
    }
    public synchronized void updatePacket(NetPacket pack){
    	np = pack;
    	changed = true;
    }
}

