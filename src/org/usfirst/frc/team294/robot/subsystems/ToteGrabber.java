package org.usfirst.frc.team294.robot.subsystems;

import org.usfirst.frc.team294.robot.Robot;
import org.usfirst.frc.team294.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ToteGrabber extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private CANTalon rightMotor = new CANTalon(RobotMap.toteCloseIntake);
	private CANTalon leftMotor = new CANTalon(RobotMap.toteCloseIntake2);

	public ToteGrabber()
	{
		leftMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rightMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rightMotor.setPosition(0);
		leftMotor.setPosition(0);
	}

	public void setLeftMotorSpeed(double leftSpeed){
		leftMotor.changeControlMode(ControlMode.PercentVbus);
		leftMotor.set(leftSpeed);
	}
	public void setRightMotorSpeed(double rightSpeed){
		rightMotor.changeControlMode(ControlMode.PercentVbus);
		rightMotor.set(rightSpeed);
	}

	public void setLeftPosition(int pos) {
		if (ControlMode.Position!=Robot.toteGrab.leftMotor.getControlMode()) {
			if(!Robot.toteGrab.leftMotor.isControlEnabled()){
				Robot.toteGrab.leftMotor.enableControl();
			}
			Robot.toteGrab.leftMotor.changeControlMode(ControlMode.Position);
		}
		leftMotor.set(pos);
	}

	public void setRightPosition(int pos) {
		if (ControlMode.Position!=Robot.toteGrab.rightMotor.getControlMode()) {
			if(!Robot.toteGrab.rightMotor.isControlEnabled()){
				Robot.toteGrab.rightMotor.enableControl();
			}
			Robot.toteGrab.rightMotor.changeControlMode(ControlMode.Position);
		}
		rightMotor.set(pos);
	}
	public CANTalon getLeftMotor(){
		return (CANTalon) this.leftMotor;
	}
	public CANTalon getRightMotor(){
		return (CANTalon) this.rightMotor;
	}
	public void stop(){
		this.setLeftMotorSpeed(0);
		this.setRightMotorSpeed(0);
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
}

