package com.massey.id16107190.pong;

import java.awt.Color;

public final class NetworkSystem {
	 
    private static NetworkSystem INSTANCE;
    
    private GameState gs = GameState.getInstance();
    
    private String hostIP = "127.0.0.1";
    private int port = 23;
    private int playerIndex = 0;
    
	private boolean connected = false;
    private boolean host = false;
    private boolean client = false;
    
    private boolean ready = false;
    private boolean otherReady = true;
    
	private Color hostColor;
	private Color clientColor;
	private Color ballColor;
    
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

	public Color getHostColor() {
		return hostColor;
	}

	public void setHostColor(Color hostColor) {
		this.hostColor = hostColor;
		if(host == true){
			if(playerIndex == 1){
				gs.setP1Color(hostColor);
			}
			if(playerIndex == 2){
				gs.setP2Color(hostColor);
			}
		}
	}

	public Color getClientColor() {
		return clientColor;
	}

	public void setClientColor(Color clientColor) {
		this.clientColor = clientColor;
		if(playerIndex == 1){
			gs.setP1Color(clientColor);
		}
		if(playerIndex == 2){
			gs.setP2Color(clientColor);
		}
	}

	public Color getBallColor() {
		return ballColor;
	}

	public void setBallColor(Color ballColor) {
		if(host == true){
			this.ballColor = ballColor;
			gs.setBallColor(ballColor);
		}
		
		
	}




}
