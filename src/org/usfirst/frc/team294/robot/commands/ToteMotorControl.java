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
		OPEN_SLIGHT,
		LEFTCONTROL,
		LEFTCONTROL2,
		RIGHTCONTROL,
		RIGHTCONTROL2
	}
	
	private int openPosR = 1000, //Make this amount "openSlightAmt" less than completely out
			wideTotePosR = 100, narrowTotePosR = 5000;
	private int openPosL = 1000, //Make this amount "openSlightAmt" less than completely out
			wideTotePosL= 100, narrowTotePosL = 5000;
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
		leftInit = Robot.toteGrab.getLeftMotor().getEncPosition();
		rightInit = Robot.toteGrab.getRightMotor().getEncPosition();
		relativeDistance=Math.abs(Robot.toteGrab.getLeftMotor().getEncPosition()-Robot.toteGrab.getRightMotor().getEncPosition());
		
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
    	int error = -Robot.toteGrab.getRightPos() + Robot.toteGrab.getLeftPos();
    	SmartDashboard.putNumber("Error:", error);
    	SmartDashboard.putNumber("Modified error", error* .0007);
    	int errorLeft = 1500 - Robot.toteGrab.getLeftPos();
    	int errorRight = 2500 - Robot.toteGrab.getRightPos();
    	//SmartDashboard.putNumber("LeftMotorOutput ", Robot.toteGrab.getLeftMotor().get());
    	//SmartDashboard.putNumber("LeftMotorOutput ", Robot.toteGrab.getRightMotor().get());
    	
    	
    	
		switch(this.action){
		
		case OPEN:
			System.out.println("Opening");
			if(!Robot.toteGrab.getRightLimit())
				Robot.toteGrab.setRightMotorSpeed(.8);	
			if(!Robot.toteGrab.getLeftLimit())
				Robot.toteGrab.setLeftMotorSpeed(.8);
			
			
			break;
		case CLOSE:
			System.out.println("closing");
			Robot.toteGrab.setLeftMotorSpeed(-.8);
			Robot.toteGrab.setRightMotorSpeed(-.8);
			break;
			
		case PAN_LEFT:
	    	Robot.toteGrab.setLeftMotorSpeed(.3 - error * .0007);
	    	Robot.toteGrab.setRightMotorSpeed(-.3 + error * .0007);
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
			Robot.toteGrab.setLeftMotorSpeed(-errorLeft * .001);
			Robot.toteGrab.setRightMotorSpeed(-errorRight * .001);
			break;
			
		case OPEN_SLIGHT:
			Robot.toteGrab.setLeftPosition(openSlightPosL);
			Robot.toteGrab.setRightPosition(openSlightPosR);
			break;
			
		case STOP:
			this.end();
			break;
			
		case LEFTCONTROL:
			Robot.toteGrab.setLeftMotorSpeed(-.8);
			break;
		case LEFTCONTROL2:
			Robot.toteGrab.setLeftMotorSpeed(.8);
			break;
		case RIGHTCONTROL:
			Robot.toteGrab.setRightMotorSpeed(-.8);
			break;
		case RIGHTCONTROL2:
			Robot.toteGrab.setRightMotorSpeed(.8);
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
			return onTargetRight(2500) && onTargetLeft(1500);
			
		
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
