package org.usfirst.frc.team294.robot.commands;

import org.usfirst.frc.team294.robot.Robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToteMotorControl extends Command {

	public enum ToteMotorActions{
		OPEN,
		CLOSE,
		PAN_LEFT,
		PAN_RIGHT,
		CENTER,
		STOP
	}
	
	private double tolerance=10;
	ToteMotorActions action;
	private int relativeDistance;

	public ToteMotorControl(ToteMotorActions a) {
		requires(Robot.toteGrab);
		this.action=a;

		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}



	// Called just before this Command runs the first time
	protected void initialize() {
		switch(this.action){
		case OPEN:

		case CLOSE:
			
		case PAN_LEFT:
			relativeDistance=Math.abs(Robot.toteGrab.getLeftMotor().getAnalogInPosition()-Robot.toteGrab.getRightMotor().getAnalogInPosition());
		case PAN_RIGHT:
			relativeDistance=Math.abs(Robot.toteGrab.getLeftMotor().getAnalogInPosition()-Robot.toteGrab.getRightMotor().getAnalogInPosition());
		case CENTER:
			//Robot.toteGrab.centerIntake();
	    	double setp = Preferences.getInstance().getDouble("setpointLeft", Double.POSITIVE_INFINITY);
			double setpr = Preferences.getInstance().getDouble("setpointRight", Double.POSITIVE_INFINITY);
			if (setp == Double.POSITIVE_INFINITY)
				return;
			if(setpr == Double.POSITIVE_INFINITY)
				return;
			Robot.toteGrab.leftMotor.set(setp);//setSetpoint(setp);
			Robot.toteGrab.rightMotor.set(setpr);
			if (!(ControlMode.Position == ((CANTalon) Robot.toteGrab.leftMotor).getControlMode())) {
				if(!((CANTalon) Robot.toteGrab.leftMotor).isControlEnabled())
					((CANTalon) Robot.toteGrab.leftMotor).enableControl();
				((CANTalon) Robot.toteGrab.leftMotor).changeControlMode(ControlMode.Position);
			}
			if (!(ControlMode.Position == ((CANTalon) Robot.toteGrab.rightMotor).getControlMode())) {
				if(!((CANTalon) Robot.toteGrab.rightMotor).isControlEnabled())
					((CANTalon) Robot.toteGrab.rightMotor).enableControl();
				((CANTalon) Robot.toteGrab.rightMotor).changeControlMode(ControlMode.Position);
			}
		case STOP:
			this.end();
		default:
			this.end();
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		int checkDistance=Math.abs(Robot.toteGrab.getLeftMotor().getAnalogInPosition()-Robot.toteGrab.getRightMotor().getAnalogInPosition());
    	int error = checkDistance - relativeDistance;
    	    	
		switch(this.action){
		case OPEN:
			
		case CLOSE:

		case PAN_LEFT:
	    	Robot.toteGrab.setLeftMotorSpeed(.8 - error * .01);
	    	Robot.toteGrab.setRightMotorSpeed(.8 + error * .01);
		case PAN_RIGHT:
	    	Robot.toteGrab.setLeftMotorSpeed(.8 + error * .01);
        	Robot.toteGrab.setRightMotorSpeed(.8 - error * .01);
		case CENTER:
			
		case STOP:
			this.end();
		default:
			this.end();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		switch(this.action){
		case OPEN:

		case CLOSE:
			
		case PAN_LEFT:
			
		case PAN_RIGHT:
			
		case CENTER:
			return (onTargetRight() && onTargetLeft());
		case STOP:
			return true;
		}
		return false;
	}
	
    public boolean onTargetRight() {
    	double error = Robot.toteGrab.rightMotor.get() - Preferences.getInstance().getDouble("setpointRight", Double.POSITIVE_INFINITY);
		return (Math.abs(error) <= tolerance);
	} 
    public boolean onTargetLeft() {
    	double error = Robot.toteGrab.leftMotor.get() - Preferences.getInstance().getDouble("setpointLeft", Double.POSITIVE_INFINITY);
		return (Math.abs(error) <= tolerance);
	} 
    
	// Called once after isFinished returns true
	protected void end() {
		Robot.toteGrab.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
