package org.usfirst.frc.team294.robot.commands;

import org.usfirst.frc.team294.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PanIntakeLeft extends Command {
	
	private int relativeDistance=0;
	
	private int softLeftDistanceLimit=15;
	
	//PotLimitedSpeedController toteControllerRight = new PotLimitedSpeedController();
    public PanIntakeLeft() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.toteGrab);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	relativeDistance=Math.abs(Robot.toteGrab.getLeftMotor().getAnalogInPosition()-Robot.toteGrab.getRightMotor().getAnalogInPosition());
    	System.out.println("Pan left yoo");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	int checkDistance=Math.abs(Robot.toteGrab.getLeftMotor().getAnalogInPosition()-Robot.toteGrab.getRightMotor().getAnalogInPosition());
    	if(Robot.toteGrab.getLeftMotor().getAnalogInPosition()>softLeftDistanceLimit)return;
    	float leftSpeed=1;
    	float rightSpeed=-1;
    	if(checkDistance>relativeDistance){
    		leftSpeed-=0.1;
    	}else if(checkDistance<relativeDistance){
    		rightSpeed-=0.1;
    	}
    	Robot.toteGrab.setLeftMotorSpeed(leftSpeed);
    	Robot.toteGrab.setLeftMotorSpeed(rightSpeed);
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
    protected void interrupted(){
    	
    }
}
