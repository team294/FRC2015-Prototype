package org.usfirst.frc.team294.robot.subsystems;

import org.usfirst.frc.team294.robot.RobotMap;
import org.usfirst.frc.team294.robot.util.MultiCANTalon;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Telescope extends Subsystem {

	int[] telescopeMotors = {RobotMap.telescope1,RobotMap.telescope2};
	MultiCANTalon telescope = new MultiCANTalon(telescopeMotors);


	public Telescope()
	{
		System.out.println("limit="+Preferences.getInstance().getDouble("pivMinLimit", 0.0));		
		System.out.println("limit="+Preferences.getInstance().getDouble("pivMaxLimit", 0.0));

		getMainTelescope().setFeedbackDevice(FeedbackDevice.AnalogPot);
		getMainTelescope().setPID(20, 0.0, 0.0, 0.0, 0, 0.0, 0); //gains going up
		getMainTelescope().setPID(4, 0.0, 0.0, 0.0, 0, 0.0, 1); //TODO gains going down, have to change this!
	}

	public CANTalon getMainTelescope()
	{
		return telescope.getCANTalon(0);
	}

	public int getPotCanVal()
	{
		return (getMainTelescope()).getAnalogInPosition();
	}

	private int forwardLimit=1023;
	private int reverseLimit=0;

	public int getForwardLimit()
	{
		return this.forwardLimit;
	}
	public int getReverseLimit()
	{
		return this.reverseLimit;
	}

	public void initDefaultCommand()
	{

	}

	public void stop()
	{
		this.telescope.set(0);
	}

	public void setManual(double value) 
	{
		getMainTelescope().changeControlMode(ControlMode.PercentVbus);
		telescope.set(value);
	}

	public void setPosition(int pos)
	{
		getMainTelescope().changeControlMode(ControlMode.Position);
		telescope.set(pos);
	}
	
	public void setTelescopeSpeed(double d)
	{
		telescope.set(d);
	}

	public double getPotVal() 
	{
		return getMainTelescope().getPosition();
	}

}

