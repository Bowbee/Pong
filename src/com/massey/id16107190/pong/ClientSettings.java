package com.massey.id16107190.pong;

import java.awt.Color;
import java.awt.event.KeyEvent;

public final class ClientSettings{
	 
    private static ClientSettings INSTANCE;
	private int resX = 600;
	private int resY = 600;
	private int fps = 60;
	
	private int p1u = KeyEvent.VK_W;
	private int p1d = KeyEvent.VK_S;
	
	private int p2u = KeyEvent.VK_UP;
	private int p2d = KeyEvent.VK_DOWN;
	
	private double waitTime = 1500; //ms, sec/1000
	
	public Color menuBackColor = new Color(240,240,240);
	private boolean muted = false;
	public boolean mouseCooldown = false;
	public String keysPressed = "";
	
     
    private ClientSettings() {  
    }
     
    public static ClientSettings getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ClientSettings();
        }
         
        return INSTANCE;
    }
 
	public int getResX() {
		return resX;
	}
	public void setResX(int resX) {
		this.resX = resX;
	}
	public int getResY() {
		return resY;
	}
	public void setResY(int resY) {
		this.resY = resY;
	}
	public int getFPS(){
		return fps;
	}
	public boolean getMouseCooldown(){
		return mouseCooldown;
	}
	public void setMouseCooldown(boolean b){
		mouseCooldown = b;
	}
	
	
	public int getUpBind(int pIndex){
		
		if(pIndex == 1){
			return p1u;
		}
		else return p2u;	
	}
	public int getDownBind(int pIndex){
		
		if(pIndex == 1){
			return p1d;
		}
		else return p2d;	
	}

	
	public void setP1u(int p1u) {
		this.p1u = p1u;
	}

	public void setP1d(int p1d) {
		this.p1d = p1d;
	}

	public void setP2u(int p2u) {
		this.p2u = p2u;
	}

	public void setP2d(int p2d) {
		this.p2d = p2d;
	}

	public double getWaitTime() {
		return waitTime ;
	}

	public boolean getMuted() {
		return muted ;
	}

	public void setMuted(boolean b) {
		this.muted = b;
		
	}
}
