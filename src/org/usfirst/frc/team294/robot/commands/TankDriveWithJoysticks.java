package org.usfirst.frc.team294.robot.commands;

import org.usfirst.frc.team294.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TankDriveWithJoysticks extends Command {

    public TankDriveWithJoysticks() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	try {
        	//System.out.println("tank drive left:" + Robot.oi.leftStick.getY() + " right:" + Robot.oi.rightStick.getY());
    		Robot.drivetrain.tankDrive((Robot.oi.leftStick.getY()*.9), (Robot.oi.rightStick.getY()*.9));
    		//System.out.println("Right " + Robot.drivetrain.getRight());
    		//System.out.println("Left " + Robot.drivetrain.getLeft());
    	} catch (ArrayIndexOutOfBoundsException e) {
    		Robot.drivetrain.tankDrive(0, 0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
