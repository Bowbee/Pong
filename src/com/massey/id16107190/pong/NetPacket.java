package com.massey.id16107190.pong;

import java.awt.Color;
import java.io.Serializable;

@SuppressWarnings("serial")
public class NetPacket implements Serializable {
	
	public Box paddle1;
	public Box paddle2;
	public Ball ball;
	public int state = 0;
	public int p1Score = 0;
	public int p2Score = 0;
	
	public Color p1Color;
	public Color p2Color;
	public Color ballColor;
	
	public NetPacket(Box p1, Box p2, Ball b, int state, int p1s, int p2s, Color pc1, Color bc) {
		paddle1 = p1;
		paddle2 = p2;
		ball = b;
		this.state = state;
		p1Score = p1s;
		p2Score = p2s;
		p1Color = pc1;
		ballColor = bc;
	}
}
