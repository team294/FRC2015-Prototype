package org.usfirst.frc.team294.robot.commands;

import org.usfirst.frc.team294.robot.Robot;
import org.usfirst.frc.team294.robot.commands.SafeGrabOrReleaseTote.GrabOrRelease;

import edu.wpi.first.wpilibj.command.Command;

/**
 *harder close than safegraborrelease, unlimited release distance
 *
 */
public class ManualGrabOrReleaseTote extends Command {

	GrabOrRelease doingThis = GrabOrRelease.RELEASE;

	public ManualGrabOrReleaseTote(GrabOrRelease doThis) {
		requires(Robot.toteGrab);
		doingThis=doThis;
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		switch(doingThis){	
		case GRAB:{
			this.close();
			break;
		}
		case RELEASE:{
			this.release();
			break;
		}
		}
	}

	private void release() {
		Robot.toteGrab.setLeftMotorSpeed(1);
		Robot.toteGrab.setRightMotorSpeed(1);
	}

	private boolean leftHasHit=false;
	private boolean rightHasHit=false;
	public void close(){
		boolean leftOverVolt=false;
		boolean rightOverVolt=false;
		leftOverVolt=Robot.toteGrab.getLeftMotor().getBusVoltage()>Robot.toteGrab.getVoltageThreshold();
		if(leftOverVolt)leftHasHit=true;
		if(!leftHasHit){
			Robot.toteGrab.panLeftRight();
		}
		rightOverVolt=Robot.toteGrab.getRightMotor().getBusVoltage()>Robot.toteGrab.getVoltageThreshold();
		if(rightOverVolt)rightHasHit=true;
		if(!rightHasHit){
			Robot.toteGrab.panRightLeft();
		}
		if(leftHasHit&&rightHasHit){
			leftHasHit=false;
			rightHasHit=false;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
