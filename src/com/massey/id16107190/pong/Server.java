package com.massey.id16107190.pong;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;


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
	        	System.out.println("Running Server! 1");
	            while (true) {
	            	
	                Socket socket = listener.accept();
	                try{
	                	ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
	                	out.flush();
	                	out.writeObject(np);
	                	
	                	ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

	                    ClientPacket cPacket = null;
	        			try {
	        				cPacket = (ClientPacket) input.readObject();
	        			} catch (ClassNotFoundException e) {
	        				// TODO Auto-generated catch block
	        				e.printStackTrace();
	        			} catch (ClassCastException e){
	        				
	        			} catch (SocketException e){
	        				gs.setOnline(false);
	        				System.out.println("Client Disconnected");
	        				//gs.setP2IsAI(true);
	        				gs.setPlayer2Paddle(new Box(-1000, -1000, 0, 0));
	        			}
	        			
	        			if(cPacket != null){
	        				gs.setP2Color(cPacket.p2Color);
	        	            gs.setPlayer2Paddle(cPacket.paddle2);
	        			}
	        			else{
	        				// No data
	        			}

	                }
	                catch (SocketException e){
        				gs.setOnline(false);
        				System.out.println("Client Disconnected");
        				//gs.setP2IsAI(true);
        				gs.setPlayer2Paddle(new Box(-1000, -1000, 0, 0));
        			}
	                finally {
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

