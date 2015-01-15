package org.usfirst.frc.team294.robot.subsystems;

import org.usfirst.frc.team294.robot.RobotMap;
import org.usfirst.frc.team294.robot.subsystems.Pivot.Setpoint;
import org.usfirst.frc.team294.robot.util.MultiCANTalon;
import org.usfirst.frc.team294.robot.util.PotLimitedSpeedController;

import edu.wpi.first.wpilibj.AnalogInput;
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
	AnalogInput pivotPot = new AnalogInput(RobotMap.kAIN_telescopePot);
	
	PotLimitedSpeedController pivotMotor = new PotLimitedSpeedController(telescope, pivotPot, "pivMinLimit", "pivMaxLimit");
	//SpeedController pivotMotor=pivotMotorUnlimited;
	
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
	Setpoint m_setpoint;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

