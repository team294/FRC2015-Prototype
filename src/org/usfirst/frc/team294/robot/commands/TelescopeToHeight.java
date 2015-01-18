package org.usfirst.frc.team294.robot.commands;

import org.usfirst.frc.team294.robot.Robot;
//import org.usfirst.frc.team294.robot.subsystems.Pivot;
import org.usfirst.frc.team294.robot.subsystems.Telescope;
//import org.usfirst.frc.team294.robot.subsystems.Telescope.Setpoint;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TelescopeToHeight extends Command {
	
	public enum Setpoint {
		kStart,
		k1Tote,
		k2Tote,
		k3Tote,
		k4Tote,
		k5Tote,
		kHumanLoad,
		kIntake
	}
	
	private int height;
	private double tolerance = 10;
	private Setpoint m_setpoint;
	
    public TelescopeToHeight(Setpoint setpoint) {
    	requires(Robot.telescope);
       m_setpoint = setpoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setPrefSetpoint(getSetpointPrefName(m_setpoint));
		System.out.println(m_setpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    public boolean onTarget() {
		//logging.debug("cur: %.2f setpoint: %.2f error: %.2f ontarget: %s",
		//		self.pidSource.PIDGet(),
		//		self.pid.GetSetpoint(),
		//		self.pid.GetError(),
		//		self.pid.OnTarget())
    	double error = Robot.telescope.getMainTelescope().get() - Preferences.getInstance().getDouble(getSetpointPrefName(getPrefSetpoint()), Double.POSITIVE_INFINITY);
		return (Math.abs(error) <= tolerance);
	} 
    
    private void setPrefSetpoint(String pref) {
		if (pref == null)
			return;
		double setp = Preferences.getInstance().getDouble(pref, Double.POSITIVE_INFINITY);
		if (setp == Double.POSITIVE_INFINITY)
			return;
		Robot.telescope.getMainTelescope().set(setp);//setSetpoint(setp);
		if (!(ControlMode.Position == Robot.telescope.getMainTelescope().getControlMode())) {
			if(!Robot.telescope.getMainTelescope().isControlEnabled())
				Robot.telescope.getMainTelescope().enableControl();
			Robot.telescope.getMainTelescope().changeControlMode(ControlMode.Position);
		}
	}
    private String getSetpointPrefName(Setpoint setpoint) {
		switch (setpoint) {
		case kStart:		return "telStartSetpoint";
		case k1Tote:		return "tel1ToteSetpoint";
		case k2Tote:		return "tel2ToteSetpoint";
		case k3Tote:		return "tel3ToteSetpoint";
		case k4Tote:		return "tel4ToteSetpoint";
		case k5Tote:		return "tel5ToteSetpoint";
		case kHumanLoad:	return "telHumanLoadSetpoint";
		case kIntake:		return "telIntakeSetpoint";
		default:			return null;
		}
	}
    
    public synchronized Setpoint getPrefSetpoint() {
		return m_setpoint;
	}
	
	public synchronized boolean is1Tote() {
		return m_setpoint == Setpoint.k1Tote;
	}
	
	public synchronized boolean is2Tote() {
		return m_setpoint == Setpoint.k2Tote;
	}
	
	public synchronized boolean is3Tote() {
		return m_setpoint == Setpoint.k3Tote;
	}
	
	public synchronized boolean is4Tote() {
		return m_setpoint == Setpoint.k4Tote;
	}
	public synchronized boolean is5Tote() {
		return m_setpoint == Setpoint.k1Tote;
	}
	
	public synchronized boolean kHumanLoad() {
		return m_setpoint == Setpoint.kHumanLoad;
	}
	
	//Add other tote levels
	public synchronized boolean isIntake() {
		return m_setpoint == Setpoint.kIntake;
	}
    
	public void tweakSetpoint(double amt) {
		if (Robot.telescope.getMainTelescope().getControlMode() == ControlMode.Position) {
			double oldSetpoint = Preferences.getInstance().getDouble(getSetpointPrefName(getPrefSetpoint()), Double.POSITIVE_INFINITY);
			double newSetpoint = oldSetpoint + amt;
			// Update preferences so the robot remembers it for next time
			String pref;
			synchronized (this) {
				// don't update start setpoint
				if (m_setpoint == Setpoint.kStart)
					return;
				pref = getSetpointPrefName(m_setpoint);
			}
			if (pref == null)
				return;
			Preferences.getInstance().putDouble(pref, newSetpoint);
			Preferences.getInstance().save();
			Robot.telescope.getMainTelescope().set(newSetpoint);
		} else {
			Robot.telescope.getMainTelescope().set(Robot.telescope.getMainTelescope().get() + amt);
			//getPIDController().reset();
			//enable();
		}
	}

	public void tweakDown() {
		tweakSetpoint(4);
	}
	
	public void tweakUp() {
		tweakSetpoint(-4);
	}
}
