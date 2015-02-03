package org.usfirst.frc.team294.robot.commands;

import org.usfirst.frc.team294.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoRotateXDegreesRel extends Command {
private double degrees;
private double currentOrientation=0;
private double desiredOrientation=0;
private double degBuffer = 5; 
private boolean first = true;
private double previousPos;
private boolean addMode = false;

    public AutoRotateXDegreesRel(double degs) {//degs = degrees. positive number = turn Right. Negative number = turn Left
    	requires(Robot.drivetrain);
    	degrees = degs;
    	System.out.println("Constructor degrees: " + degrees);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.drivetrain.resetEncoders();
    	System.out.println("initialize degrees: " + degrees);
    	
    	//initial position plus degs rel 0-360 
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("theoretical error: " + degrees);
    	currentOrientation = Robot.drivetrain.getYaw() + 180;//current position 0-360
    	System.out.println("current: " +currentOrientation);
    	if(first){
    		desiredOrientation=currentOrientation+degrees;
    		System.out.println("desired:" + desiredOrientation);
    	}
    	
       
    	if(!first)
    	{
    		if(previousPos - currentOrientation >= 300)
    		{
    			addMode = true;
    			System.out.println("add mode activated");
    		}
    	}
    	
    	if(first)
    	{
    		first = false;
    	}
    
    	if(addMode)
    	{
    		currentOrientation += 360;
    	}
    	
    	double error = desiredOrientation - currentOrientation;
    	previousPos = this.currentOrientation;
    	//System.out.println("target: " +desiredOrientation);
    	
 
    	System.out.println("error: " + error);
    	
    	Robot.drivetrain.tankDrive(error * .035,-error * .035);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Math.abs((currentOrientation)-(desiredOrientation))< degBuffer){
    		Robot.drivetrain.tankDrive(0,0);
    		System.out.println("Finished");
    		return true;
    		}
    	else
    		return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stop(); 
    	currentOrientation=0;
    	desiredOrientation=0;
    	first = true;
    	previousPos = 0;
    	addMode = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.stop();
    }
}
