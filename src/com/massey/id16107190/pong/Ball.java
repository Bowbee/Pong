package com.massey.id16107190.pong;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Ball implements Serializable {
	
	private float posX;
	private float posY;
	private int radius;
	
	public Ball(int x, int y, int r){
		setPosX(x);
		setPosY(y);
		setRadius(r);
	}

	public float getPosX() {
		return posX;
	}

	public void setPosX(float f) {
		this.posX = f;
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

}
