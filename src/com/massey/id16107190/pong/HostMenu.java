package com.massey.id16107190.pong;

import java.awt.Color;
import java.awt.event.MouseEvent;

public class HostMenu{
	// THIS CODE IS A COMPLETE MESS I KNOW!
	private ClientSettings cs = ClientSettings.getInstance();
	private GameState gs = GameState.getInstance();
	private NetworkSystem ns = NetworkSystem.getInstance();
	private Colors c = new Colors();
	
	private Button startButton = new Button(new Box(cs.getResX()/2, (float) (cs.getResY()*0.85), 260, 60, true, "Start"), "Searching...", 160, 16, true);
	private Button text1 = new Button(new Box(cs.getResX()/2, (float) (cs.getResY()*0.1), 0, 0, true, "oppText"), "Waiting for Opponent", 400, 60, true);
	
	private Button text2 = new Button(new Box(cs.getResX()/2, (float) (cs.getResY()*0.25), 0, 0, true, "oppText"), "Select your paddle colour", 320, 40, true);
	
	private Button backdrop1 = new Button(new Box((float) (cs.getResX()*0.5), (float) (cs.getResY()*0.4), 554, 100, true, "backdrop1"), "", 0, 0, true);
	
	private Button highlight1 = new Button(new Box((float) (cs.getResX()*0.125), (float) (cs.getResY()*0.4), 84, 84, true, "highlight1"), "", 0, 0, true);
	
	private Button color1 = new Button(new Box((float) (cs.getResX()*0.125), (float) (cs.getResY()*0.4), 76, 76, true, "color1"), "", 0, 0, true);
	private Button color2 = new Button(new Box((float) (cs.getResX()*0.275), (float) (cs.getResY()*0.4), 76, 76, true, "color2"), "", 0, 0, true);
	private Button color3 = new Button(new Box((float) (cs.getResX()*0.425), (float) (cs.getResY()*0.4), 76, 76, true, "color3"), "", 0, 0, true);
	private Button color4 = new Button(new Box((float) (cs.getResX()*0.575), (float) (cs.getResY()*0.4), 76, 76, true, "color4"), "", 0, 0, true);
	private Button color5 = new Button(new Box((float) (cs.getResX()*0.725), (float) (cs.getResY()*0.4), 76, 76, true, "color5"), "", 0, 0, true);
	private Button color6 = new Button(new Box((float) (cs.getResX()*0.875), (float) (cs.getResY()*0.4), 76, 76, true, "color6"), "", 0, 0, true);
	
	private Button text3 = new Button(new Box(cs.getResX()/2, (float) (cs.getResY()*0.52), 0, 0, true, "oppText"), "Select the ball colour", 280, 40, true);
	
	private Button backdrop2 = new Button(new Box((float) (cs.getResX()*0.5), (float) (cs.getResY()*0.65), 554, 100, true, "backdrop1"), "", 0, 0, true);
	
	private Button highlight2 = new Button(new Box((float) (cs.getResX()*0.125), (float) (cs.getResY()*0.65), 84, 84, true, "highlight1"), "", 0, 0, true);
	
	private Button bcolor1 = new Button(new Box((float) (cs.getResX()*0.125), (float) (cs.getResY()*0.65), 76, 76, true, "bcolor1"), "", 0, 0, true);
	private Button bcolor2 = new Button(new Box((float) (cs.getResX()*0.275), (float) (cs.getResY()*0.65), 76, 76, true, "bcolor2"), "", 0, 0, true);
	private Button bcolor3 = new Button(new Box((float) (cs.getResX()*0.425), (float) (cs.getResY()*0.65), 76, 76, true, "bcolor3"), "", 0, 0, true);
	private Button bcolor4 = new Button(new Box((float) (cs.getResX()*0.575), (float) (cs.getResY()*0.65), 76, 76, true, "bcolor4"), "", 0, 0, true);
	private Button bcolor5 = new Button(new Box((float) (cs.getResX()*0.725), (float) (cs.getResY()*0.65), 76, 76, true, "bcolor5"), "", 0, 0, true);
	private Button bcolor6 = new Button(new Box((float) (cs.getResX()*0.875), (float) (cs.getResY()*0.65), 76, 76, true, "bcolor6"), "", 0, 0, true);
	
	private Button back = new Button(new Box((float) (cs.getResX()*0.04), (float) (cs.getResY()*0.04), 50, 50, true, "back"), "<", 22, 28, true);
	private Button muteBtn = new Button(new Box((float) (cs.getResX()*0.15), (float) (cs.getResY()*0.85), 80, 60, true, "mute"), "Sound", 60, 16, true);

