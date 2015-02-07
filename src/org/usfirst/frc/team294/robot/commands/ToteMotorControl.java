package org.usfirst.frc.team294.robot.commands;

import org.usfirst.frc.team294.robot.Robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToteMotorControl extends Command {

	/**
	 * 
	 * @author team
	 * actions designated auto will terminate upon reaching preset limits
	 */
	public static enum ToteMotorAction{
		OPEN,
		CLOSE,
		PAN_LEFT,
		PAN_RIGHT,
		CENTER,
		AUTO_OPEN,
		AUTO_CLOSE,
		STOP
	}
	
	private int autoAbsOpenDist=400;
	private int autoAbsCloseDist=300;
	private double tolerance=10;
	ToteMotorAction action;
	private int relativeDistance;

	public ToteMotorControl(ToteMotorAction a) {
		requires(Robot.toteGrab);
		this.action=a;

		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}



	// Called just before this Command runs the first time
	protected void initialize() {
		switch(this.action){
		case OPEN:

		case CLOSE:
			
		case PAN_LEFT:
			relativeDistance=Math.abs(Robot.toteGrab.getLeftMotor().getAnalogInPosition()-Robot.toteGrab.getRightMotor().getAnalogInPosition());
		case PAN_RIGHT:
			relativeDistance=Math.abs(Robot.toteGrab.getLeftMotor().getAnalogInPosition()-Robot.toteGrab.getRightMotor().getAnalogInPosition());
		case CENTER:

		case STOP:
			this.end();
		default:
			this.end();
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		int checkDistance=Math.abs(Robot.toteGrab.getLeftMotor().getAnalogInPosition()-Robot.toteGrab.getRightMotor().getAnalogInPosition());
    	int error = checkDistance - relativeDistance;
    	    	
		switch(this.action){
		case OPEN:
			Robot.toteGrab.setLeftMotorSpeed(-1);
			Robot.toteGrab.setRightMotorSpeed(-1);
		case CLOSE:
			Robot.toteGrab.setLeftMotorSpeed(1);
			Robot.toteGrab.setRightMotorSpeed(1);
		case PAN_LEFT:
	    	Robot.toteGrab.setLeftMotorSpeed(.8 - error * .01);
	    	Robot.toteGrab.setRightMotorSpeed(.8 + error * .01);
		case PAN_RIGHT:
	    	Robot.toteGrab.setLeftMotorSpeed(.8 + error * .01);
        	Robot.toteGrab.setRightMotorSpeed(.8 - error * .01);
		case CENTER:
			System.out.println("Center command doesn't work. Need tested values!");
			Robot.toteGrab.getLeftMotor().setPID(1.0, 1.0, 1.0);//TODO
			Robot.toteGrab.getRightMotor().setPID(1.0, 1.0, 1.0);//TODO
		case AUTO_CLOSE:
			Robot.toteGrab.setLeftMotorSpeed(1);
			Robot.toteGrab.setRightMotorSpeed(1);
		case AUTO_OPEN:
			Robot.toteGrab.setLeftMotorSpeed(-1);
			Robot.toteGrab.setRightMotorSpeed(-1);
		case STOP:
			this.end();
		default:
			this.end();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		switch(this.action){
		case OPEN:

		case CLOSE:
			
		case PAN_LEFT:
			
		case PAN_RIGHT:
			
		case CENTER:
			return (onTargetRight() && onTargetLeft());
		case AUTO_OPEN:
			relativeDistance=Math.abs(Robot.toteGrab.getLeftMotor().getAnalogInPosition()-Robot.toteGrab.getRightMotor().getAnalogInPosition());
			if(relativeDistance>this.autoAbsOpenDist){
				return true;
			}
		case AUTO_CLOSE:
			relativeDistance=Math.abs(Robot.toteGrab.getLeftMotor().getAnalogInPosition()-Robot.toteGrab.getRightMotor().getAnalogInPosition());
			if(relativeDistance<this.autoAbsCloseDist){
				return true;
			}
		case STOP:
			return true;
		}
		return false;
	}
	
    public boolean onTargetRight() {
    	double error = Robot.toteGrab.getRightMotor().get() - Preferences.getInstance().getDouble("setpointRight", Double.POSITIVE_INFINITY);
		return (Math.abs(error) <= tolerance);
	} 
    public boolean onTargetLeft() {
    	double error = Robot.toteGrab.getLeftMotor().get() - Preferences.getInstance().getDouble("setpointLeft", Double.POSITIVE_INFINITY);
		return (Math.abs(error) <= tolerance);
	} 
    
	// Called once after isFinished returns true
	protected void end() {
		Robot.toteGrab.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
