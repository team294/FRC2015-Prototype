package org.usfirst.frc.team294.robot.commands;

import org.usfirst.frc.team294.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunIntakeTillBump extends Command {

	Timer timer = new Timer();
	boolean isHit;
    public RunIntakeTillBump() {
    	requires(Robot.intakeRollerArms);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	isHit = false;
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(!isHit)
    		Robot.intakeRollerArms.closeMotor(.6);
    	else
    		Robot.intakeRollerArms.closeMotor(.25);
		if(Robot.intakeRollerArms.getBumpSwitch())
			isHit = true;

		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(!isHit || !Robot.intakeRollerArms.getBumpSwitch())
    		timer.reset();
    	return (timer.get() > 0.25);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intakeRollerArms.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intakeRollerArms.stop();
    }
}
