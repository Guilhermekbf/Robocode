package Tapajós;
import robocode.*;
import java.awt.Color;


// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * Tapajós - a robot by (your name here)
 */
public class Tapajós extends AdvancedRobot
{
	/**
	 * run: Tapajós's default behavior
	 */
	public void run() {
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar
		
        setBodyColor(Color.blue);      // Body => corpo
		setGunColor(Color.green);      // Gun => Arma
		setRadarColor(Color.blue);     // Radar => Radar
		
		setScanColor(Color.green);     // Sacn => varredura
		setBulletColor(Color.blue);    // Bullet => Bala  
		// Robot main loop
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			ahead(110);
			turnRight(90);
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		fire(2);
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		back(50);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		back(50);
	}	
}
