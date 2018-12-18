package com.massey.id16107190.pong;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Game extends GameEngine {

	private GameState gs = GameState.getInstance();
	private ClientSettings cs = ClientSettings.getInstance();
	private List<Box> buttons = new ArrayList<>();
	private boolean paused = true;
	private boolean gameExited = false;
	private Box paddle1 = null;
	private Box paddle2 = null;
	private Ball ball = null;
	private boolean titleSet = false;
	
	
	// Player controls etc
	private int p1Move = 0; //-1 for down, +1 for up, 0 for nothing
	private int p2Move = 0;
	private int p1Height = 0; // total height for paddle (+/-)
	private int p2Height = 0;
	
	private int ballSpeed = gs.getBallInitSpeed(); // ball speed per tick
	private float ballVelX = 0f;
	private float ballVelY = 0f;
	private int turnRate = 24; //higher is less severe
	private float increaseRate = 0.3333333f;
	
	private PlayerController pc1 = new PlayerController(1);
	private PlayerController pc2 = new PlayerController(2);
	private boolean doOnceStart;
	private boolean newGame = true;
	private long scoreTime;
	
	public void init() {
		// Initialize Window Size
		setWindowSize(cs.getResX(), cs.getResY());
		gs.ResetPaddles();
		paddle1 = gs.GetPlayer1Paddle();
		paddle2 = gs.GetPlayer2Paddle();
		ball = gs.GetBall();
	}

	public static void start() {
		
	}
	
	public static void quit() {
		Menus m = new Menus();
		m.init();
		GameEngine.createGame(m, 144);
	}
	
	public void reset(){
		gs.ResetBall();
		gs.ResetPaddles();
		p1Height = 0;
		p2Height = 0;
		
		p1Move = 0;
		p2Move = 0;
		
		pc1.resetPC();
		pc2.resetPC();
		
		ballVelX = 0;
		ballVelY = 0;
		
		
		paused = true;
		doOnceStart = false;
		scoreTime = time;

	}

	@Override
	public void update(double dt) {
		if(titleSet == false){
			titleSet = true;
			mFrame.setTitle("Pong!");
		}
		
		if(paused == false){
			if(doOnceStart == false){
				doOnceStart = true;
				int launchSide = (Math.random() <= 0.5) ? 1 : 2;
				if(launchSide == 1){
					ballVelX = ballSpeed * -1;
				}
				else ballVelX = ballSpeed;
			}
			
		}
		
		ball.setPosX(ball.getPosX()+ballVelX);
		ball.setPosY(ball.getPosY()+ballVelY);
		
		float ballX = ball.getPosX();
		float ballY = ball.getPosY();
		if(ballX > cs.getResX()+(ball.getRadius()/2)){
			reset();
			System.out.println("SCORE RIGHT");
			gs.Score(1);
		}
		if(ballX < 0-(ball.getRadius()/2)){
			reset();
			System.out.println("SCORE LEFT");
			gs.Score(2);
		}
		
		if(ballY-ball.getRadius() <= 0){
			ballVelY *= -1;
		}
		if(ballY+ball.getRadius() >= cs.getResY()){
			ballVelY *= -1;
		}
		
		
		
		switch(p1Move){
			case 1:{
				if(p1Height *-1 >= (cs.getResY()/2 - paddle1.getHeight()/2)){ //check for paddle boundaries
					p1Height += 0;
				}
				else p1Height -= 6;
				break;
			}
			case -1:{
				if(p1Height *-1 <= (cs.getResY()/2 - paddle1.getHeight()/2)*-1){
					p1Height += 0;
				}
				else p1Height += 6;
				break;
			}
		}
		
		switch(p2Move){
			case 1:{
				if(p2Height *-1 >= (cs.getResY()/2 - paddle2.getHeight()/2)){ //check for paddle boundaries
					p2Height += 0;
				}
				else p2Height -= 6;				
				break;
			}
			case -1:{
				if(p2Height *-1 <= (cs.getResY()/2 - paddle2.getHeight()/2)*-1){
					p2Height += 0;
				}
				else p2Height += 6;
				break;
			}
		}
		
		
		if(ball.getPosX()-ball.getRadius()  < 0+paddle1.getWidth()){
			System.out.println("Within X p1");
			
			if(ball.getPosY()-ball.getRadius() <= p1Height+(cs.getResX()/2)+(paddle1.getHeight()/2) 
					&& ball.getPosY()+ball.getRadius()  >= p1Height+(cs.getResX()/2)-(paddle1.getHeight()/2)){
				
				ballVelX *= -1;
				float fromCenter = (p1Height+(cs.getResX()/2) - ball.getPosY()-(ball.getRadius()/2));
				System.out.println(fromCenter);
				ballVelY = (fromCenter/turnRate)*-1;
				
				ballVelX += increaseRate;
			}
		}
		
		if(ball.getPosX()+ball.getRadius() > cs.getResX()-paddle2.getWidth()){
			System.out.println("Within X p2");
			if(ball.getPosY()-ball.getRadius() <= p2Height+(cs.getResX()/2)+(paddle2.getHeight()/2) 
					&& ball.getPosY()+ball.getRadius()  >= p2Height+(cs.getResX()/2)-(paddle2.getHeight()/2)){
				
				ballVelX *= -1;
				float fromCenter = (p2Height+(cs.getResX()/2) - ball.getPosY()-(ball.getRadius()/2));
				System.out.println(fromCenter);
				ballVelY = (fromCenter/turnRate)*-1;
				
				ballVelX -= increaseRate;
			}
		}
		
		if(paused == true && newGame == false){
			if(scoreTime + cs.getWaitTime() <= time){
				System.out.println("1.5 SECONDS");
				paused = false;
			}
		}
		pc1.setPlayerPos(p1Height); //record height in controller for future ref.
		pc2.setPlayerPos(p2Height);

	}
	
	public void paintComponent() {
		// Clear the background to black
		changeBackgroundColor(black);
		clearBackground(cs.getResX(), cs.getResY());
		
		if(paused == true && newGame == false){
			changeColor(white);
			float pausedX = cs.getResX()/2 - (160/2);
			float pausedY = cs.getResY()/2 + 10;
			String pausedText = "Score!";
			drawBoldText(pausedX, pausedY, pausedText,"Arial", 50);
		}
		else{
			if(newGame == false){
				changeColor(white);
				drawSolidCircle(ball.getPosX(), ball.getPosY(), ball.getRadius());
			}
		}
		
		if(newGame == true){
			changeColor(white);
			float pausedX = cs.getResX()/2 - (270/2);
			float pausedY = cs.getResY()/2 + 10;
			String pausedText = "Press Start";
			drawBoldText(pausedX, pausedY, pausedText,"Arial", 50);
		}
		else{
			changeColor(new Color(200,200,200)); // grey color
			float scoreX = cs.getResX()/2 - (138/2);
			float scoreY = 50;
			String scoreText = String.valueOf(gs.GetScore(1))+ "  -  " + String.valueOf(gs.GetScore(2));
			drawBoldText(scoreX, scoreY, scoreText,"Arial", 50);
			
		}
		// Draw Board on the screen
		changeColor(white);
		drawSolidRectangle(paddle1.getHitbox().get(0), paddle1.getHitbox().get(1)+p1Height, paddle1.getHitbox().get(2), paddle1.getHitbox().get(3));
		drawSolidRectangle(paddle2.getHitbox().get(0), paddle2.getHitbox().get(1)+p2Height, paddle2.getHitbox().get(2), paddle2.getHitbox().get(3));
	}
	
	public void keyPressed(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.VK_ESCAPE){ // Exit the game
			if(gameExited == false){
				gameExited = true;
				mFrame.dispose();
				Game.quit();
			}
		}
		if(paused == true){
			if(event.getKeyCode() == KeyEvent.VK_ENTER){
				paused = false;
				newGame = false;
			}
		}
		if(paused == false){
			p1Move = pc1.evaluateKeyPress(event);
			p2Move = pc2.evaluateKeyPress(event);
		}		
	}
	
	public void keyReleased(KeyEvent event){	
		if(paused == false){
			p1Move = pc1.evaluateKeyRelease(event);
			p2Move = pc2.evaluateKeyRelease(event);
		}		
	}
	
	//System.out.println(event.getX() + " " + event.getY());
	public void mouseClicked(MouseEvent event) {
		if(event.getButton() == 1){ //if left click
			for(Box btn : buttons){ 
				if(btn.isEnabled()){	//for every button, if enabled do this:
					List<Float> hitbox = btn.getHitbox();
					if(event.getX() > hitbox.get(0) && event.getX() < (hitbox.get(0)+hitbox.get(2))){ // x coord check
						if(event.getY() > hitbox.get(1) && event.getY() < (hitbox.get(1)+hitbox.get(3))){ // y coord check
							
						}	
					}
				}
			}
		}		
	}

}
