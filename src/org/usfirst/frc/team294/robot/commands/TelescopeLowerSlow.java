package org.usfirst.frc.team294.robot.commands;

import org.usfirst.frc.team294.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TelescopeLowerSlow extends Command {

	int targetPos;
    public TelescopeLowerSlow() {
    	requires(Robot.telescope);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	targetPos = Robot.telescope.getPotVal() - 15;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.telescope.setManual(-0.2);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.telescope.getPotVal() <= targetPos;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.telescope.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.telescope.stop();
    }
}
