package com.massey.id16107190.pong;

import java.awt.event.KeyEvent;

public class PlayerController {
	
	private int playerIndex = 0;
	private ClientSettings cs = ClientSettings.getInstance();
	private int playerMove = 0;
	private boolean kbPressedUP = false;
	private boolean kbPressedDOWN = false;
	private int playerPos = 300;
	
	public PlayerController(int pIndex){
		playerIndex = pIndex;
	}
	
	public void resetPC(){
		playerMove = 0;
		kbPressedUP = false;
		kbPressedDOWN = false;
		playerPos = 300;
	}
	
	public int evaluateKeyPress(KeyEvent event){
		// Player 1 up/down (W/S)
		if(event.getKeyCode() == cs.getUpBind(playerIndex)){
			playerMove = 1;
			kbPressedUP = true;
		}
		if(event.getKeyCode() == cs.getDownBind(playerIndex)){
			playerMove = -1;
			kbPressedDOWN = true;
		}
		if(event.getKeyCode() == cs.getDownBind(playerIndex) && event.getKeyCode() == cs.getUpBind(playerIndex)){
			playerMove = 0;
		}
		return playerMove;
	}
	
	public int evaluateKeyRelease(KeyEvent event){
		
		if(event.getKeyCode() == cs.getUpBind(playerIndex) && kbPressedDOWN == false){
			playerMove = 0;
			kbPressedUP = false;
		}
		if(event.getKeyCode() == cs.getDownBind(playerIndex) && kbPressedUP == false){
			playerMove = 0;
			kbPressedDOWN = false;
		}
		if(event.getKeyCode() == cs.getDownBind(playerIndex)){
			kbPressedDOWN = false;
		}
		if(event.getKeyCode() == cs.getUpBind(playerIndex)){
			kbPressedUP = false;
		}
		if(event.getKeyCode() == cs.getUpBind(playerIndex) && kbPressedDOWN == true){
			playerMove = -1;	
		}
		if(event.getKeyCode() == cs.getDownBind(playerIndex) && kbPressedUP == true){
			playerMove = 1;	
		}
		return playerMove;
	}

	public int getPlayerPos() {
		return playerPos;
	}

	public void setPlayerPos(int playerPos) {
		this.playerPos = playerPos;
	}
}
