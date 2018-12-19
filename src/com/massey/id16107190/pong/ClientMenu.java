package com.massey.id16107190.pong;

import java.awt.Color;

public class ClientMenu{
	// THIS CODE IS A COMPLETE MESS I KNOW!
	private ClientSettings cs = ClientSettings.getInstance();
	private Colors c = new Colors();
	
	private Button connectButton = new Button(new Box(cs.getResX()/2, (float) (cs.getResY()*0.85), 260, 60, true, "connect"), "Connect", 160, 26, true);
	private Button text1 = new Button(new Box(cs.getResX()/2, (float) (cs.getResY()*0.1), 0, 0, true, "oppText"), "Connect to Opponent", 420, 60, true);
	
	private Button text2 = new Button(new Box(cs.getResX()/2, (float) (cs.getResY()*0.25), 0, 0, true, "urText"), "Select your paddle colour", 320, 40, true);
	
	private Button text3 = new Button(new Box(cs.getResX()/2, (float) (cs.getResY()*0.6), 0, 0, true, "ipText"), "IP: " + cs.keysPressed, 54, 60, true);
	
	private Button backdrop1 = new Button(new Box((float) (cs.getResX()*0.5), (float) (cs.getResY()*0.4), 554, 100, true, "backdrop1"), "", 0, 0, true);
	
	private Button highlight1 = new Button(new Box((float) (cs.getResX()*0.125), (float) (cs.getResY()*0.4), 84, 84, true, "highlight1"), "", 0, 0, true);
	
	private Button color1 = new Button(new Box((float) (cs.getResX()*0.125), (float) (cs.getResY()*0.4), 76, 76, true, "color1"), "", 0, 0, true);
	private Button color2 = new Button(new Box((float) (cs.getResX()*0.275), (float) (cs.getResY()*0.4), 76, 76, true, "color2"), "", 0, 0, true);
	private Button color3 = new Button(new Box((float) (cs.getResX()*0.425), (float) (cs.getResY()*0.4), 76, 76, true, "color3"), "", 0, 0, true);
	private Button color4 = new Button(new Box((float) (cs.getResX()*0.575), (float) (cs.getResY()*0.4), 76, 76, true, "color4"), "", 0, 0, true);
	private Button color5 = new Button(new Box((float) (cs.getResX()*0.725), (float) (cs.getResY()*0.4), 76, 76, true, "color5"), "", 0, 0, true);
	private Button color6 = new Button(new Box((float) (cs.getResX()*0.875), (float) (cs.getResY()*0.4), 76, 76, true, "color6"), "", 0, 0, true);
	
	private Button back = new Button(new Box((float) (cs.getResX()*0.04), (float) (cs.getResY()*0.04), 50, 50, true, "back"), "<", 22, 28, true);

	private Button muteBtn = new Button(new Box((float) (cs.getResX()*0.15), (float) (cs.getResY()*0.85), 80, 60, true, "mute"), "Sound", 60, 16, true);
	
	public ClientMenu(){
		connectButton.setBoxColor(new Color(60,60,60));
		text1.setTextColor(new Color(0,0,0));
		text2.setTextColor(new Color(30,30,30));
		text2.setTextSize(26);
		text3.setTextColor(new Color(30,30,30));
		text3.setTextSize(50);
		highlight1.setBoxColor(new Color(30,30,30));
		backdrop1.setBoxColor(new Color(200,200,200));
		
		color1.setBoxColor(c.red);
		color2.setBoxColor(c.orange);
		color3.setBoxColor(c.green);
		color4.setBoxColor(c.blue);
		color5.setBoxColor(c.purple);
		color6.setBoxColor(c.pink);
		
		back.setBoxColor(new Color(200,200,200));
		back.setTextColor(new Color(30,30,30));
		
		muteBtn.setBoxColor(c.green);
		muteBtn.setTextColor(c.darkGray);
		muteBtn.setTextSize(20);
		
	}
	private int length = 15;

	private boolean visible = false;

	public Button get(int i) {
		Button returnVar = null;
		switch (i){
		case 0: returnVar = connectButton;
				break;
		case 1: returnVar = text1;
				break;
		case 2: returnVar = text2;
				break;
		case 3: returnVar = backdrop1;
				break;
		case 4: returnVar = null;
				break;
		case 5: returnVar = highlight1;
				break;
		case 6: returnVar = color1;
				break;
		case 7: returnVar = color2;
				break;
		case 8: returnVar = color3;
				break;
		case 9: returnVar = color4;
				break;
		case 10: returnVar = color5;
				break;
		case 11: returnVar = color6;
				break;
		case 12: returnVar = back;
				break;
		case 13: returnVar = muteBtn;
				break;
		case 14: returnVar = text3;
				break;
		}
		
		return returnVar;
	}
	
	public Button getHighlight1(){
		return highlight1;
	}

	public int length() {
		return length;
	}
	public boolean isMenuVisible(){
		return visible;
	}
	public void setVisible(boolean b){
		visible = b;
	}

}

