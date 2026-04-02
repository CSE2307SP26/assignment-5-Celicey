//*
// To Walk you through what I have been doing in steps, the first thing I did
// was try to determine what the variables were, renaming them as I went */









import java.awt.Color;
import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Game {
	public static void main(String[] args) {
		Balls redBalls = new Balls();
		
		int score = 0;
		int highScore = 0;
		double positionX = 0.5;
		double positionY = 0.5;
		double playerSpeed = 0.01;

		
		redBalls.ballSpawn();


		StdDraw.enableDoubleBuffering();
		
		long scoretime = System.currentTimeMillis();
		long difficultyIncrementTime = System.currentTimeMillis();
		
		while (true) {
			
			StdDraw.clear();
			boolean collision = false;
			for(int i = 0; i < redBalls.ballCount; i++) {
				
			
				redBalls.ballPositionX[i] = redBalls.ballPositionX[i] + redBalls.ballVelocityX[i];
				redBalls.ballPositionY[i] = redBalls.ballPositionY[i] + redBalls.ballVelocityY[i];
				if(redBalls.ballPositionX[i] + redBalls.ballRadius > 1 || redBalls.ballPositionX[i] - redBalls.ballRadius < 0) { 
					redBalls.ballVelocityX[i] = -redBalls.ballVelocityX[i];
				}
				if(redBalls.ballPositionY[i] + redBalls.ballRadius > 1 || redBalls.ballPositionY[i] - redBalls.ballRadius < 0) { 
					redBalls.ballVelocityY[i] = -redBalls.ballVelocityY[i];
				}
				for(int j = 0; j < redBalls.ballCount; j++) {
					if(i != j) {
						double d = Math.sqrt(Math.pow(redBalls.ballPositionX[i] - redBalls.ballPositionX[j], 2) + Math.pow(redBalls.ballPositionY[i] - redBalls.ballPositionY[j], 2));
						if(d < 2 * redBalls.ballRadius) {
							redBalls.ballVelocityX[i] = -redBalls.ballVelocityX[i];
							redBalls.ballVelocityY[i] = -redBalls.ballVelocityY[i];
						}
					}
				}
				
				double d = Math.sqrt(Math.pow(redBalls.ballPositionX[i] - positionX, 2) + Math.pow(redBalls.ballPositionY[i] - positionY, 2));
				if(d < 2 * redBalls.ballRadius) {
					collision = true;
				}
			}
			
			if(collision) {
				redBalls.collision();

				score = 0;
				scoretime = System.currentTimeMillis();
				difficultyIncrementTime = System.currentTimeMillis();
				positionX = 0.5;
				positionY = 0.5;
				}			
				
				
			
			
			if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
				positionY = positionY + playerSpeed;
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {
				positionY = positionY - playerSpeed;
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_A)) {
				positionX = positionX - playerSpeed;
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_D)) {
				positionX = positionX + playerSpeed;
			}
			
			if(positionX > 1) {
				positionX = 1;
			}
			if(positionX < 0) {
				positionX = 0;
			}
			if(positionY > 1) {
				positionY = 1;
			}
			if(positionY < 0) {
				positionY = 0;
			}
			
			long now = System.currentTimeMillis();
			if(now > scoretime + 1000) {
				score++;
				if(score > highScore) {
					highScore = score;
				}
				scoretime = now;
			}
			
			if(now > difficultyIncrementTime + 10000) {
				redBalls.increaseDifficulty();
				difficultyIncrementTime = now;
			}
			StdDraw.setPenColor(Color.red);
			for(int i = 0; i < redBalls.ballCount; i++) {
				StdDraw.filledCircle(redBalls.ballPositionX[i], redBalls.ballPositionY[i], redBalls.ballRadius);
			}
			
			StdDraw.setPenColor(Color.black);
			StdDraw.filledCircle(positionX, positionY, redBalls.ballRadius);
			StdDraw.text(0.5, 0.1, "Score: " + score + " High Score: " + highScore);
			
			StdDraw.show();
			StdDraw.pause(10);
			
		}
	}
}
