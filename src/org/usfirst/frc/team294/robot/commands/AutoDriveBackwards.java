package org.usfirst.frc.team294.robot.commands;

import org.usfirst.frc.team294.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveBackwards extends Command {

    public AutoDriveBackwards() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	try {
        	//System.out.println("tank drive left:" + Robot.oi.leftStick.getY() + " right:" + Robot.oi.rightStick.getY());
    		Robot.drivetrain.tankDrive(-.4, -.4);
    		System.out.println("Worked");
    	} catch (ArrayIndexOutOfBoundsException e) {
    		Robot.drivetrain.tankDrive(0, 0);
    		System.out.println("Didnt work");
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
