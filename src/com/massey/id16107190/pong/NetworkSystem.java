package com.massey.id16107190.pong;

public final class NetworkSystem {
	 
    private static NetworkSystem INSTANCE;
    
    private String hostIP = "";
    private String clientIP = "";
    private int port = 1234;
    private int playerIndex = 0;
    
    private boolean connected = false;
    private boolean host = false;
    private boolean client = false;
    
    private boolean ready = false;
    private boolean otherReady = true;
	
     
    private NetworkSystem() {  
    }
     
    public static NetworkSystem getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new NetworkSystem();
        }
         
        return INSTANCE;
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
			sendReady();
		}
		
	}
	
	public Box sendData(Box p1, Box p2, Ball ball) {
		//System.out.println("Sending Data");
		
		if(playerIndex == 1){
			return p2;
		}
		return p1;
		
		
	}

	public void sendGameState(GameState gs) {
		System.out.println("Sending Game State");
		
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}




}
