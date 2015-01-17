package org.usfirst.frc.team294.robot.subsystems;

import org.usfirst.frc.team294.robot.RobotMap;

//import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ToteGrab extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	CANTalon leftMotor = new CANTalon(RobotMap.kPWM_intakeMotorLeft);
	CANTalon rightMotor = new CANTalon(RobotMap.kPWM_intakeMotorRight);
	
	//AnalogPotentiometer left = new AnalogPotentiometer(RobotMap.kAIN_leftIntakePot);
	//AnalogPotentiometer right = new AnalogPotentiometer(RobotMap.kAIN_rightIntakePot);
	
	private int voltageThreshold=5;//TODO
	private int isOpenDistance=100;//TODO

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
    	leftMotor.set(1);
    }
    public void panLeftLeft(){
    	if(isBlocked(leftMotor))return;
    	leftMotor.set(-1);
    }
    public boolean isBlocked(CANTalon motor){
    	if(motor.getBusVoltage()>voltageThreshold)return true;
    	return false;
    }
    public void setLeftMotor(int s){
    	if(isBlocked(leftMotor))return;
    	leftMotor.set(s);
    }
    public void setRightMotorSpeed(int s){
    	if(isBlocked(rightMotor))return;
    	rightMotor.set(s);
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
    public boolean isOpen(){
    	if(Math.abs(leftMotor.getAnalogInPosition()-rightMotor.getAnalogInPosition())>isOpenDistance)return true;
    	return false;
    }
}

