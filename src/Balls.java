import java.awt.Color;
import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Balls {

    public double lowerVelocity = 0.005;
	public double upperVelocity = 0.01;
    public int ballCount = 3;
	public double ballRadius = 0.05;

    public double[] ballPositionX = new double[ballCount];
	public double[] ballPositionY = new double[ballCount];
	public double[] ballVelocityX = new double[ballCount];
	public double[] ballVelocityY = new double[ballCount];
 
    
    // Creates a ball for every ball in ballCount and gives it a random position and velocity
    public void ballSpawn() {
        for(int i = 0; i < ballCount; i++) {
            ballPositionX[i] = Math.random();
            ballPositionY[i] = Math.random();
            ballVelocityX[i] = Math.random() * (upperVelocity - lowerVelocity) + lowerVelocity;
            ballVelocityY[i] = Math.random() * (upperVelocity - lowerVelocity) + lowerVelocity;
		}
    }




    // Called if there is a collision between the player and a ball. Resets the position and velocity of all the balls.
    public void collision() {
        ballCount = 3;
				for(int i = 0; i < ballCount; i++) {
					ballPositionX[i] = Math.random();
					ballPositionY[i] = Math.random();
					ballVelocityX[i] = Math.random() * (upperVelocity - lowerVelocity) + lowerVelocity;
					ballVelocityY[i] = Math.random() * (upperVelocity - lowerVelocity) + lowerVelocity;
                }
            }






    public void increaseDifficulty() {
        ballCount++;
        double[] ballXnew = new double[ballCount];
        double[] ballYnew = new double[ballCount];
        double[] ballXVnew = new double[ballCount];
        double[] ballYVnew = new double[ballCount];
        for(int i = 0; i < ballCount - 1; i++) {
            ballXnew[i] = ballPositionX[i];
            ballYnew[i] = ballPositionY[i];
            ballXVnew[i] = ballVelocityX[i];
            ballYVnew[i] = ballVelocityY[i];
        }
        ballXnew[ballCount - 1] = Math.random();
        ballYnew[ballCount - 1] = Math.random();
        ballXVnew[ballCount - 1] = Math.random() * (upperVelocity - lowerVelocity) + lowerVelocity;
        ballYVnew[ballCount - 1] = Math.random() * (upperVelocity - lowerVelocity) + lowerVelocity;
        ballPositionX = ballXnew;
        ballPositionY = ballYnew;
        ballVelocityX = ballXVnew;
        ballVelocityY = ballYVnew;
    }


        




















}