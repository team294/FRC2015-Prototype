package org.usfirst.frc.team294.robot.commands;

import org.usfirst.frc.team294.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoRotateDegrees extends Command {
private double degrees;
private double initialPosition = Robot.drivetrain.getAngle();
private double finalPosition=0;
private double threshold = 0.5; 

    public AutoRotateDegrees(double degs) {//degs = degrees. positive number = turn Right. Negative number = turn Left
    	requires(Robot.drivetrain);
    	degrees = degs;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (degrees>0)Robot.drivetrain.tankDrive(1,-1);
    	else Robot.drivetrain.tankDrive(-1, 1);
    	double finalPosition = Robot.drivetrain.getAngle();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Math.abs(degrees -(finalPosition-initialPosition))< threshold){return true;}
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
