package com.massey.id16107190.pong;

public class HostMenu{
	
	private ClientSettings cs = ClientSettings.getInstance();
	
	private Button startButton = new Button(new Box(cs.getResX()/2, (float) (cs.getResY()*0.75), 200, 80, true, "Start"), "Start!", 100, 20, true);
	
	private int length = 1;

	private boolean visible = false;

	public Button get(int i) {
		Button returnVar = null;
		switch (i){
		case 0: returnVar = startButton;
				break;
		}
		return returnVar;
	}

	public int length() {
		return length;
	}

	public boolean enabled(int i) {
		switch (i){
		case 0: return startButton.getEnabled();
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

