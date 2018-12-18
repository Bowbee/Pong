package com.massey.id16107190.pong;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Menus extends GameEngine {
	
	private static ClientSettings cs = ClientSettings.getInstance();
	private List<Box> buttons = new ArrayList<>();
	private boolean gameStarted = false;
	private boolean titleSet = false;
	private Color grey = new Color(100,100,100);
	
	public void init() {
		
		// Initialise Window Size
		setWindowSize(cs.getResX(), cs.getResY());
		
		Box playBtn = new Box(cs.getResX()/2, (float) (cs.getResY()*0.25), 200, 80, true, "Play");
		Box clientBtn = new Box(cs.getResX()/2, (float) (cs.getResY()*0.50), 200, 80, true, "Client");
		Box hostBtn = new Box(cs.getResX()/2, (float) (cs.getResY()*0.75), 200, 80, true, "Host");
		buttons.add(playBtn);
		buttons.add(clientBtn);
		buttons.add(hostBtn);
		
	}

	public static void start() {
		
		Game pong = new Game();
		GameState gs = GameState.getInstance();
		NetworkSystem ns = NetworkSystem.getInstance();
		/*
		gs.setPlayers(true, true);
		gs.setOnline(true);
		ns.setHost(true);
		ns.setPlayerIndex(1);
		*/
		
		gs.setPlayers(true, true);
		gs.setOnline(true);
		ns.setClient(true);
		ns.setPlayerIndex(2);
		
		
		GameEngine.createGame(pong, cs.getFPS());
	}
	public static void startHost() {
			
		Game pong = new Game();
		GameState gs = GameState.getInstance();
		NetworkSystem ns = NetworkSystem.getInstance();
		
		gs.setPlayers(true, true);
		gs.setOnline(true);
		ns.setHost(true);
		ns.setPlayerIndex(1);

		GameEngine.createGame(pong, cs.getFPS());
	}
	public static void startClient() {
		
		Game pong = new Game();
		GameState gs = GameState.getInstance();
		NetworkSystem ns = NetworkSystem.getInstance();
		/*
		gs.setPlayers(true, true);
		gs.setOnline(true);
		ns.setHost(true);
		ns.setPlayerIndex(1);
		*/
		
		gs.setPlayers(true, true);
		gs.setOnline(true);
		ns.setClient(true);
		ns.setPlayerIndex(2);
		
		
		GameEngine.createGame(pong, cs.getFPS());
	}

	@Override
	public void update(double dt) {
		// TODO Auto-generated method stub
		if(titleSet == false){
			mFrame.setTitle("Pong Options");
			titleSet = true;
		}

	}
	
	public void paintComponent() {
		// Clear the background to black
		changeBackgroundColor(white);
		clearBackground(cs.getResX(), cs.getResY());

		// Draw Board on the screen
		changeColor(white);
		
		
		changeColor(grey);
		for(Box btn: buttons){
			drawSolidRectangle(btn.getHitbox().get(0), btn.getHitbox().get(1), btn.getHitbox().get(2), btn.getHitbox().get(3));
		}		
		changeColor(white);
		float xcoord = cs.getResX()/2 - (80/2);
		float ycoord = (float) ((cs.getResY()*0.25) + (26/2));
		drawBoldText(xcoord, ycoord, "Play");
		xcoord = cs.getResX()/2 - (110/2);
		ycoord = (float) ((cs.getResY()*0.55) - (30/2));
		drawBoldText(xcoord, ycoord, "Client");
		xcoord = cs.getResX()/2 - (80/2);
		ycoord = (float) ((cs.getResY()*0.75) + (26/2));
		drawBoldText(xcoord, ycoord, "Host");
	}
	public void keyPressed(KeyEvent event) {
		if(gameStarted == false){
			if(event.getKeyCode() == KeyEvent.VK_ENTER){
				gameStarted = true;
				mFrame.dispose();
				Menus.start();
			}
		}
	}
	//System.out.println(event.getX() + " " + event.getY());
	public void mouseClicked(MouseEvent event) {
		if(event.getButton() == 1){ //if left click
			for(Box btn : buttons){ 
				if(btn.isEnabled()){	//for every button, if enabled do this:
					List<Float> hitbox = btn.getHitbox();
					if(event.getX() > hitbox.get(0) && event.getX() < (hitbox.get(0)+hitbox.get(2))){ // x coord check
						if(event.getY() > hitbox.get(1) && event.getY() < (hitbox.get(1)+hitbox.get(3))){ // y coord check
							if(btn.getLabel() == "Play"){
								if(gameStarted == false){
									gameStarted = true;
									System.out.println("Start the game");
									mFrame.dispose();
									Menus.start();
								}
							}
							if(btn.getLabel() == "Client"){
								if(gameStarted == false){
									gameStarted = true;
									System.out.println("Start the game client");
									mFrame.dispose();
									Menus.startClient();
								}	
							}
							if(btn.getLabel() == "Host"){
								if(gameStarted == false){
									gameStarted = true;
									System.out.println("Start the game host");
									mFrame.dispose();
									Menus.startHost();
								}				
							}
						}	
					}
				}
			}
		}		
	}

}
