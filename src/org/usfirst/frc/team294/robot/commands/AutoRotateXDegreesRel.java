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

    public AutoRotateXDegreesRel(double degs) {//degs = degrees. positive number = turn Right. Negative number = turn Left
    	requires(Robot.drivetrain);
    	degrees = degs;
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.drivetrain.resetEncoders();
    	//initial position plus degs rel 0-360 
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	this.currentOrientation = Robot.drivetrain.getYaw() + 180;//current position 0-360
    	if(first){
    		desiredOrientation=currentOrientation+degrees;
    		first=false;
    	}
    	//System.out.println("target: " +desiredOrientation);
    	//System.out.println("current: " +currentOrientation);
    	double error = desiredOrientation - currentOrientation;
    	//System.out.println("error: " + error);
    	
    	

    	Robot.drivetrain.tankDrive(error * .035,-error * .035);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Math.abs((currentOrientation)-(desiredOrientation))< degBuffer){
    		Robot.drivetrain.tankDrive(0,0);
    		return true;}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stop(); 
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.stop();
    }
}
