package com.massey.id16107190.pong;

public class main {
	
	private static ClientSettings cs = ClientSettings.getInstance();

	public static void main(String[] args) {
		
		Menus m = new Menus();
		m.init();
		GameEngine.createGame(m, cs.getFPS());
		
	}

}
