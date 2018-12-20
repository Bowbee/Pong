package com.massey.id16107190.pong;

import java.awt.Color;
import java.io.Serializable;

@SuppressWarnings("serial")
public class ClientPacket implements Serializable {
	
	public Box paddle2;
	public Color p2Color;
	
	public ClientPacket(Box p2, Color pc2){
		paddle2 = p2;
		p2Color = pc2;
		
	}
	
}
