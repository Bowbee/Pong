package com.massey.id16107190.pong;

import java.awt.Color;
import java.awt.event.MouseEvent;

public class PlayMenu{
	// THIS CODE IS A COMPLETE MESS I KNOW!
	private ClientSettings cs = ClientSettings.getInstance();
	private GameState gs = GameState.getInstance();
	private NetworkSystem ns = NetworkSystem.getInstance();
	private Colors c = new Colors();
	
	private Button startButton = new Button(new Box(cs.getResX()/2, (float) (cs.getResY()*0.87), 260, 60, true, "Start"), "Start", 90, 26, true);

	private Button text2 = new Button(new Box(cs.getResX()/2, (float) (cs.getResY()*0.02), 0, 0, true, "oppText"), "Player 1 Paddle Colour", 280, 76, true);
	
	private Button text1 = new Button(new Box(cs.getResX()/2, (float) (cs.getResY()*0.28), 0, 0, true, "oppText"), "Player 2 Paddle Colour", 280, 52, true);
	
	private Button backdrop1 = new Button(new Box((float) (cs.getResX()*0.5), (float) (cs.getResY()*0.18), 554, 100, true, "backdrop1"), "", 0, 0, true);
	
	private Button highlight1 = new Button(new Box((float) (cs.getResX()*0.125), (float) (cs.getResY()*0.18), 84, 84, true, "highlight1"), "", 0, 0, true);
	
	private Button color1 = new Button(new Box((float) (cs.getResX()*0.125), (float) (cs.getResY()*0.18), 76, 76, true, "color1"), "", 0, 0, true);
	private Button color2 = new Button(new Box((float) (cs.getResX()*0.275), (float) (cs.getResY()*0.18), 76, 76, true, "color2"), "", 0, 0, true);
	private Button color3 = new Button(new Box((float) (cs.getResX()*0.425), (float) (cs.getResY()*0.18), 76, 76, true, "color3"), "", 0, 0, true);
	private Button color4 = new Button(new Box((float) (cs.getResX()*0.575), (float) (cs.getResY()*0.18), 76, 76, true, "color4"), "", 0, 0, true);
	private Button color5 = new Button(new Box((float) (cs.getResX()*0.725), (float) (cs.getResY()*0.18), 76, 76, true, "color5"), "", 0, 0, true);
	private Button color6 = new Button(new Box((float) (cs.getResX()*0.875), (float) (cs.getResY()*0.18), 76, 76, true, "color6"), "", 0, 0, true);
	
	private Button text3 = new Button(new Box(cs.getResX()/2, (float) (cs.getResY()*0.54), 0, 0, true, "oppText"), "Ball Colour", 146, 40, true);
	
	private Button backdrop2 = new Button(new Box((float) (cs.getResX()*0.5), (float) (cs.getResY()*0.67), 554, 100, true, "backdrop2"), "", 0, 0, true);
	
	private Button highlight2 = new Button(new Box((float) (cs.getResX()*0.125), (float) (cs.getResY()*0.67), 84, 84, true, "highlight2"), "", 0, 0, true);
	
	private Button bcolor1 = new Button(new Box((float) (cs.getResX()*0.125), (float) (cs.getResY()*0.67), 76, 76, true, "bcolor1"), "", 0, 0, true);
	private Button bcolor2 = new Button(new Box((float) (cs.getResX()*0.275), (float) (cs.getResY()*0.67), 76, 76, true, "bcolor2"), "", 0, 0, true);
	private Button bcolor3 = new Button(new Box((float) (cs.getResX()*0.425), (float) (cs.getResY()*0.67), 76, 76, true, "bcolor3"), "", 0, 0, true);
	private Button bcolor4 = new Button(new Box((float) (cs.getResX()*0.575), (float) (cs.getResY()*0.67), 76, 76, true, "bcolor4"), "", 0, 0, true);
	private Button bcolor5 = new Button(new Box((float) (cs.getResX()*0.725), (float) (cs.getResY()*0.67), 76, 76, true, "bcolor5"), "", 0, 0, true);
	private Button bcolor6 = new Button(new Box((float) (cs.getResX()*0.875), (float) (cs.getResY()*0.67), 76, 76, true, "bcolor6"), "", 0, 0, true);
	
	
	private Button backdrop3 = new Button(new Box((float) (cs.getResX()*0.5), (float) (cs.getResY()*0.42), 554, 100, true, "backdrop3"), "", 0, 0, true);
	
