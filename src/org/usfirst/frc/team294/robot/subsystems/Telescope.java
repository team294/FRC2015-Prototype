package org.usfirst.frc.team294.robot.subsystems;

import org.usfirst.frc.team294.robot.Robot;
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
	public static enum TelescopePosition {
		PICKUP,
		CARRY,
		GROUND_1TOTE,
		GROUND_2TOTE,
		GROUND_3TOTE,
		STEP_1TOTE,
		STEP_2TOTE,
		STEP_3TOTE,
		SCORE_1TOTE,
		SCORE_2TOTE,
		SCORE_3TOTE
	}
	
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

	int closedLoopPos;
	TelescopePosition closedLoopPosEnum = TelescopePosition.CARRY;
	
	public void setPositionTarget(int pos)
	{
		closedLoopPos = pos;
	}
	public TelescopePosition getPositionTarget()
	{
		return closedLoopPosEnum;
	}
	public void setPositionTarget(TelescopePosition pos)
	{
		closedLoopPosEnum = pos;
    	closedLoopPos = getPotVal();
    	int bottom_pos = getReverseLimit();
    	int top_pos = getForwardLimit();
    	switch (pos) {
    	case PICKUP: closedLoopPos = bottom_pos; break;
    	case CARRY: closedLoopPos = bottom_pos + 40; break;
    	case GROUND_1TOTE: closedLoopPos = top_pos - (670 - 417); break;
    	case GROUND_2TOTE: closedLoopPos = top_pos - (670 - 525); break;
    	case GROUND_3TOTE: break;
    	case STEP_1TOTE: break;
    	case STEP_2TOTE: break;
    	case STEP_3TOTE: break;
    	case SCORE_1TOTE: break;
    	case SCORE_2TOTE: break;
    	case SCORE_3TOTE: break;
    	}
    	if (getPotVal() < closedLoopPos)
    		getMainTelescope().setProfile(0); //Values going up
    	else
    		getMainTelescope().setProfile(1); //Values going down
	}
	
	public void gotoPosition()
	{
		SmartDashboard.putString("teleMode", "pos");
		getMainTelescope().changeControlMode(ControlMode.Position);
		telescope.set(closedLoopPos);
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

