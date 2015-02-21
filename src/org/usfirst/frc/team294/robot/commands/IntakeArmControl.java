package org.usfirst.frc.team294.robot.commands;

import org.usfirst.frc.team294.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeArmControl extends Command {

	public static enum IntakeArmAction{
		OPEN,
		OPEN_MID,
		CLOSE,
		STOP,
		MOTOR_OUT,
		MOTOR_IN,
		WIDE_TOTE
	}

	protected IntakeArmAction act;

	public IntakeArmControl(IntakeArmAction action) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.intakeRollerArms);
		this.act=action;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("Running");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		switch (this.act){
		case OPEN:
			Robot.intakeRollerArms.openAll();
			break;
		case CLOSE:
			System.out.println("Running close()");
			Robot.intakeRollerArms.closeAll();
			break;
		case STOP:
			System.out.println("stop");
			Robot.intakeRollerArms.stop();
			break;
		case OPEN_MID:
			Robot.intakeRollerArms.openMid();
			break;
		case WIDE_TOTE:
			Robot.intakeRollerArms.openWideTote();
			break;
		case MOTOR_OUT:
			Robot.intakeRollerArms.openMotor();
			break;
			
		case MOTOR_IN:
			Robot.intakeRollerArms.closeMotor(0.6);
			break;
		}
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
		Robot.intakeRollerArms.stop();
	}
}
