package org.usfirst.frc.team294.robot.commands;

import org.usfirst.frc.team294.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveStraight extends Command {
	
	private double dis;
	private double velocity;
	//private int initDir;
	//private int dirCorrectionThreshold=5;
	/**
	 * 
	 * @param distance is negative for backwards and positive for forwards.
	 */
    public AutoDriveStraight(double distance, double absoluteSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	dis=distance;
    	velocity=absoluteSpeed;
    }
    public AutoDriveStraight(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	dis=distance;
    	velocity=1;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetEncoders();
    	//initDir=(int) Robot.drivetrain.getYaw();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(dis<0){
    		velocity*=-1;
    	}
    	Robot.drivetrain.autoDrive(velocity);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double left=Robot.drivetrain.getLeft();
    	double right=Robot.drivetrain.getRight();
        return (right>=dis)||(left>=dis);
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
