package org.usfirst.frc.team294.robot.subsystems;

import org.usfirst.frc.team294.robot.RobotMap;
//import org.usfirst.frc.team294.robot.util.SwitchSpeedController;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeRollers extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	SpeedController intakeMotor = new CANTalon(RobotMap.kPWM_intakeWheelMotor);
	private int runmode = 0;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public int runMode() //Made to return state of intake motor
    {
    	return runmode;
    }
    
    public void runIn()
    {
    	intakeMotor.set(1.0);
    	runmode = 1;
    }
    public void runOut()
    {
    	intakeMotor.set(-1.0);
    	runmode = -1;
    }
    public void stop()
    {
    	intakeMotor.set(0);
    	runmode = 0;
    }
}

