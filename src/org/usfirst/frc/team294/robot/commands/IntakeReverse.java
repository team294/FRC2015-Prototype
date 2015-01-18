package org.usfirst.frc.team294.robot.commands;

import org.usfirst.frc.team294.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeReverse extends Command {

    public IntakeReverse() {
        requires(Robot.intakeRollers);
    }
    int runMode;
    // Called just before this Command runs the first time
    protected void initialize() {
    	runMode = Robot.intakeRollers.runMode();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(runMode == 1) //If intake is running forward
    	{
    		Robot.intakeRollers.runOut(); //run in reverse
    	}
    	else if(runMode == -1) //If intake is running in reverse
    	{
    		Robot.intakeRollers.runIn();
    	}
    	else if(runMode == 0) //If intake is not running
    		Robot.intakeRollers.runIn();
    	else
    		Robot.intakeRollers.stop();
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
