package org.usfirst.frc.team294.robot.commands.autoMode;



import org.usfirst.frc.team294.robot.Robot;
import org.usfirst.frc.team294.robot.subsystems.Drivetrain;
import org.usfirst.frc.team294.robot.util.Constants;

/**
 * Navigator.java
 * This controls the positioning system and collects acceleration info.
 * @author Tom Bottiglieri
 * @author Nate Schickler
 */
public class Navigator extends edu.wpi.first.wpilibj.command.Subsystem implements Loopable {

	double x = 0, y = 0, heading = 0;
	double lastL, lastR;
	double gyroReference = 0;
	Drivetrain drive;
	public Navigator(Drivetrain drive) {
		super("Navigator");
		this.drive = drive;
	}

	public void resetWithReferenceHeading(double reference) {
		x = y = heading = 0;
		gyroReference = reference;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getHeading() {
		return heading;
	}

	private double degreesToRadians(double deg) {
		return (deg * Math.PI) / 180.0;
	}

	public void update(double left, double right, double gyroAngle) {
		double diffL = left - lastL;
		double diffR = right - lastR;
		lastL = left;
		lastR = right;
		heading = gyroAngle - gyroReference;
		double magnitude = (diffL + diffR) / 2.0;
		x += magnitude * Math.sin(degreesToRadians(heading));
		y += magnitude * Math.cos(degreesToRadians(heading));
	}

	public double getCentripetalAccel(){//in ft/sec^2
		//using the max speed, convert to g and get centripetal accel on 
		//the perimeter of the robot(using the radius of drivetrain in ft), then return
		double [] powers=Robot.drivetrain.getDrivePower();
		double centripetalAccel = Math.pow((Math.abs(powers[0]-powers[1])*Constants.MAX_SPEED),2)/(Constants.DRIVETRAIN_RADIUS/12);
		return centripetalAccel;
	}

	public double getLinearAccel(){//in ft/sec^2
		//finds the magnitude of acceleration in the x and y planes in g and then converts to ft/sec^2 and returns
		double linearAccel = Math.sqrt(Math.pow(
				((double)(Robot.drivetrain.getImu().getWorldLinearAccelX()+Robot.drivetrain.getImu().getWorldLinearAccelY())+
						(Robot.drivetrain.getImu().getWorldLinearAccelX()+Robot.drivetrain.getImu().getWorldLinearAccelY())), 2))*32.1740;
		return linearAccel;

	}

	public String toString() {
		return "X: " + x + " Y: " + y + " Heading: " + heading;
	}

	public void update() {
		this.update(Robot.drivetrain.getLeftEncPos(), Robot.drivetrain.getRightEncPos(), Robot.drivetrain.getImu().getCompassHeading());
	}

	@Override
	protected void initDefaultCommand() {

	}
}