	private Button highlight3 = new Button(new Box((float) (cs.getResX()*0.125), (float) (cs.getResY()*0.42), 84, 84, true, "highlight3"), "", 0, 0, true);
	
	private Button ocolor1 = new Button(new Box((float) (cs.getResX()*0.125), (float) (cs.getResY()*0.42), 76, 76, true, "ocolor1"), "", 0, 0, true);
	private Button ocolor2 = new Button(new Box((float) (cs.getResX()*0.275), (float) (cs.getResY()*0.42), 76, 76, true, "ocolor2"), "", 0, 0, true);
	private Button ocolor3 = new Button(new Box((float) (cs.getResX()*0.425), (float) (cs.getResY()*0.42), 76, 76, true, "ocolor3"), "", 0, 0, true);
	private Button ocolor4 = new Button(new Box((float) (cs.getResX()*0.575), (float) (cs.getResY()*0.42), 76, 76, true, "ocolor4"), "", 0, 0, true);
	private Button ocolor5 = new Button(new Box((float) (cs.getResX()*0.725), (float) (cs.getResY()*0.42), 76, 76, true, "ocolor5"), "", 0, 0, true);
	private Button ocolor6 = new Button(new Box((float) (cs.getResX()*0.875), (float) (cs.getResY()*0.42), 76, 76, true, "ocolor6"), "", 0, 0, true);
	
	private Button back = new Button(new Box((float) (cs.getResX()*0.04), (float) (cs.getResY()*0.04), 50, 50, true, "back"), "<", 22, 28, true);
	
	private Button muteBtn = new Button(new Box((float) (cs.getResX()*0.15), (float) (cs.getResY()*0.87), 80, 60, true, "mute"), "Sound", 60, 16, true);
	private Button AIbtn = new Button(new Box((float) (cs.getResX()*0.85), (float) (cs.getResY()*0.87), 80, 60, true, "ai"), "AI", 34, 28, true);
	
