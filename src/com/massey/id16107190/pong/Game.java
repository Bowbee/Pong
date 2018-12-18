package com.massey.id16107190.pong;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game extends GameEngine {
	// Settings data
	private GameState gs = GameState.getInstance();
	private ClientSettings cs = ClientSettings.getInstance();
	private NetworkSystem ns = NetworkSystem.getInstance();
	// Session vars
	private Random random = new Random();
	private boolean paused = true;
	private boolean gameExited = false;
	private boolean titleSet = false;
	private boolean doOnceStart;
	private boolean newGame = true;
	private long scoreTime;
	// GameObjects
	private List<Box> buttons = new ArrayList<>();
	private Box paddle1 = null;
	private Box paddle2 = null;
	private Ball ball = null;
	// Player controls etc
	private PlayerController pc1 = new PlayerController(1);
	private PlayerController pc2 = new PlayerController(2);
	private int p1Move = 0; //-1 for down, +1 for up, 0 for nothing
	private int p2Move = 0;
	private int p1Height = 0; // total height for paddle (+/-)
	private int p2Height = 0;
	private float AIoffset = 0;
	// Ball variables
	private int ballSpeed = gs.getBallInitSpeed(); // ball speed per tick
	private float ballVelX = 0f;
	private float ballVelY = 0f;
	private int turnRate = 24; //higher is less severe
	private float increaseRate = 0.3333333f;
	
	
	
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
		// Paddle reset
		gs.ResetPaddles();
		pc1.resetPC();
		pc2.resetPC();
		p1Move = 0;
		p2Move = 0;
		p1Height = 0;
		p2Height = 0;
		// Ball reset
		gs.ResetBall();
		ballVelX = 0;
		ballVelY = 0;
		// Round reset + timer
		paused = true;
		doOnceStart = false;
		scoreTime = time;
	}

	@Override
	public void update(double dt) {
		if(titleSet == false){
			titleSet = true;
			mFrame.setTitle("Pong!");
			if(gs.isOnline()){
				ns.sendGameState(gs);
			}			
		}
		if(gs.isOnline()){
			if(ns.isHost()){
				ns.sendData(paddle1, paddle2, ball);
			}
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
		
		if(gs.isP1IsAI()){
			p1Move = makeAImove(1);
		}
		if(gs.isP2IsAI()){
			p2Move = makeAImove(2);
			System.out.println(p2Move);
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
		
		
		if(ball.getPosX()-ball.getRadius()  < 0+paddle1.getWidth()){ //ball hitbox X
			if(ball.getPosY()-ball.getRadius() <= p1Height+(cs.getResX()/2)+(paddle1.getHeight()/2) //ball hitbox Y
					&& ball.getPosY()+ball.getRadius()  >= p1Height+(cs.getResX()/2)-(paddle1.getHeight()/2)){
				// velocity X
				ballVelX *= -1;
				ballVelX += increaseRate;
				// velocity Y
				float fromCenter = (p1Height+(cs.getResX()/2) - ball.getPosY()-(ball.getRadius()/2));
				System.out.println(fromCenter);
				ballVelY = (fromCenter/turnRate)*-1;
				AIoffset = getAIOffset();
			}
		}
		
		if(ball.getPosX()+ball.getRadius() > cs.getResX()-paddle2.getWidth()){ //ball hitbox X
			if(ball.getPosY()-ball.getRadius() <= p2Height+(cs.getResX()/2)+(paddle2.getHeight()/2) //ball hitbox Y
					&& ball.getPosY()+ball.getRadius()  >= p2Height+(cs.getResX()/2)-(paddle2.getHeight()/2)){
				// velocity X
				ballVelX *= -1;
				ballVelX -= increaseRate;
				// velocity Y
				float fromCenter = (p2Height+(cs.getResX()/2) - ball.getPosY()-(ball.getRadius()/2));
				System.out.println(fromCenter);
				ballVelY = (fromCenter/turnRate)*-1;
				AIoffset = getAIOffset();
			}
		}
		// newGame timer
		if(paused == true && newGame == false){
			if(scoreTime + cs.getWaitTime() <= time){
				System.out.println("1.5 SECONDS");
				paused = false;
			}
		}
		
		pc1.setPlayerPos(p1Height); //record height in player controller for future ref (networking?).
		pc2.setPlayerPos(p2Height);
		paddle1.setCoordY((cs.getResY()/2) + p1Height);
		paddle2.setCoordY((cs.getResY()/2) + p2Height);

	}
	
	private float getAIOffset(){
		float height = paddle1.getHeight();
		float offset = random.nextInt(Math.round(height + 1 + height)) - height;
		return offset/2;
	}
	
	private int makeAImove(int i) {
		
		int ballHeight = (int) (ball.getPosY()+(ball.getRadius()/2)-(cs.getResY()/2));
		if(i == 1){
			if(p1Height + AIoffset < ballHeight){
				//DOWN
				return -1;
			}
			if(p1Height - AIoffset > ballHeight){
				//UP
				return 1;
			}
			else{
				return 0;
			}
		}
		if(i == 2){
			
			if(p2Height +5 + AIoffset < ballHeight){
				//DOWN
				return -1;
			}
			if(p2Height -5 + AIoffset> ballHeight){
				//UP
				return 1;
			}
			else{
				return 0;
			}
			
		}
		else{
			return 0;
		}
	}

	public void paintComponent() {
		// Clear the background to black
		changeBackgroundColor(black);
		clearBackground(cs.getResX(), cs.getResY());
		
		if(paused == true && newGame == false){ // Score text
			changeColor(white);
			float pausedX = cs.getResX()/2 - (160/2);
			float pausedY = cs.getResY()/2 + 10;
			String pausedText = "Score!";
			drawBoldText(pausedX, pausedY, pausedText,"Arial", 50);
		}
		else{
			if(newGame == false){ // Draw ball
				changeColor(white);
				drawSolidCircle(ball.getPosX(), ball.getPosY(), ball.getRadius());
			}
		}
		
		if(newGame == true){ // New Game (do once)
			changeColor(white);
			float pausedX = cs.getResX()/2 - (270/2);
			float pausedY = cs.getResY()/2 + 10;
			String pausedText = "Press Start";
			drawBoldText(pausedX, pausedY, pausedText,"Arial", 50);
		}
		else{ // central drawing
			changeColor(new Color(200,200,200)); // grey color
			float scoreX = cs.getResX()/2 - (138/2);
			float scoreY = 50;
			String scoreText = String.valueOf(gs.GetScore(1))+ "  -  " + String.valueOf(gs.GetScore(2));
			drawBoldText(scoreX, scoreY, scoreText,"Arial", 50);
			
		}
		// Draw gameObjects (excluding ball)
		changeColor(white);
		drawSolidRectangle(paddle1.getHitbox().get(0), paddle1.getCoordY()-(paddle1.getHeight()/2), paddle1.getHitbox().get(2), paddle1.getHitbox().get(3));
		drawSolidRectangle(paddle2.getHitbox().get(0), paddle2.getCoordY()-(paddle2.getHeight()/2), paddle2.getHitbox().get(2), paddle2.getHitbox().get(3));
	}
	
	public void keyPressed(KeyEvent event) {
		if(gs.isOnline()){
			if(event.getKeyCode() == KeyEvent.VK_ESCAPE){ // Exit the game
				if(gameExited == false){
					ns.sendClose();
					gameExited = true;
					mFrame.dispose();
					Game.quit();
				}
			}
			if(paused == true){
				if(event.getKeyCode() == KeyEvent.VK_ENTER){ // Start the game
					ns.setReady(true);
					if(ns.isHost()){
						if(ns.isReady() == true){
							paused = false;
							newGame = false;
							System.out.println("Ready!");
						}
					}
					
					if(gs.isP1IsAI() || gs.isP2IsAI()){
						AIoffset = getAIOffset();
					}
				}	
			}
			if(paused == false){
				if(ns.getPlayerIndex() == 1){
					p1Move = pc1.evaluateKeyPress(event); // Player movement
				}
				if(ns.getPlayerIndex() == 2){
					p2Move = pc2.evaluateKeyPress(event);
				}
			}
		}
		
		else{
			if(event.getKeyCode() == KeyEvent.VK_ESCAPE){ // Exit the game
				if(gameExited == false){
					gameExited = true;
					mFrame.dispose();
					Game.quit();
				}
			}
			if(paused == true){
				if(event.getKeyCode() == KeyEvent.VK_ENTER){ // Start the game
					paused = false;
					newGame = false;
					if(gs.isP1IsAI() || gs.isP2IsAI()){
						AIoffset = getAIOffset();
					}
				}
			}
			if(paused == false){
				if(gs.isP1IsPlayer()){
					p1Move = pc1.evaluateKeyPress(event); // Player movement
				}
				if(gs.isP2IsPlayer()){
					p2Move = pc2.evaluateKeyPress(event);
				}
			}
		}

	}
	
	public void keyReleased(KeyEvent event){	
		if(paused == false){
			if(gs.isP1IsPlayer()){
				p1Move = pc1.evaluateKeyRelease(event); // Player movement key-release
			}
			if(gs.isP2IsPlayer()){
				p2Move = pc2.evaluateKeyRelease(event);
			}
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
