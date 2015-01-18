package org.usfirst.frc.team294.robot.subsystems;

import org.usfirst.frc.team294.robot.RobotMap;


//import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ToteGrab extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public CANTalon leftMotor = new CANTalon(RobotMap.kPWM_intakeMotorLeft);
	public CANTalon rightMotor = new CANTalon(RobotMap.kPWM_intakeMotorRight);
	
	//AnalogPotentiometer left = new AnalogPotentiometer(RobotMap.kAIN_leftIntakePot);
	//AnalogPotentiometer right = new AnalogPotentiometer(RobotMap.kAIN_rightIntakePot);
	
	private int voltageThreshold=5;//TODO
	private int preferredOpenDistance=420+69;//TODO

    public int getPreferredOpenDistance() {
		return preferredOpenDistance;
	}

	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void panRightRight(){
    	if(isBlocked(rightMotor))return;
    	rightMotor.set(1);
    }
    public void panRightLeft(){
    	if(isBlocked(rightMotor))return;
    	rightMotor.set(-1);
    }
    public void panLeftRight(){
    	if(isBlocked(leftMotor))return;
    	leftMotor.set(-1);
    }
    public void panLeftLeft(){
    	if(isBlocked(leftMotor))return;
    	leftMotor.set(1);
    }
    public boolean isBlocked(CANTalon motor){
    	if(motor.getBusVoltage()>voltageThreshold)return true;
    	return false;
    }
    public void setLeftMotorSpeed(float d){
    	if(isBlocked(leftMotor))return;
    	leftMotor.set(d);
    }
    public void setRightMotorSpeed(float rightSpeed){
    	if(isBlocked(rightMotor))return;
    	rightMotor.set(rightSpeed);
    }
    public CANTalon getLeftMotor(){
    	return this.leftMotor;
    }
    public CANTalon getRightMotor(){
    	return this.rightMotor;
    }
    public int getVoltageThreshold(){
    	return this.voltageThreshold;
    }
    public int getRightPosition()
    {
    	return leftMotor.getAnalogInPosition();
    }
    public int getLeftPosition()
    {
    	return rightMotor.getAnalogInPosition();
    }
    
    public boolean isOpen(){
    	if(Math.abs(leftMotor.getAnalogInPosition()-rightMotor.getAnalogInPosition())>=preferredOpenDistance)return true;
    	return false;
    }
    
    public void centerIntake()
    {
    	leftMotor.changeControlMode(ControlMode.Position);
		leftMotor.setPID(1.0, 1.0, 1.0); //TODO
		rightMotor.changeControlMode(ControlMode.Position);
		rightMotor.setPID(1.0, 1.0, 1.0); //TODO
    }

}

