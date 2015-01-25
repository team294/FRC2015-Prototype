package org.usfirst.frc.team294.robot.subsystems;

import org.usfirst.frc.team294.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ToteGrabber extends Subsystem {

		// Put methods for controlling this subsystem
		// here. Call these from Commands.

		public SpeedController leftMotor = new CANTalon(RobotMap.kPWM_toteCloseIntake);
		public SpeedController rightMotor = new CANTalon(RobotMap.kPWM_toteCloseIntake2);

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
		public boolean isBlocked(SpeedController rightMotor2){
			if(((CANTalon) rightMotor2).getBusVoltage()>voltageThreshold)return true;
			return false;
		}
		public void setLeftMotorSpeed(double leftSpeed){
			if(isBlocked(leftMotor))
			{
				System.out.println("left motor blocked");
				return;
			}
			leftMotor.set(leftSpeed);
		}
		public void setRightMotorSpeed(double rightSpeed){
			if(isBlocked(rightMotor))
			{
				System.out.println("right motor blocked");
				return;
			}
			rightMotor.set(rightSpeed);
		}
		public void setRightTest(double rightSpeed)
		{
			rightMotor.set(rightSpeed);
		}
		public void setLeftTest(double leftSpeed)
		{
			leftMotor.set(leftSpeed);
		}
		public CANTalon getLeftMotor(){
			return (CANTalon) this.leftMotor;
		}
		public CANTalon getRightMotor(){
			return (CANTalon) this.rightMotor;
		}
		public int getVoltageThreshold(){
			return this.voltageThreshold;
		}
		public int getRightPosition()
		{
			return ((CANTalon) leftMotor).getAnalogInPosition();
		}
		public int getLeftPosition()
		{
			return ((CANTalon) rightMotor).getAnalogInPosition();
		}

		public boolean isOpen(){
			if(Math.abs(((CANTalon) leftMotor).getAnalogInPosition()-((CANTalon) rightMotor).getAnalogInPosition())>=preferredOpenDistance)return true;
			return false;
		}

		public void centerIntake()
		{
			((CANTalon) leftMotor).changeControlMode(ControlMode.Position);
			((CANTalon) leftMotor).setPID(1.0, 1.0, 1.0); //TODO
			((CANTalon) rightMotor).changeControlMode(ControlMode.Position);
			((CANTalon) rightMotor).setPID(1.0, 1.0, 1.0); //TODO
		}


		public void open()
		{
			this.setLeftMotorSpeed(1);
			this.setRightMotorSpeed(1);
			//intakeMotor.set(1);
		}

		public void close()
		{
			this.setLeftMotorSpeed(-1);
			this.setRightMotorSpeed(-1);
			//intakeMotor.set(-1);
		}
		public void stop()
		{
			this.setLeftMotorSpeed(0);
			this.setRightMotorSpeed(0);
			//intakeMotor.set(0);
		}
	}

