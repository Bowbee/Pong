package com.massey.id16107190.pong;

import java.awt.Color;

public class MainMenu{
	
	private ClientSettings cs = ClientSettings.getInstance();
	private Colors c = new Colors();

	private Button playButton = new Button(new Box(cs.getResX()/2, (float) (cs.getResY()*0.4), 300, 100, true, "Play"), "Play", 80, 24, true);
	private Button clientButton = new Button(new Box(cs.getResX()/2, (float) (cs.getResY()*0.6), 300, 100, true, "Client"), "Connect", 156, 24, true);
	private Button hostButton = new Button(new Box(cs.getResX()/2, (float) (cs.getResY()*0.8), 300, 100, true, "Host"), "Host", 80, 24, true);
	private Button text1 = new Button(new Box(cs.getResX()/2, cs.getResY()*0.2f, 300, 100, true, "text1"), "PONG!", 600, 80, true);
	
	private int length = 4;

	private boolean visible = true;
	
	public MainMenu(){
		playButton.setBoxColor(c.lightGray);
		playButton.setTextColor(c.darkGray);
		clientButton.setBoxColor(c.lightGray);
		clientButton.setTextColor(c.darkGray);
		hostButton.setBoxColor(c.lightGray);
		hostButton.setTextColor(c.darkGray);
		text1.setTextSize(204);
		text1.setBoxColor(new Color(240,240,240));
		text1.setTextColor(new Color(226,226,226));
	}

	public Button get(int i) {
		Button returnVar = null;
		switch (i){
		case 0: returnVar = playButton;
				break;
		case 1: returnVar = clientButton;
				break;
		case 2: returnVar = hostButton;
				break;
		case 3: returnVar = text1;
				break;
		}
		return returnVar;
	}

	public int length() {
		return length;
	}

	public boolean enabled(int i) {
		switch (i){
		case 0: return playButton.getEnabled();
		case 1: return clientButton.getEnabled();
		case 2: return hostButton.getEnabled();
		case 3: return text1.getEnabled();
		}
		return false;
	}
	public boolean isMenuVisible(){
		return visible;
	}
	public void setVisible(boolean b){
		visible = b;
	}

}
