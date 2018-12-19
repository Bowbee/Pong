package com.massey.id16107190.pong;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Menus extends GameEngine {
	
	private static ClientSettings cs = ClientSettings.getInstance();

	private boolean gameStarted = false;
	private boolean titleSet = false;

	private MainMenu mMenu;
	private HostMenu hostMenu;
	private boolean mouseCooldown = false;
	private long mouseTime;
	
	public void init() {
		
		// Initialise Window Size
		setWindowSize(cs.getResX(), cs.getResY());
		
		mMenu = new MainMenu();
		hostMenu = new HostMenu();
		
	}

	public static void start() {
		
		Game pong = new Game();
		GameState gs = GameState.getInstance();
		NetworkSystem ns = NetworkSystem.getInstance();
		
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
		if(mouseCooldown == true){
			if(time - mouseTime >= 200){
				mouseCooldown = false;
			}
		}
	}
	
	public void paintComponent() {
		// Clear the background to black
		changeBackgroundColor(white);
		clearBackground(cs.getResX(), cs.getResY());

		// Draw Board on the screen
		changeColor(white);
		
		if(mMenu.isMenuVisible()){
			for(int i=0; i < mMenu.length(); i++){
				if(mMenu.enabled(i) == true){
					Button btn = mMenu.get(i);
					
					Box box = btn.getBox();
					changeColor(btn.getBoxColor());
					drawSolidRectangle(box.getHitbox().get(0), box.getHitbox().get(1), box.getHitbox().get(2), box.getHitbox().get(3));
					
					changeColor(btn.getTextColor());
					String text = btn.getText();
					float xCoord = btn.getBox().getCoordX() - btn.getTextWidth()/2;
					float yCoord = btn.getBox().getCoordY() + btn.getTextHeight()/2;
					drawBoldText(xCoord,yCoord,text);		
				}
			}
		}
		if(hostMenu.isMenuVisible()){
			for(int i=0; i < hostMenu.length(); i++){
				if(hostMenu.enabled(i) == true){
					Button btn = hostMenu.get(i);
					
					Box box = btn.getBox();
					changeColor(btn.getBoxColor());
					drawSolidRectangle(box.getHitbox().get(0), box.getHitbox().get(1), box.getHitbox().get(2), box.getHitbox().get(3));
					
					changeColor(btn.getTextColor());
					String text = btn.getText();
					float xCoord = btn.getBox().getCoordX() - btn.getTextWidth()/2;
					float yCoord = btn.getBox().getCoordY() + btn.getTextHeight()/2;
					drawBoldText(xCoord,yCoord,text);					
				}
			}
		}

		changeColor(white);

	}
	private void mouseCD() {
		mouseTime = time;
		mouseCooldown = true;
		System.out.println("mousecd");
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
	public void mouseClicked(MouseEvent event) {
		if(mouseCooldown == false){
			if(event.getButton() == 1){ //if left click
				if(mMenu.isMenuVisible()){
					for(int i=0; i < mMenu.length(); i++){
						if(mMenu.enabled(i) == true){
							Button btn = mMenu.get(i);
							boolean isHit = btn.withinHitbox(event);
							if(isHit == true){ // y coord check
								if(i == 0){ // Play btn
									if(gameStarted == false){
										gameStarted = true;
										System.out.println("Start the game");
										mFrame.dispose();
										Menus.start();
									}
								}
								if(i == 1){ // Client btn
									if(gameStarted == false){
										System.out.println("Start the client");
										gameStarted = true;
										mMenu.setVisible(false);
										//mFrame.dispose();
										//Menus.startClient();
									}	
								}
								if(i == 2){ // Host btn
									if(gameStarted == false){
										System.out.println("Start the host");
										mMenu.setVisible(false);
										hostMenu.setVisible(true);
										mouseCD();
										//gameStarted = true;
									}
								}
							}
						}
					}
				}
				if(hostMenu.isMenuVisible()){
					for(int i=0; i < hostMenu.length(); i++){
						if(hostMenu.enabled(i) == true){
							Button btn = hostMenu.get(i);
							boolean isHit = btn.withinHitbox(event);
							if(isHit == true){ // y coord check
								if(i == 0){ // Start button
									if(mouseCooldown == false){
										gameStarted = true;
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
}
