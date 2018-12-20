package com.massey.id16107190.pong;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;

public class NetPingClient extends Thread{
	
	private NetworkSystem ns = NetworkSystem.getInstance();
	
	public NetPingClient(){
	}
	
	public void run(){
		System.out.println("Client2");
		try{
			if(ns.isConnected() == false){
				Socket socket = new Socket(ns.getHostIP(), ns.getPort());
	        	ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
	        	System.out.println(socket.getInetAddress());
	            Boolean cResponse = null;
	            cResponse = (Boolean) input.readBoolean();
	            System.out.println(cResponse);
				
				if(cResponse != null){
		            if(cResponse == true){
		            	System.out.println("Server accepted client!");
		            }
				}
				try{
	            	ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
	            	out.flush();
	        		out.writeBoolean(true);
	        		System.out.println("Notifying server that client connected");
	        		out.flush();
	        		ns.setConnected(true);
	        		socket.close();
	            } finally {
	                socket.close();
	            }
			}
	        
            
    	}
		catch (ConnectException e){
			System.out.println("ConnectException");
    		try {
				Thread.sleep(500);
				run();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		catch (EOFException e){
			System.out.println("Game already started?");
			e.printStackTrace();
		}
		catch (SocketException e){
			System.out.println("Game already started?");
    		e.printStackTrace();
    	} 
		catch (IOException e){
			System.out.println("Game already started?");
    		e.printStackTrace();
    	}
		
	}
}
