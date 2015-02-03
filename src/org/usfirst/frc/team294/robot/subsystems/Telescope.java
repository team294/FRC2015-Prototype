package org.usfirst.frc.team294.robot.subsystems;

import org.usfirst.frc.team294.robot.RobotMap;
//import org.usfirst.frc.team294.robot.subsystems.Pivot.Setpoint;
//import org.usfirst.frc.team294.robot.subsystems.Pivot.Setpoint;
import org.usfirst.frc.team294.robot.util.MultiCANTalon;
import org.usfirst.frc.team294.robot.util.PotLimitedSpeedController;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Telescope extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	int[] telescopeMotors = {RobotMap.kPWM_telescope1,RobotMap.kPWM_telescope2};
	SpeedController telescope = new MultiCANTalon(telescopeMotors);
	
	
	AnalogInput telescopePot = new AnalogInput(RobotMap.kAIN_telescopePot);
	PotLimitedSpeedController teleMotor = new PotLimitedSpeedController(telescope, telescopePot, "pivMinLimit", "pivMaxLimit");
	//SpeedController pivotMotor=pivotMotorUnlimited;

	public Telescope() {
		// Use these to get going:
		// setSetpoint() -  Sets where the PID controller should move the system
		//                  to
		// enable() - Enables the PID controller.
		
		//super(Preferences.getInstance().getDouble("pivP", 0.0),
		//		Preferences.getInstance().getDouble("pivI", 0.0),
		//		Preferences.getInstance().getDouble("pivD", 0.0));
		System.out.println("limit="+Preferences.getInstance().getDouble("pivMinLimit", 0.0));		
		System.out.println("limit="+Preferences.getInstance().getDouble("pivMaxLimit", 0.0));
		
		getMainTelescope().setFeedbackDevice(FeedbackDevice.AnalogPot);
		//getMainTelescope().reverseSensor(true);
		//getMainTelescope().changeControlMode(ControlMode.Position);
		getMainTelescope().setPID(20, 0.0, 0.0, 0.0, 0, 0.0, 0); //gains going up
		getMainTelescope().setPID(4, 0.0, 0.0, 0.0, 0, 0.0, 1); //gains going down, have to change this!
		
		//setInputRange(Preferences.getInstance().getDouble("pivMinLimit", 0.0),
		//		Preferences.getInstance().getDouble("pivMaxLimit", 5.0));
		//setOutputRange(-0.75, 0.75);
		//setTolerance(0.1);
		//setAbsoluteTolerance(0.03);
	//	teleMotor.setInverted(true);
	//	teleMotor.setScale(1.0/200.0);
	}
	
	public CANTalon getMainTelescope()
	{
		return ((MultiCANTalon) telescope).getCANTalon(0);
	}

	/*
	public void setForwardSoftLimitTel(MultiCANTalon tel, int limit){
		for(int x : telescopeMotors){
			(tel.getCANTalon(x)).setForwardSoftLimit(limit);
			(tel.getCANTalon(x)).enableForwardSoftLimit(true);
		}
		
	}
	public void setReverseSoftLimitTel(MultiCANTalon tel, int limit){
		for(int x : telescopeMotors){
			(tel.getCANTalon(x)).setReverseSoftLimit(limit);
			(tel.getCANTalon(x)).enableReverseSoftLimit(true);
		}
		
	}
	*/
	public int getPotCanVal(){
		return (getMainTelescope()).getAnalogInPosition();
	}

	private int forwardLimit=1023;
	private int reverseLimit=0;
	
	public int getForwardLimit(){
		return this.forwardLimit;
	}
	public int getReverseLimit(){
		return this.reverseLimit;
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	

    	}

	public void stop() {
		//disable();
	}
	
	public void setManual(double value) {
		getMainTelescope().changeControlMode(ControlMode.PercentVbus);
		telescope.set(value);
	}
	
	public void setPosition(int pos) {
		getMainTelescope().changeControlMode(ControlMode.Position);
		telescope.set(pos);
	}

	public void setTelescopeSpeed(double d) {
		this.getMainTelescope().set(d);
	}
	
	public void setTelescopeSpeed2(double d)
	{
		telescope.set(d);
		System.out.println("Motors running at " + d);
	}

	public double getPotVal() {
		// TODO Auto-generated method stub
		//return this.telescopePot.getValue();
		return getMainTelescope().getPosition();
	}
	
}

