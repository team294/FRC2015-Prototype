package org.usfirst.frc.team294.robot.subsystems;

import org.usfirst.frc.team294.robot.Robot;
import org.usfirst.frc.team294.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
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

	private CANTalon rightMotor = new CANTalon(RobotMap.toteCloseIntakeRight);
	private CANTalon leftMotor = new CANTalon(RobotMap.toteCloseIntakeLeft);
	
	public AnalogInput distLeft = new AnalogInput(RobotMap.kAIN_distLeft);


	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	public void initDefaultCommand() {

	}

	//public void periodicDefaultCommand()

	
	
	public ToteGrabber()
	{
		leftMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rightMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		leftMotor.reverseSensor(true);
		rightMotor.reverseSensor(true);
		
		rightMotor.setPID(4.0, 0, 0);
		leftMotor.setPID(4.0, 0, 0);
		
		rightMotor.enableLimitSwitch(true, false);
		leftMotor.enableLimitSwitch(true, false);
		
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
		leftMotor.changeControlMode(ControlMode.Position);
		leftMotor.set(-pos);
	}

	public void setRightPosition(int pos) {
		rightMotor.changeControlMode(ControlMode.Position);
		rightMotor.set(-pos);
	}
	
	public int getRightPos()
	{
		return rightMotor.getEncPosition();
	}
	
	public int getLeftPos()
	{
		return leftMotor.getEncPosition();
	}
	public CANTalon getLeftMotor(){
		return this.leftMotor;
	}
	public CANTalon getRightMotor(){
		return this.rightMotor;
	}
	public void stop(){
		this.setLeftMotorSpeed(0);
		this.setRightMotorSpeed(0);
	}
	
	public void resetRightEnc()
	{
		
		rightMotor.changeControlMode(ControlMode.Position);
		
		rightMotor.setPosition(0);
	}
	
	public void resetLeftEnc()
	{
		
		leftMotor.changeControlMode(ControlMode.Position);
		leftMotor.setPosition(0);
	}
	
	public boolean getRightLimit()
	{
		return rightMotor.isFwdLimitSwitchClosed();
	}
	public boolean getLeftLimit()
	{
		return leftMotor.isFwdLimitSwitchClosed();
	}

	public double getLeftDistanceSensor()
	{
		return distLeft.getAverageVoltage();
	}

}