	public PlayMenu(){
		
		startButton.setBoxColor(new Color(60,60,60));
		text2.setTextColor(new Color(30,30,30));
		text2.setTextSize(26);
		text1.setTextColor(new Color(30,30,30));
		text1.setTextSize(26);
		text3.setTextColor(new Color(30,30,30));
		text3.setTextSize(26);
		highlight1.setBoxColor(new Color(30,30,30));
		highlight2.setBoxColor(new Color(30,30,30));
		highlight3.setBoxColor(new Color(30,30,30));
		backdrop1.setBoxColor(new Color(200,200,200));
		backdrop2.setBoxColor(new Color(200,200,200));
		backdrop3.setBoxColor(new Color(200,200,200));

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
		
		muteBtn.setBoxColor(c.green);
		AIbtn.setBoxColor(c.green);
		muteBtn.setTextColor(c.darkGray);
		AIbtn.setTextColor(c.darkGray);
		muteBtn.setTextSize(20);
		AIbtn.setTextSize(36);
		
		if(gs.isP2IsAI()){
			ocolor1.setBoxColor(c.darkGray);
			ocolor2.setBoxColor(c.darkGray);
			ocolor3.setBoxColor(c.darkGray);
			ocolor4.setBoxColor(c.darkGray);
			ocolor5.setBoxColor(c.darkGray);
			ocolor6.setBoxColor(c.darkGray);
		}
		else{
			ocolor1.setBoxColor(c.red);
			ocolor2.setBoxColor(c.orange);
			ocolor3.setBoxColor(c.green);
			ocolor4.setBoxColor(c.blue);
			ocolor5.setBoxColor(c.purple);
			ocolor6.setBoxColor(c.pink);
		}

		back.setBoxColor(new Color(200,200,200));
		back.setTextColor(new Color(30,30,30));
		
	}
	private int length = 31;

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
		case 21: returnVar = backdrop3;
				break;
		case 22: returnVar = highlight3;
				break;
		case 23: returnVar = ocolor1;
				break;
		case 24: returnVar = ocolor2;
				break;
		case 25: returnVar = ocolor3;
				break;
		case 26: returnVar = ocolor4;
				break;
		case 27: returnVar = ocolor5;
				break;
		case 28: returnVar = ocolor6;
				break;	
		case 29: returnVar = muteBtn;
				break;
		case 30: returnVar = AIbtn;
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
	public Button getHighlight3() {
		return highlight3;
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
							return 1;
						}
					}// PADDLE COLOURS
					if(i == 6){ //red
						Box h1 = this.getHighlight1().getBox();
						h1.setCoordX(btn.getBox().getCoordX());
						gs.setP1Color(c.red);		
					}
					if(i == 7){ //orange
						Box h1 = this.getHighlight1().getBox();
						h1.setCoordX(btn.getBox().getCoordX());
						gs.setP1Color(c.orange);		
					}
					if(i == 8){ //green
						Box h1 = this.getHighlight1().getBox();
						h1.setCoordX(btn.getBox().getCoordX());
						gs.setP1Color(c.green);		
					}
					if(i == 9){ //blue
						Box h1 = this.getHighlight1().getBox();
						h1.setCoordX(btn.getBox().getCoordX());
						gs.setP1Color(c.blue);		
					}
					if(i == 10){ //purple
						Box h1 = this.getHighlight1().getBox();
						h1.setCoordX(btn.getBox().getCoordX());
						gs.setP1Color(c.purple);		
					}
					if(i == 11){ //pink
						Box h1 = this.getHighlight1().getBox();
						h1.setCoordX(btn.getBox().getCoordX());
						gs.setP1Color(c.pink);		
					}
					// BALL COLOURS
					if(i == 14){
						Box h2 = this.getHighlight2().getBox();
						h2.setCoordX(btn.getBox().getCoordX());
						gs.setBallColor(c.red);		
					}
					if(i == 15){
						Box h2 = this.getHighlight2().getBox();
						h2.setCoordX(btn.getBox().getCoordX());
						gs.setBallColor(c.orange);		
					}
					if(i == 16){
						Box h2 = this.getHighlight2().getBox();
						h2.setCoordX(btn.getBox().getCoordX());
						gs.setBallColor(c.green);		
					}
					if(i == 17){
						Box h2 = this.getHighlight2().getBox();
						h2.setCoordX(btn.getBox().getCoordX());
						gs.setBallColor(c.blue);		
					}
					if(i == 18){
						Box h2 = this.getHighlight2().getBox();
						h2.setCoordX(btn.getBox().getCoordX());
						gs.setBallColor(c.purple);		
					}
					if(i == 19){
						Box h2 = this.getHighlight2().getBox();
						h2.setCoordX(btn.getBox().getCoordX());
						gs.setBallColor(c.pink);		
					}
					if(i == 23){
						Box h3 = this.getHighlight3().getBox();
						h3.setCoordX(btn.getBox().getCoordX());
						gs.setP2Color(c.red);		
					}
					if(i == 24){
						Box h3 = this.getHighlight3().getBox();
						h3.setCoordX(btn.getBox().getCoordX());
						gs.setP2Color(c.orange);		
					}
					if(i == 25){
						Box h3 = this.getHighlight3().getBox();
						h3.setCoordX(btn.getBox().getCoordX());
						gs.setP2Color(c.green);		
					}
					if(i == 26){
						Box h3 = this.getHighlight3().getBox();
						h3.setCoordX(btn.getBox().getCoordX());
						gs.setP2Color(c.blue);		
					}
					if(i == 27){
						Box h3 = this.getHighlight3().getBox();
						h3.setCoordX(btn.getBox().getCoordX());
						gs.setP2Color(c.purple);		
					}
					if(i == 28){
						Box h3 = this.getHighlight3().getBox();
						h3.setCoordX(btn.getBox().getCoordX());
						gs.setP2Color(c.pink);		
					}
					if(i == 20){
						this.setVisible(false);
						Menus.setVisible(1);
						ns.setHostColor(c.white);	
						ns.setClientColor(c.white);
						ns.setBallColor(c.white);
						gs.setOnline(false);
						ns.setHost(false);
					}
					if(i == 29){
						if(cs.getMuted() == false){
							btn.setBoxColor(c.red);
							cs.setMuted(true);
						}
						else{
							btn.setBoxColor(c.green);
							cs.setMuted(false);
						}
					}
					if(i == 30){
						if(gs.isP2IsAI() == false){
							btn.setBoxColor(c.green);
							gs.setPlayers(true, false);
							gs.setP2IsAI(true);
						}
						else{
							btn.setBoxColor(c.red);
							gs.setPlayers(true, true);
							gs.setP2IsAI(false);
						}
						
					}
				}
			}
		}
		return 0;
	}
	
}

