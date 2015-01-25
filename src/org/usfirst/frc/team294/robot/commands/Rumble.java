package org.usfirst.frc.team294.robot.commands;

import org.usfirst.frc.team294.robot.Robot;

import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Rumble extends Command {

    public Rumble() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.compressor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.compressor.enabled())
    	{
    		Robot.oi.testStick.setRumble(RumbleType.kLeftRumble, 1);
    		Robot.oi.testStick.setRumble(RumbleType.kRightRumble, 1);
    	}
    	//Robot.oi.testStick.setRumble(RumbleType.kLeftRumble, (float) Math.abs(Robot.oi.testStick.getRawAxis(1)));
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
