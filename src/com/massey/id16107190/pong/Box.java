package com.massey.id16107190.pong;

import java.util.Arrays;
import java.util.List;

public class Box {
	private boolean visible = false;
	
	private float coordX = 0;
	private float coordY = 0;
	private float width = 0;
	private float height = 0;
	private List<Float> hitbox = Arrays.asList(0f, 0f, 0f, 0f);
	private String label = "";
	
	
	public Box(float cX, float cY, float w, float h, boolean vs) {
		this.coordX = cX;
		this.coordY= cY;
		this.width = w;
		this.height = h;
		this.visible = vs;
		
		float hb1 = getCoordX()-(w/2);
		float hb2 = getCoordY()-(h/2);
		float hb3 = w;
		float hb4 = h;
		this.hitbox = Arrays.asList(hb1,hb2,hb3,hb4);
	}
	
	public Box(float cX, float cY, float w, float h) {
		this.coordX = cX;
		this.coordY= cY;
		this.width = w;
		this.height = h;
		
		float hb1 = getCoordX()-(w/2);
		float hb2 = getCoordY()-(h/2);
		float hb3 = w;
		float hb4 = h;
		this.hitbox = Arrays.asList(hb1,hb2,hb3,hb4);
	}
	
	public Box(float cX, float cY, float w, float h, String lb) {
		this.coordX = cX;
		this.coordY= cY;
		this.width = w;
		this.height = h;
		this.label = lb;
		
		float hb1 = getCoordX()-(w/2);
		float hb2 = getCoordY()-(h/2);
		float hb3 = w;
		float hb4 = h;
		this.hitbox = Arrays.asList(hb1,hb2,hb3,hb4);
	}
	
	public Box(float cX, float cY, float w, float h, boolean vs, String lb) {
		this.coordX = cX;
		this.coordY= cY;
		this.width = w;
		this.height = h;
		this.visible = vs;
		this.label = lb;
		
		float hb1 = getCoordX()-(w/2);
		float hb2 = getCoordY()-(h/2);
		float hb3 = w;
		float hb4 = h;
		this.hitbox = Arrays.asList(hb1,hb2,hb3,hb4);
	}
	
	
	public boolean isEnabled() {
		return visible;
	}
	public void enable(){
		visible = true;
	}
	public void disable(){
		visible = false;
	}
	public float getCoordX() {
		return coordX;
	}
	public void setCoordX(float coordX) {
		this.coordX = coordX;
	}
	public float getCoordY() {
		return coordY;
	}
	public void setCoordY(float coordY) {
		this.coordY = coordY;
	}
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public List<Float> getHitbox() {
		return hitbox;
	}
	public void setHitbox(List<Float> hitbox) {
		this.hitbox = hitbox;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


}
