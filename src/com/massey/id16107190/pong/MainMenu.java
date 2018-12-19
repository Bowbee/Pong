package com.massey.id16107190.pong;

public class MainMenu{
	
	private ClientSettings cs = ClientSettings.getInstance();

	private Button playButton = new Button(new Box(cs.getResX()/2, (float) (cs.getResY()*0.25), 200, 80, true, "Play"), "Play", 80, 24, true);
	private Button clientButton = new Button(new Box(cs.getResX()/2, (float) (cs.getResY()*0.50), 200, 80, true, "Client"), "Connect", 156, 24, true);
	private Button hostButton = new Button(new Box(cs.getResX()/2, (float) (cs.getResY()*0.75), 200, 80, true, "Host"), "Host", 80, 24, true);
	
	private int length = 3;

	private boolean visible = true;

	public Button get(int i) {
		Button returnVar = null;
		switch (i){
		case 0: returnVar = playButton;
				break;
		case 1: returnVar = clientButton;
				break;
		case 2: returnVar = hostButton;
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
