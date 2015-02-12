package org.usfirst.frc.team294.robot.commands;

import org.usfirst.frc.team294.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		AUTO_OPEN,
		STOP,
		WIDE_TOTE,
		NARROW_TOTE,
		OPEN_SLIGHT
	}
	
	private int openPosR = 1000, //Make this amount "openSlightAmt" less than completely out
			wideTotePosR = 100, narrowTotePosR;
	private int openPosL = 1000, //Make this amount "openSlightAmt" less than completely out
			wideTotePosL= 100, narrowTotePosL;
	private int openSlightAmt = 30;
	private int openSlightPosL;
	private int openSlightPosR;
	//private int autoAbsOpenDist=400;//TODO
	//private int autoAbsCloseDist=300;//TODO
	private int leftInit, rightInit;
	private double tolerance=15;
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
		leftInit = Robot.toteGrab.getLeftMotor().getAnalogInPosition();
		rightInit = Robot.toteGrab.getRightMotor().getAnalogInPosition();
		relativeDistance=Math.abs(Robot.toteGrab.getLeftMotor().getAnalogInPosition()-Robot.toteGrab.getRightMotor().getAnalogInPosition());
		
		switch(this.action)
		{
		case OPEN_SLIGHT:
			openSlightPosL = leftInit + openSlightAmt;
			openSlightPosR = rightInit + openSlightAmt;
		}
		
		
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//int checkDistance=Math.abs(Robot.toteGrab.getLeftPos()-Robot.toteGrab.getRightPos());
    	//int error = checkDistance - relativeDistance;
    	int error = Robot.toteGrab.getRightPos();
    	SmartDashboard.putNumber("Error:", error);
    	SmartDashboard.putNumber("Modified error", error* .0007);
    	
		switch(this.action){
		
		case OPEN:
			Robot.toteGrab.setLeftMotorSpeed(-.8);
			Robot.toteGrab.setRightMotorSpeed(-.8);	
			break;
		case CLOSE:
			Robot.toteGrab.setLeftMotorSpeed(.8);
			Robot.toteGrab.setRightMotorSpeed(.8);
			break;
			
		case PAN_LEFT:
	    	Robot.toteGrab.setLeftMotorSpeed(.3 - error * .0007);
	    	Robot.toteGrab.setRightMotorSpeed(.3 + error * .0007);
	    	break;
		case PAN_RIGHT:
	    	Robot.toteGrab.setLeftMotorSpeed(-.3 + error * .0007);
        	Robot.toteGrab.setRightMotorSpeed(-.3 - error * .0007);
        	break;
        	
		case AUTO_OPEN:
			Robot.toteGrab.setLeftPosition(openPosL);
			Robot.toteGrab.setRightPosition(openPosR);
			break;
			
		case WIDE_TOTE:
			Robot.toteGrab.setLeftPosition(wideTotePosL);
			Robot.toteGrab.setRightPosition(wideTotePosR);
			break;
		case NARROW_TOTE:
			Robot.toteGrab.setLeftPosition(narrowTotePosL);
			Robot.toteGrab.setRightPosition(narrowTotePosR);
			break;
			
		case OPEN_SLIGHT:
			Robot.toteGrab.setLeftPosition(openSlightPosL);
			Robot.toteGrab.setRightPosition(openSlightPosR);
			break;
			
		case STOP:
			this.end();
			break;
		default:
			this.end();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		switch(this.action){
		case OPEN:
			return false;
		case CLOSE:
			return false;
			
			
		case PAN_LEFT:
			System.out.println("Pan Left Done");
			return !(Robot.oi.left[1].get());
		case PAN_RIGHT:
			System.out.println("Pan Right Done");
			return !(Robot.oi.right[1].get());
			
		case AUTO_OPEN:
			//return onTargetRight(openPosR) && onTargetLeft(openPosL);
			return onTargetRight(openPosR);
		case WIDE_TOTE:
			return onTargetRight(wideTotePosR) && onTargetLeft(wideTotePosL);
		case NARROW_TOTE:
			return onTargetRight(narrowTotePosR) && onTargetLeft(narrowTotePosL);
			
		
		case OPEN_SLIGHT:
			return onTargetRight(openSlightPosR) && onTargetLeft(openSlightPosL);
			
			
		case STOP:
			return true;
			
		default:
			break;
		}
		return false;
	}
	
    public boolean onTargetRight(int targetPos) {
    	double error = Robot.toteGrab.getRightMotor().get() - targetPos;
		return (Math.abs(error) <= tolerance);
	} 
    public boolean onTargetLeft(int targetPos) {
    	double error = Robot.toteGrab.getLeftMotor().get() - targetPos;
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