	public HostMenu(){
		startButton.setBoxColor(c.lightGray);
		startButton.setTextSize(30);
		startButton.setTextColor(c.darkGray);
		text1.setTextColor(new Color(0,0,0));
		text2.setTextColor(new Color(30,30,30));
		text2.setTextSize(26);
		highlight1.setBoxColor(new Color(30,30,30));
		highlight2.setBoxColor(new Color(30,30,30));
		backdrop1.setBoxColor(new Color(200,200,200));
		backdrop2.setBoxColor(new Color(200,200,200));
		text3.setTextColor(new Color(30,30,30));
		text3.setTextSize(26);
		
		color1.setBoxColor(c.red);
		color2.setBoxColor(c.orange);
		color3.setBoxColor(c.green);
		color4.setBoxColor(c.blue);
		color5.setBoxColor(c.purple);
		color6.setBoxColor(c.pink);
		
		bcolor1.setBoxColor(c.red);
		bcolor2.setBoxColor(c.orange);
		bcolor3.setBoxColor(c.green);
		bcolor4.setBoxColor(c.blue);
		bcolor5.setBoxColor(c.purple);
		bcolor6.setBoxColor(c.pink);
		
		back.setBoxColor(new Color(200,200,200));
		back.setTextColor(new Color(30,30,30));
		
		muteBtn.setBoxColor(c.green);
		muteBtn.setTextColor(c.darkGray);
		muteBtn.setTextSize(20);
		
	}
	private int length = 22;

	private boolean visible = false;

	public Button get(int i) {
		Button returnVar = null;
		switch (i){
		case 0: returnVar = startButton;
				break;
		case 1: returnVar = text1;
				break;
		case 2: returnVar = text2;
				break;
		case 3: returnVar = backdrop1;
				break;
		case 4: returnVar = backdrop2;
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
		case 12: returnVar = text3;
				break;
		case 13: returnVar = highlight2;
				break;
		case 14: returnVar = bcolor1;
				break;
		case 15: returnVar = bcolor2;
				break;
		case 16: returnVar = bcolor3;
				break;
		case 17: returnVar = bcolor4;
				break;
		case 18: returnVar = bcolor5;
				break;
		case 19: returnVar = bcolor6;
				break;	
		case 20: returnVar = back;
				break;
		case 21: returnVar = muteBtn;
				break;
		}
		return returnVar;
	}
	
	public Button getHighlight1(){
		return highlight1;
	}
	public Button getHighlight2(){
		return highlight2;
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
	public int getClicks(int i, MouseEvent event){
		Button btn = this.get(i);
		if(btn != null){
			if(btn.getEnabled() == true){
				boolean isHit = btn.withinHitbox(event);
				if(isHit == true){ // y coord check
					if(i == 0){ // Start button
						if(cs.mouseCooldown == false){
							if(ns.isConnected() == true){
								return 1;
							}
						}
					}// PADDLE COLOURS
					if(i == 6){ //red
						Box h1 = this.getHighlight1().getBox();
						h1.setCoordX(btn.getBox().getCoordX());
						ns.setHostColor(c.red);		
					}
					if(i == 7){ //orange
						Box h1 = this.getHighlight1().getBox();
						h1.setCoordX(btn.getBox().getCoordX());
						ns.setHostColor(c.orange);		
					}
					if(i == 8){ //green
						Box h1 = this.getHighlight1().getBox();
						h1.setCoordX(btn.getBox().getCoordX());
						ns.setHostColor(c.green);		
					}
					if(i == 9){ //blue
						Box h1 = this.getHighlight1().getBox();
						h1.setCoordX(btn.getBox().getCoordX());
						ns.setHostColor(c.blue);		
					}
					if(i == 10){ //purple
						Box h1 = this.getHighlight1().getBox();
						h1.setCoordX(btn.getBox().getCoordX());
						ns.setHostColor(c.purple);		
					}
					if(i == 11){ //pink
						Box h1 = this.getHighlight1().getBox();
						h1.setCoordX(btn.getBox().getCoordX());
						ns.setHostColor(c.pink);		
					}
					// BALL COLOURS
					if(i == 14){
						Box h2 = this.getHighlight2().getBox();
						h2.setCoordX(btn.getBox().getCoordX());
						ns.setBallColor(c.red);		
					}
					if(i == 15){
						Box h2 = this.getHighlight2().getBox();
						h2.setCoordX(btn.getBox().getCoordX());
						ns.setBallColor(c.orange);		
					}
					if(i == 16){
						Box h2 = this.getHighlight2().getBox();
						h2.setCoordX(btn.getBox().getCoordX());
						ns.setBallColor(c.green);		
					}
					if(i == 17){
						Box h2 = this.getHighlight2().getBox();
						h2.setCoordX(btn.getBox().getCoordX());
						ns.setBallColor(c.blue);		
					}
					if(i == 18){
						Box h2 = this.getHighlight2().getBox();
						h2.setCoordX(btn.getBox().getCoordX());
						ns.setBallColor(c.purple);		
					}
					if(i == 19){
						Box h2 = this.getHighlight2().getBox();
						h2.setCoordX(btn.getBox().getCoordX());
						ns.setBallColor(c.pink);		
					}
					if(i == 20){
						this.setVisible(false);
						Menus.setVisible(1);
						ns.setHostColor(c.white);	
						ns.setClientColor(c.white);
						ns.setBallColor(c.white);
						gs.setOnline(false);
						ns.setHost(false);
						ns.setPlayerIndex(0);	
					}
					if(i == 21){
						if(cs.getMuted() == false){
							btn.setBoxColor(c.red);
							cs.setMuted(true);
						}
						else{
							btn.setBoxColor(c.green);
							cs.setMuted(false);
						}
					}
				}
			}
		}
		return 0;	
	}

}

