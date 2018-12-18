package com.massey.id16107190.pong;

import java.io.Serializable;

public class NetPacket implements Serializable {
	
	public Box paddle1;
	public Box paddle2;
	public Ball ball;
	public int state = 0;
	public int p1Score = 0;
	public int p2Score = 0;
	
	public NetPacket(Box p1, Box p2, Ball b, int state, int p1s, int p2s) {
		paddle1 = p1;
		paddle2 = p2;
		ball = b;
		this.state = state;
		p1Score = p1s;
		p2Score = p2s;
	}
}
