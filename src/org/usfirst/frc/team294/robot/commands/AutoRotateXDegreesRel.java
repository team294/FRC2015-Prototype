package org.usfirst.frc.team294.robot.commands;

import org.usfirst.frc.team294.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoRotateXDegreesRel extends Command {
	private double degreesRel;
	private double currentOrientation=0;
	private double desiredOrientation=0;
	private double degBuffer = 10; 
	private double rotScale = 0.02;
	private double initDriveSpeed=0.9;
	private int rotDir=0;

	public AutoRotateXDegreesRel(double degs) {//degs = degrees. positive number = turn Right. Negative number = turn Left
		requires(Robot.drivetrain);
		degreesRel = degs;
		currentOrientation = Robot.drivetrain.getYaw()+180;
		desiredOrientation=currentOrientation + degreesRel;
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		
	}

	// Called repeatedly when this Command is scheduled to run
	
	protected void execute() {
		currentOrientation=Robot.drivetrain.getYaw()+180;
		
		if(currentOrientation>desiredOrientation){
			rotDir=1;
		}else{
			rotDir=-1;
		}
		
		rotScale= Math.abs(currentOrientation-desiredOrientation)*rotScale;
				
		Robot.drivetrain.tankDrive(-initDriveSpeed*rotDir-rotScale, initDriveSpeed*rotDir-rotScale);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (Math.abs((currentOrientation)-(desiredOrientation))< degBuffer){
			Robot.drivetrain.tankDrive(0,0);
			System.out.println("Finished");
			return true;
		}else{
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.stop(); 
		currentOrientation=0;
		desiredOrientation=0;
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.drivetrain.stop();
	}
}
