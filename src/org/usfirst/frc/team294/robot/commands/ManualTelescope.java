package org.usfirst.frc.team294.robot.commands;

import org.usfirst.frc.team294.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualTelescope extends Command {


	public ManualTelescope() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);

		requires(Robot.telescope);
		
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		try{
			Robot.telescope.setTelescopeSpeed(-.3 * (Robot.oi.testStick.getY()));
		}
		catch(ArrayIndexOutOfBoundsException e){
			Robot.telescope.setTelescopeSpeed(0);
		}
		System.out.println(Robot.telescope.getPotCanVal());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.telescope.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
