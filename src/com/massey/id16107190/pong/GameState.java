package com.massey.id16107190.pong;

public final class GameState {
	 
    private static GameState INSTANCE;
    private ClientSettings cs = ClientSettings.getInstance();
    
    private boolean online = false;
    
    private boolean p1IsPlayer = false;
    private boolean p1IsAI = false;

    private boolean p2IsPlayer = false;
    private boolean p2IsAI = false;
    
    private int player1Points = 0;
    private int player2Points = 0;
    
    private int paddleWidth = 20;
    private int paddleHeight = 150;
    
    private Box player1Paddle = new Box(0+(paddleWidth/2),cs.getResY()/2, paddleWidth,paddleHeight);
    private Box player2Paddle = new Box(cs.getResX()-(paddleWidth/2), cs.getResY()/2, paddleWidth, paddleHeight);
    
    private int ballSize = 10;
    private Ball ball = new Ball(cs.getResX()/2-(ballSize/2), cs.getResY()/2-(ballSize/2), ballSize);
	private int ballInitSpeed = 3;
     
    private GameState() {  

    }
    
    public static GameState getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new GameState();
        }
         
        return INSTANCE;
    }
    
    public void setPlayers(boolean p1, boolean p2){
    	if(p1 == true){
    		p1IsPlayer = true;
    		p1IsAI = false;
    	}
    	if(p1 == false){
    		p1IsAI = true;
    		p1IsPlayer = false;
    	}
    	if(p2 == true){
    		p2IsPlayer = true;
    		p2IsAI = false;
    	}
    	if(p2 == false){
    		p2IsAI = true;
    		p2IsPlayer = false;
    	}
    }
    
    public Box GetPlayer1Paddle(){
    	return player1Paddle;
    }
    public Box GetPlayer2Paddle(){
    	return player2Paddle;
    }
    public Ball GetBall(){
    	return ball;
    }
    
    public void ResetBall(){
    	ball.setPosX(cs.getResX()/2-(ballSize/2));
    	ball.setPosY(cs.getResY()/2-(ballSize/2));
    }
    
    public void ResetPaddles(){
        player1Paddle = new Box(0+(paddleWidth/2),cs.getResY()/2, paddleWidth,paddleHeight);
        player2Paddle = new Box(cs.getResX()-(paddleWidth/2), cs.getResY()/2, paddleWidth, paddleHeight);
    }
    public void ResetScore(){
    	player1Points = 0;
    	player2Points = 0;
    }
    
    public void Score(int pIndex){
    	if(pIndex == 1){
    		player1Points += 1;
    	}
    	if(pIndex == 2){
    		player2Points += 1;
    	}
    }
    public int GetScore(int pIndex){
    	if(pIndex == 1){
    		return player1Points;
    	}
    	else{
    		return player2Points;
    	}
    }

	public int getBallSize() {
		return ballSize;
	}

	public void setBallSize(int ballSize) {
		this.ballSize = ballSize;
	}

	public int getBallInitSpeed() {
		return ballInitSpeed;
	}

	public boolean isP1IsPlayer() {
		return p1IsPlayer;
	}

	public void setP1IsPlayer(boolean p1IsPlayer) {
		this.p1IsPlayer = p1IsPlayer;
		this.p1IsAI = false;
	}

	public boolean isP1IsAI() {
		return p1IsAI;
	}

	public void setP1IsAI(boolean p1IsAI) {
		this.p1IsAI = p1IsAI;
		this.p1IsPlayer = !p1IsAI;
	}

	public boolean isP2IsPlayer() {
		return p2IsPlayer;
	}

	public void setP2IsPlayer(boolean p2IsPlayer) {
		this.p2IsPlayer = p2IsPlayer;
		this.p2IsAI = !p2IsPlayer;
	}

	public boolean isP2IsAI() {
		return p2IsAI;
	}

	public void setP2IsAI(boolean p2IsAI) {
		this.p2IsAI = p2IsAI;
		this.p2IsPlayer = !p2IsAI;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}
}
