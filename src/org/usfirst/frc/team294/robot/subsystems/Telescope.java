package org.usfirst.frc.team294.robot.subsystems;

import org.usfirst.frc.team294.robot.RobotMap;
import org.usfirst.frc.team294.robot.util.MultiCANTalon;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		getMainTelescope().setPID(30, 0.05, 0.0, 0.0, 20, 5.0, 0); //gains going up
		getMainTelescope().setPID(15, 0.05, 0.0, 0.0, 20, 5.0, 1); //TODO gains going down, have to change this!
	}

	public CANTalon getMainTelescope()
	{
		return telescope.getCANTalon(0);
	}

	public int getPotCanVal()
	{
		return (getMainTelescope()).getAnalogInPosition();
	}

	private int forwardLimit=670;
	private int reverseLimit=280;

	public int getForwardLimit()
	{
		return this.forwardLimit;
	}
	public int getReverseLimit()
	{
		return this.reverseLimit; // FIXME: get from CANTalon
	}

	public void initDefaultCommand()
	{

	}

	public void stop()
	{
		SmartDashboard.putString("teleMode", "stop");
		getMainTelescope().changeControlMode(ControlMode.PercentVbus);
		this.telescope.set(0);
	}

	public void setManual(double value) 
	{
		SmartDashboard.putString("teleMode", "manual");
		getMainTelescope().changeControlMode(ControlMode.PercentVbus);
		telescope.set(value);
	}

	public void setPosition(int pos)
	{
		SmartDashboard.putString("teleMode", "pos");
		getMainTelescope().changeControlMode(ControlMode.Position);
		telescope.set(pos);
	}
	
	public boolean onTarget()
	{
		return Math.abs(getMainTelescope().getClosedLoopError()) <= 2;
	}
	public int getPotVal() 
	{
		return (int)getMainTelescope().getPosition();
	}

}

