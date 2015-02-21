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
	
	private int openPosR = 3500, //Make this amount "openSlightAmt" less than completely out
			wideTotePosR = 3638,
			narrowTotePosR = 23075;
	private int openPosL = 3500, //Make this amount "openSlightAmt" less than completely out
			wideTotePosL= 3638,
			narrowTotePosL = 23075;
	private int openSlightAmt = 3500;
	//private int autoAbsOpenDist=400;//TODO
	//private int autoAbsCloseDist=300;//TODO
	private int leftInit, rightInit;
	private double tolerance=30;
	protected ToteMotorAction action;
	private int targetPosL, targetPosR;
	public ToteMotorControl(ToteMotorAction a) {
		requires(Robot.toteGrab);
		this.action=a;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		leftInit = Robot.toteGrab.getLeftMotor().getEncPosition();
		rightInit = Robot.toteGrab.getRightMotor().getEncPosition();
		Math.abs(Robot.toteGrab.getLeftMotor().getEncPosition()-Robot.toteGrab.getRightMotor().getEncPosition());
		
		switch(this.action)
		{
		case AUTO_OPEN:
			targetPosL = openPosL;
			targetPosR = openPosR;
			break;
		case WIDE_TOTE:
			targetPosL = wideTotePosL;
			targetPosR = wideTotePosR;
			break;
		case NARROW_TOTE:
			targetPosL = narrowTotePosL;
			targetPosR = narrowTotePosR;
			break;
		case OPEN_SLIGHT:
			targetPosL = leftInit - openSlightAmt;
			targetPosR = rightInit - openSlightAmt;
			break;
		default:
			break;
		}
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//int checkDistance=Math.abs(Robot.toteGrab.getLeftPos()-Robot.toteGrab.getRightPos());
    	//int error = checkDistance - relativeDistance;
    	int error = -Robot.toteGrab.getRightPos() + Robot.toteGrab.getLeftPos();
    	SmartDashboard.putNumber("Error:", error);
    	SmartDashboard.putNumber("Modified error", error* .0007);
    	//SmartDashboard.putNumber("LeftMotorOutput ", Robot.toteGrab.getLeftMotor().get());
    	//SmartDashboard.putNumber("LeftMotorOutput ", Robot.toteGrab.getRightMotor().get());
    	
    	
    	
		switch(this.action){
		case AUTO_OPEN:
		case WIDE_TOTE:
		case NARROW_TOTE:
		case OPEN_SLIGHT:
			Robot.toteGrab.setLeftPosition(targetPosL);
			Robot.toteGrab.setRightPosition(targetPosR);
			break;
		
		case OPEN:
			System.out.println("Opening");
				Robot.toteGrab.setRightMotorSpeed(1);
				Robot.toteGrab.setLeftMotorSpeed(1);
			
			
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
    	if (timeSinceInitialized() > 2.0)
    		return true;
		switch(this.action){
		case OPEN:
			return Robot.toteGrab.getLeftLimit() && Robot.toteGrab.getRightLimit();
		case CLOSE:
			return false;
		case PAN_LEFT:
			System.out.println("Pan Left Done");
			return !(Robot.oi.left[1].get());
		case PAN_RIGHT:
			System.out.println("Pan Right Done");
			return !(Robot.oi.right[1].get());
			
		case AUTO_OPEN:
		case WIDE_TOTE:
		case NARROW_TOTE:
		case OPEN_SLIGHT:
	    	if (timeSinceInitialized() < 0.2)
	    		return false;
			return onTargetRight() && onTargetLeft();
			
		case STOP:
			return true;
			
		default:
			break;
		}
		return false;
	}
	
    public boolean onTargetRight() {
		return Math.abs(Robot.toteGrab.getRightMotor().getClosedLoopError()) <= tolerance;
	} 
    public boolean onTargetLeft() {
		return Math.abs(Robot.toteGrab.getLeftMotor().getClosedLoopError()) <= tolerance;
	} 
    
	// Called once after isFinished returns true
	protected void end() {
		Robot.toteGrab.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.toteGrab.stop();
	}
}
