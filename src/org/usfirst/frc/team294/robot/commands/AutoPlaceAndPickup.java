package org.usfirst.frc.team294.robot.commands;

import org.usfirst.frc.team294.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoPlaceAndPickup extends Command {

    public AutoPlaceAndPickup() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    //	requires(Robot.telesope);
    	requires(Robot.telescope);
    	requires(Robot.doublePistonIntake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.doublePistonIntake.pistonOut();
    	//new TelescopeToPickup();
    	Robot.doublePistonIntake.pistonIn();
    	new TelescopeToMid();
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
