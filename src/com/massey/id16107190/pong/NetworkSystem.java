package com.massey.id16107190.pong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public final class NetworkSystem {
	 
    private static NetworkSystem INSTANCE;
    
    private String hostIP = "127.0.0.1";
    private String clientIP = "";
    private int port = 23;
    private int playerIndex = 0;
    
    private boolean connected = false;
    private boolean host = false;
    private boolean client = false;
    
    private boolean ready = false;
    private boolean otherReady = true;
    
    private Thread tr;
   
	
     
    private NetworkSystem() {  
    }
     
    public static NetworkSystem getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new NetworkSystem();
        }
         
        return INSTANCE;
    }
    
    public void Connect(){
    	if(client){
    		runClient();
    	}
    	if(host){
    		runServer();
    	}
    }
    
    public void runClient(){
    	if(client){
    		try{
    			tr = new Client(hostIP, port);
    			tr.start();
    		}
    		catch (Exception e) {
    			e.printStackTrace();
			}
    	}
    }
    public void runServer(){
    	if(host){
    		try{
				tr = new Server(port);
				tr.start();
			}
    		catch (Exception e) {
    			e.printStackTrace();
			}	
    	}
    }
	public Box sendData(Box p1, Box p2, Ball ball) {
		//System.out.println("Sending Data");
		
		if(playerIndex == 1){
			return p2;
		}
		return p1;
		
		
	}

	public void sendNetPacket(NetPacket np) {	
		Server sv = (Server) tr;
		sv.updatePacket(np);
	}
    
    
	public void sendClose() {
		// send a close signal to the other player
		if(host){
			//send to client "Host left"
		}
		if(client){
			//send to host "Client Left"
		}
		
	}
	private void sendReady() {
		// TODO Auto-generated method stub
		
	}

	public boolean isHost() {
		return host;
	}

	public void setHost(boolean host) {
		this.host = host;
		this.client = !host;
	}

	public boolean isClient() {
		return client;
	}

	public void setClient(boolean client) {
		this.client = client;
		this.host = !client;
	}

	public boolean isReady() { // if client+host are ready
		if(ready && otherReady == true){
			return true;
		}
		else{
			return false;
		}
	}

	public void setReady(boolean ready) {
		if(host){
			this.ready = ready;
		}
		else{
			Client cl = (Client) tr;
			cl.setReady(true);
			System.out.println("Client Ready!");
		}
		
	}
	public void setOtherReady(boolean ready){
		if(host){
			System.out.println("Other client is ready!");
			this.otherReady = ready;
		}
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	
	public String getHostIP(){
		return hostIP;
		
	}
	public int getPort(){
		return port;
	}




}
