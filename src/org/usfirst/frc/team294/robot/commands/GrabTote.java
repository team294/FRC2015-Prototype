package org.usfirst.frc.team294.robot.commands;

import org.usfirst.frc.team294.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class GrabTote extends Command {

	public GrabTote() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.toteGrab);
		requires(Robot.intakeRollers);
		requires(Robot.telescope);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(Robot.toteGrab.isOpen())this.close();
	}
	
	private boolean leftHasHit=false;
	private boolean rightHasHit=false;
    public void close(){
    	boolean leftOverVolt=false;
    	boolean rightOverVolt=false;
    	if(Robot.toteGrab.isOpen()){
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
    	}else{
    		leftHasHit=false;
			rightHasHit=false;
    	}
    }

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return !Robot.toteGrab.isOpen();
	}

	// Called once after isFinished returns true
	protected void end() {
		
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		
	}
}
