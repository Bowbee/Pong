package com.massey.id16107190.pong;

public class NetPing{
	
	private NetworkSystem ns = NetworkSystem.getInstance();
	
	private Thread tr;
	
	public NetPing(){
		if(ns.isClient()){
			System.out.println("Client1");
			tr = new NetPingClient();
			tr.start();
			
		}
		if(ns.isHost()){
			System.out.println("Server1");
			tr = new NetPingServer();
			tr.start();
		}
	}

}
