package com.massey.id16107190.pong;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Menus extends GameEngine {
	
	private static ClientSettings cs = ClientSettings.getInstance();
	private GameState gs = GameState.getInstance();
	private NetworkSystem ns = NetworkSystem.getInstance();
	private Colors c = new Colors();

	private boolean gameStarted = false;
	private boolean titleSet = false;

	private static MainMenu mMenu;
	private HostMenu hostMenu;
	private ClientMenu clientMenu;
	private PlayMenu playMenu;
	private long mouseTime;
	private boolean serverPinging;
	
	public void init() {
		
		// Initialise Window Size
		setWindowSize(cs.getResX(), cs.getResY());
		
		mMenu = new MainMenu();
		hostMenu = new HostMenu();
		clientMenu = new ClientMenu();
		playMenu = new PlayMenu();
		
	}

	public static void start() {
		
		Game pong = new Game();
		GameState gs = GameState.getInstance();
		
		//gs.setPlayers(true, true);
		gs.setOnline(false);
		
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
		if(titleSet == false){
			mFrame.setTitle("Pong Options");
			titleSet = true;
		}
		if(cs.mouseCooldown == true){
			if(time - mouseTime >= 200){
				cs.mouseCooldown = false;
			}
		}
		if(gs.isOnline() && ns.isHost() && serverPinging == false){
			new NetPing();
			serverPinging = true;
			System.out.println("Server runninringing");
		}
		if(ns.isConnected() == true && gs.isOnline() && gameStarted == false){
			if(ns.isClient()){
				gameStarted = true;
				mFrame.dispose();
				Menus.startClient();
			}
			if(ns.isHost()){
				gameStarted = true;
				mFrame.dispose();
				Menus.startHost();
			}
		}
	}
	
	public void paintComponent() {
		// Clear the background to black
		changeBackgroundColor(cs.menuBackColor);
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
					int textSize = btn.getTextSize();
					float xCoord = btn.getBox().getCoordX() - btn.getTextWidth()/2;
					float yCoord = btn.getBox().getCoordY() + btn.getTextHeight()/2;
					drawBoldText(xCoord,yCoord,text,"Arial", textSize);
				}
			}
		}
		if(hostMenu.isMenuVisible()){
			for(int i=0; i < hostMenu.length(); i++){
				Button btn = hostMenu.get(i);
				if(btn != null){
					if(btn.getEnabled() == true){
						
						Box box = btn.getBox();
						changeColor(btn.getBoxColor());
						drawSolidRectangle(box.getHitbox().get(0), box.getHitbox().get(1), box.getHitbox().get(2), box.getHitbox().get(3));
						
						changeColor(btn.getTextColor());
						String text = btn.getText();
						int textSize = btn.getTextSize();
						float xCoord = btn.getBox().getCoordX() - btn.getTextWidth()/2;
						float yCoord = btn.getBox().getCoordY() + btn.getTextHeight()/2;
						drawBoldText(xCoord,yCoord,text,"Arial", textSize);				
					}
				}
			}
		}
		if(clientMenu.isMenuVisible()){
			for(int i=0; i < clientMenu.length(); i++){
				Button btn = clientMenu.get(i);
				if(btn != null){
					if(btn.getEnabled() == true){
						
						Box box = btn.getBox();
						changeColor(btn.getBoxColor());
						drawSolidRectangle(box.getHitbox().get(0), box.getHitbox().get(1), box.getHitbox().get(2), box.getHitbox().get(3));
						
						changeColor(btn.getTextColor());
						String text = btn.getText();
						int textSize = btn.getTextSize();
						float xCoord = btn.getBox().getCoordX() - btn.getTextWidth()/2;
						float yCoord = btn.getBox().getCoordY() + btn.getTextHeight()/2;
						drawBoldText(xCoord,yCoord,text,"Arial", textSize);				
					}
				}
			}
		}
		if(playMenu.isMenuVisible()){
			for(int i=0; i < playMenu.length(); i++){
				Button btn = playMenu.get(i);
				if(btn != null){
					if(btn.getEnabled() == true){
						
						Box box = btn.getBox();
						changeColor(btn.getBoxColor());
						drawSolidRectangle(box.getHitbox().get(0), box.getHitbox().get(1), box.getHitbox().get(2), box.getHitbox().get(3));
						
						changeColor(btn.getTextColor());
						String text = btn.getText();
						int textSize = btn.getTextSize();
						float xCoord = btn.getBox().getCoordX() - btn.getTextWidth()/2;
						float yCoord = btn.getBox().getCoordY() + btn.getTextHeight()/2;
						drawBoldText(xCoord,yCoord,text,"Arial", textSize);				
					}
				}
			}
		}

		changeColor(white);

	}
	private void mouseCD() {
		mouseTime = time;
		cs.mouseCooldown = true;
	}
	
	public void keyPressed(KeyEvent event) {
		if(this.gameStarted == false){
			if(event.getKeyChar() != KeyEvent.VK_BACK_SPACE){
				if(event.getKeyChar() != KeyEvent.VK_ENTER){
					if(cs.keysPressed.length() <= 16){
						cs.keysPressed += String.valueOf(event.getKeyChar());
					}
				}
			}
			else{
				if(cs.keysPressed.length() > 0){
					cs.keysPressed = cs.keysPressed.substring(0, cs.keysPressed.length() - 1);
				}
			}
			clientMenu.get(14).setText("IP: "+cs.keysPressed);
			clientMenu.get(14).setTextWidth((28*cs.keysPressed.length())+60);
			ns.setHostIP(cs.keysPressed);
		}
		else{
			cs.keysPressed = "";
		}
	}
	
	public void mouseClicked(MouseEvent event) {
		if(cs.mouseCooldown == false){
			if(event.getButton() == 1){ //if left click
				if(mMenu.isMenuVisible()){
					for(int i=0; i < mMenu.length(); i++){
						if(mMenu.enabled(i) == true){
							Button btn = mMenu.get(i);
							
							boolean isHit = btn.withinHitbox(event);
							if(isHit == true){ // y coord check
								if(i == 0){ // Play btn
									if(gameStarted == false){
										gameStarted = false;
										System.out.println("Start the game");
										mMenu.setVisible(false);
										playMenu.setVisible(true);
										gs.setPlayers(true, false);
										gs.setP1Color(c.red);
										gs.setP2Color(c.red);
										gs.setBallColor(c.red);
										//mFrame.dispose();
										//Menus.start();
									}
								}
								if(i == 1){ // Client btn
									if(gameStarted == false){
										System.out.println("Start the client");
										mMenu.setVisible(false);
										clientMenu.setVisible(true);
										gs.setPlayers(true, true);
										gs.setOnline(true);
										ns.setClient(true);
										ns.setPlayerIndex(2);
										ns.setClientColor(c.red);
										mouseCD();
										//mFrame.dispose();
										//Menus.startClient();
									}	
								}
								if(i == 2){ // Host btn
									if(gameStarted == false){
										System.out.println("Start the host");
										mMenu.setVisible(false);
										hostMenu.setVisible(true);
										gs.setPlayers(true, true);
										gs.setOnline(true);
										ns.setHost(true);
										ns.setPlayerIndex(1);
										ns.setHostColor(c.red);	
										ns.setBallColor(c.red);
										mouseCD();
										//gameStarted = true;
									}
								}
							}
						}
					}
				}
				if(playMenu.isMenuVisible()){
					for(int i=0; i < playMenu.length(); i++){
						int rtv = playMenu.getClicks(i, event);
						if (rtv == 1){
							gameStarted = true;
							mFrame.dispose();
							Menus.start();
						}
					}
				}
				if(hostMenu.isMenuVisible()){
					for(int i=0; i < hostMenu.length(); i++){
						int rtv = hostMenu.getClicks(i, event);
						if (rtv == 1){
							gameStarted = true;
							mFrame.dispose();
							Menus.startHost();
						}
					}
				}
				
				if(clientMenu.isMenuVisible()){
					cs.keysPressed = "";
					for(int i=0; i < clientMenu.length(); i++){
						int rtv = clientMenu.getClicks(i, event);
						if (rtv == 1){
							gameStarted = true;
							mFrame.dispose();
							Menus.startClient();
						}
					}
				}
			}
		}		
	}

	public static void setVisible(int i) {
		if(i == 1){
			mMenu.setVisible(true);
			cs.mouseCooldown = false;
		}
	}
}
