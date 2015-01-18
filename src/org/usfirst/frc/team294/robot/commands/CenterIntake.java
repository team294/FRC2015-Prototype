package org.usfirst.frc.team294.robot.commands;

import org.usfirst.frc.team294.robot.Robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CenterIntake extends Command {

	
	private double tolerance;
	
    public CenterIntake() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.toteGrab);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.toteGrab.centerIntake();
    	
		double setp = Preferences.getInstance().getDouble("setpointLeft", Double.POSITIVE_INFINITY);
		double setpr = Preferences.getInstance().getDouble("setpointRight", Double.POSITIVE_INFINITY);
		if (setp == Double.POSITIVE_INFINITY)
			return;
		if(setpr == Double.POSITIVE_INFINITY)
			return;
		Robot.toteGrab.leftMotor.set(setp);//setSetpoint(setp);
		Robot.toteGrab.rightMotor.set(setpr);
		if (!(ControlMode.Position == Robot.toteGrab.leftMotor.getControlMode())) {
			if(!Robot.toteGrab.leftMotor.isControlEnabled())
				Robot.toteGrab.leftMotor.enableControl();
			Robot.toteGrab.leftMotor.changeControlMode(ControlMode.Position);
		}
		if (!(ControlMode.Position == Robot.toteGrab.rightMotor.getControlMode())) {
			if(!Robot.toteGrab.rightMotor.isControlEnabled())
				Robot.toteGrab.rightMotor.enableControl();
			Robot.toteGrab.rightMotor.changeControlMode(ControlMode.Position);
		}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (onTargetRight() && onTargetLeft());
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    public boolean onTargetRight() {
    	double error = Robot.toteGrab.rightMotor.get() - Preferences.getInstance().getDouble("setpointRight", Double.POSITIVE_INFINITY);
		return (Math.abs(error) <= tolerance);
	} 
    public boolean onTargetLeft() {
    	double error = Robot.toteGrab.leftMotor.get() - Preferences.getInstance().getDouble("setpointLeft", Double.POSITIVE_INFINITY);
		return (Math.abs(error) <= tolerance);
	} 
}
