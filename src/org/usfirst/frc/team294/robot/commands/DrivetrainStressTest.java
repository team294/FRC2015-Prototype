package org.usfirst.frc.team294.robot.commands;

import org.usfirst.frc.team294.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivetrainStressTest extends Command {

	private double speedMultiplier=1.0;
	Timer t=new Timer();

	public DrivetrainStressTest() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		t.reset();
		t.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(t.get()<20.0){
			Robot.drivetrain.tankDrive(1.0*speedMultiplier, 1.0*speedMultiplier);
		}else{
			t.stop();
			t.reset();
			t.start();
			speedMultiplier*=-1;
		}
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
