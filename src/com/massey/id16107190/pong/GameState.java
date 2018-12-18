package com.massey.id16107190.pong;

public final class GameState {
	 
    private static GameState INSTANCE;
    private ClientSettings cs = ClientSettings.getInstance();
    
    private boolean p1IsPlayer = true;
    private boolean p2IsPlayer = true;
    
    private int player1Points = 0;
    private int player2Points = 0;
    
    private int paddleWidth = 20;
    private int paddleHeight = 100;
    
    private Box player1Paddle = new Box(0+(paddleWidth/2),cs.getResY()/2, paddleWidth,paddleHeight);
    private Box player2Paddle = new Box(cs.getResX()-(paddleWidth/2), cs.getResY()/2, paddleWidth, paddleHeight);
    
    private int ballSize = 10;
    private Ball ball = new Ball(cs.getResX()/2-(ballSize/2), cs.getResY()/2-(ballSize/2), ballSize);
	private int ballInitSpeed = 4;
     
    private GameState() {  

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
     
    public static GameState getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new GameState();
        }
         
        return INSTANCE;
    }

	public int getBallSize() {
		return ballSize;
	}

	public void setBallSize(int ballSize) {
		this.ballSize = ballSize;
	}

	public int getBallInitSpeed() {
		// TODO Auto-generated method stub
		return ballInitSpeed;
	}
}
