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
	private boolean soundCD;
	private long soundTime;
	
	private AudioClip beepSound = loadAudio("beep.wav");
	private AudioClip scoreSound = loadAudio("score.wav");
	
	
	public void init() {
		// Initialize Window Size
		setWindowSize(cs.getResX(), cs.getResY());
		gs.ResetPaddles();
		paddle1 = gs.GetPlayer1Paddle();
		paddle2 = gs.GetPlayer2Paddle();
		ball = gs.GetBall();
		if(gs.isOnline()){
			ns.Connect();
			System.out.println("NS connect");
		}
		
		
	}

	public static void start() {
		
	}
	
	public static void quit() {
		Menus m = new Menus();
		m.init();
		GameEngine.createGame(m, 144);
	}
	
	public void onlineStart(){
		paused = true;
		scoreTime = time;
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
				if(ns.isClient()){
					mFrame.setTitle("Pong Client! ("+ns.getHostIP()+":"+ns.getPort()+")");
				}
				if(ns.isHost()){
					mFrame.setTitle("Pong Host! (0.0.0.0:"+ns.getPort()+")");
				}
				onlineStart();
			}			
		}
		
		if(gs.isOnline()){
			if(ns.isHost()){
				ns.sendData(paddle1, paddle2, ball);
			}
			if(ns.isClient()){
				paddle1 = gs.getNetPaddle1();
				paddle2 = gs.getNetPaddle2();
				ball = gs.getNetBall();
				gs.setPlayer1Points(gs.getNetPoints1());
				gs.setPlayer2Points(gs.getNetPoints2());
				
				if(gs.isNetReady() == 1){
					paused = false;
					newGame = false;
				}
				if(gs.isNetReady() == 0){
					paused = true;
					newGame = true;
				}
				if(gs.isNetReady() == 2){
					paused = true;
					newGame = false;
					reset();
				}
			}
		}
		
		if(paused == false){
			if(doOnceStart == false){
				doOnceStart = true;
				playAudio(scoreSound, -1000);
				playAudio(beepSound, -1000);
				int launchSide = (Math.random() <= 0.5) ? 1 : 2;
				if(launchSide == 1){
					ballVelX = ballSpeed * -1;
				}
				else ballVelX = ballSpeed;
			}
		}
		
		ball.setPosX((float) (ball.getPosX()+(ballVelX*dt*60)));
		ball.setPosY((float) (ball.getPosY()+(ballVelY*dt*60)));
		
		float ballX = ball.getPosX();
		float ballY = ball.getPosY();
		if(ballX > cs.getResX()+(ball.getRadius()/2)){
			reset();
			System.out.println("SCORE RIGHT");
			gs.Score(1);
			playScore();
		}
		if(ballX < 0-(ball.getRadius()/2)){
			reset();
			System.out.println("SCORE LEFT");
			gs.Score(2);
			playScore();
		}
		
		if(ballY-ball.getRadius() <= 0){
			ball.setPosY(0+ball.getRadius());
			ballVelY *= -1;
			playHit();
		}
		if(ballY+ball.getRadius() >= cs.getResY()){
			ball.setPosY(cs.getResY()-ball.getRadius());
			ballVelY *= -1;
			playHit();
		}
		
		if(gs.isP1IsAI()){
			p1Move = makeAImove(1);
		}
		if(gs.isP2IsAI()){
			p2Move = makeAImove(2);
			//System.out.println(p2Move);
		}
		//System.out.println(gs.isP2IsAI());
		
		switch(p1Move){
			case 1:{
				if(p1Height *-1 >= (cs.getResY()/2 - paddle1.getHeight()/2)){ //check for paddle boundaries
					p1Height += 0;
				}
				else p1Height -= (gs.getPaddleSpeed()*dt*60);
				break;
			}
			case -1:{
				if(p1Height *-1 <= (cs.getResY()/2 - paddle1.getHeight()/2)*-1){
					p1Height += 0;
				}
				else p1Height += (gs.getPaddleSpeed()*dt*60);
				break;
			}
		}
		
		
		
		switch(p2Move){
			case 1:{
				if(p2Height *-1 >= (cs.getResY()/2 - paddle2.getHeight()/2)){ //check for paddle boundaries
					p2Height += 0;
				}
				else p2Height -= (gs.getPaddleSpeed()*dt*60);				
				break;
			}
			case -1:{
				if(p2Height *-1 <= (cs.getResY()/2 - paddle2.getHeight()/2)*-1){
					p2Height += 0;
				}
				else p2Height += (gs.getPaddleSpeed()*dt*60);
				break;
			}
		}
		
		
		if(ball.getPosX()-ball.getRadius()  < 0+paddle1.getWidth()){ //ball hitbox X
			if(ball.getPosY()-ball.getRadius() <= p1Height+(cs.getResX()/2)+(paddle1.getHeight()/2) //ball hitbox Y
					&& ball.getPosY()+ball.getRadius()  >= p1Height+(cs.getResX()/2)-(paddle1.getHeight()/2)){
				// velocity X
				ball.setPosX(0+paddle1.getWidth()+ball.getRadius());
				ballVelX *= -1;
				ballVelX += increaseRate;
				// velocity Y
				float fromCenter = (p1Height+(cs.getResX()/2) - ball.getPosY()-(ball.getRadius()/2));
				
				ballVelY = (fromCenter/turnRate)*-1;
				AIoffset = getAIOffset();
				playHit();
			}
		}
		
		if(ball.getPosX()+ball.getRadius() > cs.getResX()-paddle2.getWidth()){ //ball hitbox X
			if(ball.getPosY()-ball.getRadius() <= p2Height+(cs.getResX()/2)+(paddle2.getHeight()/2) //ball hitbox Y
					&& ball.getPosY()+ball.getRadius()  >= p2Height+(cs.getResX()/2)-(paddle2.getHeight()/2)){
				// velocity X
				ball.setPosX(cs.getResX()-paddle2.getWidth()-ball.getRadius());
				ballVelX *= -1;
				ballVelX -= increaseRate;
				// velocity Y
				float fromCenter = (p2Height+(cs.getResX()/2) - ball.getPosY()-(ball.getRadius()/2));
				
				ballVelY = (fromCenter/turnRate)*-1;
				AIoffset = getAIOffset();
				playHit();
			}
		}
		// newGame timer
		if(paused == true && newGame == false){
			if(scoreTime + cs.getWaitTime() <= time){
				paused = false;
			}
		}
		if(gs.isOnline()){ // instead of waiting for two players to be ready
			if(paused == true && newGame == true && scoreTime + cs.getWaitTime() <= time){
				paused = false;
				newGame = false;
			}
		}
		// prevent sound glitching by playing clip many times in a short period
		if(soundCD == true){
			if(soundTime + 100 <= time){
				soundCD = false;
			}
		}
		
		if(ns.getPlayerIndex() == 1){
			paddle1.setCoordY((cs.getResY()/2) + p1Height);
		}
		if(ns.getPlayerIndex() == 2){
			paddle2.setCoordY((cs.getResY()/2) + p2Height);
		}
		if(gs.isOnline() == false){
			paddle1.setCoordY((cs.getResY()/2) + p1Height);
			paddle2.setCoordY((cs.getResY()/2) + p2Height);
		}
		if(ns.isHost()){
			gs.SetPlayer1Paddle(paddle1);
			paddle2 = gs.GetPlayer2Paddle();
			p2Height = paddle2.getpHeight();
		}
		
		if(ns.isClient()){
			gs.setPlayer2Paddle(paddle2);
			paddle2.setpHeight(p2Height);
		}
		
		
		if(paused == false && newGame == false){
			if(ns.isHost()){
				ns.sendNetPacket(new NetPacket(paddle1, paddle2, ball, 1, gs.GetScore(1), gs.GetScore(2), gs.getP1Color(), gs.getBallColor()));
			}
		}
		if(paused == true && newGame == false){
			if(ns.isHost()){
				ns.sendNetPacket(new NetPacket(paddle1, paddle2, ball, 2, gs.GetScore(1), gs.GetScore(2), gs.getP1Color(), gs.getBallColor()));
			}
		}
		if(paused == true && newGame == true){
			if(ns.isHost()){
				ns.sendNetPacket(new NetPacket(paddle1, paddle2, ball, 0, gs.GetScore(1), gs.GetScore(2), gs.getP1Color(), gs.getBallColor()));
			}
		}
	}
	
	private void playHit() {
		if(soundCD == false && cs.getMuted() == false){
			playAudio(beepSound, -10);
			soundCooldown();
		}
	}

	private void playScore() {
		if(soundCD == false && cs.getMuted() == false){
			playAudio(scoreSound, -10);
			soundCooldown();
		}
	}
	
	private void soundCooldown(){
		soundCD = true;
		soundTime = time;
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
				changeColor(gs.getBallColor());
				drawSolidCircle(ball.getPosX(), ball.getPosY(), ball.getRadius());
			}
		}

		if(newGame == true){ // New Game (do once)
			String pausedText;
			int size = 200;
			if(gs.isOnline() == false){
				pausedText = "Press Enter";
				size = 264;
			}
			else{
				pausedText = "Waiting...";
				size = 200;
			}
			changeColor(white);
			float pausedX = cs.getResX()/2 - (size/2);
			float pausedY = cs.getResY()/2 + 10;
			
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
		changeColor(gs.getP1Color());
		drawSolidRectangle(paddle1.getHitbox().get(0), paddle1.getCoordY()-(paddle1.getHeight()/2), paddle1.getHitbox().get(2), paddle1.getHitbox().get(3));
		changeColor(gs.getP2Color());
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
