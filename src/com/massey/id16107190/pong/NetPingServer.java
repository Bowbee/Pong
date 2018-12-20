package com.massey.id16107190.pong;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class NetPingServer extends Thread {
	
	private NetworkSystem ns = NetworkSystem.getInstance();
	
	public NetPingServer(){
		
	}
	
	public void run(){
		try{
	        ServerSocket listener = new ServerSocket(ns.getPort());
	        try {
	        	System.out.println("Running Server!");
	            while (ns.isConnected() == false) {
	                Socket socket = listener.accept();
	                try{
	                	ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
	                	out.writeBoolean(true);
	                	out.flush();
	                	System.out.println("bool sent");

	                	
	                	ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
	                	
	                    Boolean response = null;
	        			try {
	        				response = (Boolean) input.readBoolean();
	        			} catch (ClassCastException e){
	        				
	        			}
	        			
	        			if(response != null){
	        				if(response == true){
	        					System.out.println("Client has connected to server!");
	        					ns.setConnected(true);
	        					socket.close();
	        				}
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

}
