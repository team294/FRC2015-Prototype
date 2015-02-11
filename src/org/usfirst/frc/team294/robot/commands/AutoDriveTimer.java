package org.usfirst.frc.team294.robot.commands;

import org.usfirst.frc.team294.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoDriveTimer extends Command {

	private double timeInit;
	private double elapsedTime;
	private boolean forwBack;
    public AutoDriveTimer(boolean forwBack) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		this.forwBack = forwBack;
    	requires(Robot.drivetrain);
    	 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timeInit = System.currentTimeMillis();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("ElapsedTime", elapsedTime);
    	elapsedTime = System.currentTimeMillis() - timeInit;
    	if(forwBack == false)
    		Robot.drivetrain.tankDrive(-.32,-.32);
    	else
    		Robot.drivetrain.tankDrive(.32,.32);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (elapsedTime / 1000 > .7);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.tankDrive(0,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
