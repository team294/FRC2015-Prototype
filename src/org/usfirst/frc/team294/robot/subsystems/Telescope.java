package org.usfirst.frc.team294.robot.subsystems;

import org.usfirst.frc.team294.robot.RobotMap;
//import org.usfirst.frc.team294.robot.subsystems.Pivot.Setpoint;
//import org.usfirst.frc.team294.robot.subsystems.Pivot.Setpoint;
import org.usfirst.frc.team294.robot.util.MultiCANTalon;
import org.usfirst.frc.team294.robot.util.PotLimitedSpeedController;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Telescope extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	int[] telescopeMotors = {RobotMap.kPWM_telescope1,RobotMap.kPWM_telescope2};
	SpeedController telescope = new MultiCANTalon(telescopeMotors);
	AnalogInput telePot = new AnalogInput(RobotMap.kAIN_telescopePot); //3715 at bottom, 3050 at top
	//AnalogInput telescopePot = new AnalogInput(RobotMap.kAIN_telescopePot);
	
	//PotLimitedSpeedController teleMotor = new PotLimitedSpeedController(telescope, telescopePot, "pivMinLimit", "pivMaxLimit");
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
		
		//((MultiCANTalon) telescope).SetInverted(1, true);
		//getMainTelescope().changeControlMode(ControlMode.Position);
		//getMainTelescope().setPID(1.0, 1.0, 1.0); //TODO
		
		//setInputRange(Preferences.getInstance().getDouble("pivMinLimit", 0.0),
		//		Preferences.getInstance().getDouble("pivMaxLimit", 5.0));
		//setOutputRange(-0.75, 0.75);
		//setTolerance(0.1);
		//setAbsoluteTolerance(0.03);
	//	teleMotor.setInverted(true);
	//	teleMotor.setScale(1.0/200.0);
	}
	
	public void setTelescopeSpeed(double speed)
	{
		((MultiCANTalon) telescope).set(speed);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public double getPotVal()
    {
    return telePot.getValue();
    }
   // protected double returnPIDInput() {
	//	return telescopePot.getAverageValue() / 200.0;
	//}
	
	//protected void usePIDOutput(double output) {
	//	teleMotor.set(-output);
	//}

	public void stop() {
		//disable();
	}
	
	/*public void setManual(double value) {
		if (getMainTelescope().isControlEnabled())
			getMainTelescope().changeControlMode(ControlMode.Speed);
		getMainTelescope().set(value);
		//getMainTelescope().disableControl();
	}
	*/
	/*public boolean isIntakeUpOk() {
		double pivStartSetpoint = Preferences.getInstance().getDouble("pivStartSetpoint", Double.POSITIVE_INFINITY);
		if (pivStartSetpoint == Double.POSITIVE_INFINITY)
			return false;
		return getPosition() < (pivStartSetpoint+2);
	}
	*/
/*	public void goHome() {
		setPrefSetpoint(Setpoint.kStart);
	} */

	

	/*private String getSetpointPrefName(Setpoint setpoint) {
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
	} */
    
 /*   public void setPrefSetpoint(Setpoint setpoint) {
	
		setPrefSetpoint(getSetpointPrefName(setpoint));
		synchronized (this) {
			m_setpoint = setpoint;
		}
	}
	*/
/*	public synchronized Setpoint getPrefSetpoint() {
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
	} */
	
	//Add other tote levels
//	public synchronized boolean isIntake() {
//		return m_setpoint == Setpoint.kIntake;
//	}
	
	/*public boolean onTarget() {
		//logging.debug("cur: %.2f setpoint: %.2f error: %.2f ontarget: %s",
		//		self.pidSource.PIDGet(),
		//		self.pid.GetSetpoint(),
		//		self.pid.GetError(),
		//		self.pid.OnTarget())
		return super.onTarget();
	} */
	
	/*public void tweakSetpoint(double amt) {
		if (getPIDController().isEnable()) {
			double oldSetpoint = getSetpoint();
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
			setSetpoint(newSetpoint);
		} else {
			setSetpoint(getPosition() + amt);
			getPIDController().reset();
			enable();
		}
	}*/

	/*public void tweakDown() {
		tweakSetpoint(4);
	}
	
	public void tweakUp() {
		tweakSetpoint(-4);
	}*/
}

