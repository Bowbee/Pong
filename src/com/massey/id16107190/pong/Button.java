package com.massey.id16107190.pong;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.List;

public class Button {

	private Box box;
	private String text;
	private Boolean enabled = true;	
	private Color boxColor = new Color(0,0,0);
	private Color textColor = new Color(255,255,255);
	private float textWidth;
	private float textHeight;
	
	
	public Button(Box box2, String str, float tw, float th, boolean en) {
		box = box2;
		text = str;
		textWidth = tw;
		textHeight = th;
		enabled = en;
		
	}

	public void setText(String in){
		text = in;
	}
	
	public String getText(){
		return text;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}


	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


	public Box getBox() {
		return box;
	}


	public void setBox(Box box) {
		this.box = box;
	}
	
	public boolean withinHitbox(MouseEvent event){
		
		List<Float> hitbox = box.getHitbox();
		if(event.getX() > hitbox.get(0) && event.getX() < (hitbox.get(0)+hitbox.get(2))){ // x coord check
			if(event.getY() > hitbox.get(1) && event.getY() < (hitbox.get(1)+hitbox.get(3))){ // y coord check
				return true;
			}
		}
		return false;
	}

	public Color getBoxColor() {
		return boxColor;
	}

	public void setBoxColor(Color boxColor) {
		this.boxColor = boxColor;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public float getTextWidth() {
		return textWidth;
	}

	public void setTextWidth(float textWidth) {
		this.textWidth = textWidth;
	}

	public float getTextHeight() {
		return textHeight;
	}

	public void setTextHeight(float textHeight) {
		this.textHeight = textHeight;
	}

}
