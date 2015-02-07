package org.usfirst.frc.team294.robot.commands;

import org.usfirst.frc.team294.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TelescopeToPos extends Command {
	private int pos;
    public TelescopeToPos(int pos) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.pos = pos;
    	requires(Robot.telescope);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Robot.telescope.getPotVal() > pos)
    		Robot.telescope.getMainTelescope().setProfile(0);
    	else
    		Robot.telescope.getMainTelescope().setProfile(1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.telescope.setPosition(pos);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Math.abs(Robot.telescope.getPotVal() - pos) < 5);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}