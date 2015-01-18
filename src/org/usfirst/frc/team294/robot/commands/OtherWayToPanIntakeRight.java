package org.usfirst.frc.team294.robot.commands;

import org.usfirst.frc.team294.robot.Robot;
import org.usfirst.frc.team294.robot.RobotMap;
import org.usfirst.frc.team294.robot.util.PotLimitedSpeedController;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OtherWayToPanIntakeRight extends Command {

	private float error;
	private float relativeDistance = 0;
	private float constant = 1;
	//PotLimitedSpeedController toteControllerRight = new PotLimitedSpeedController();
    public OtherWayToPanIntakeRight() {
        requires(Robot.toteGrab);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	relativeDistance = Robot.toteGrab.getLeftPosition() - Robot.toteGrab.getRightPosition();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	error = ((Robot.toteGrab.getLeftPosition() - Robot.toteGrab.getRightPosition()) - relativeDistance) * constant;
    	Robot.toteGrab.setLeftMotorSpeed(1 - error);
    	Robot.toteGrab.setRightMotorSpeed(1 + error);
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
