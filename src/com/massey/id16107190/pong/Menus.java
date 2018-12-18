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
		
		Box playBtn = new Box(cs.getResX()/2, cs.getResY()/2, 200, 80, true, "Play");
		buttons.add(playBtn);
		
	}

	public static void start() {
		
		Game pong = new Game();
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
		float ycoord = cs.getResY()/2 + (26/2);
		drawBoldText(xcoord, ycoord, "Play");
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
						}	
					}
				}
			}
		}		
	}

}
