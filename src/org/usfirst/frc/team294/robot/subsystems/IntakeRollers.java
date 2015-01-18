package org.usfirst.frc.team294.robot.subsystems;

import org.usfirst.frc.team294.robot.RobotMap;
//import org.usfirst.frc.team294.robot.util.SwitchSpeedController;

import org.usfirst.frc.team294.robot.commands.IntakeStop;
import org.usfirst.frc.team294.robot.triggers.LimitSwitchTrigger;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeRollers extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	SpeedController intakeMotor = new CANTalon(RobotMap.kPWM_intakeWheelMotor);
	
	DigitalInput buttonIntake = new DigitalInput(RobotMap.kDIN_buttonIntake);
	LimitSwitchTrigger buttonIntakeHit = new LimitSwitchTrigger(buttonIntake);
	
	private int runmode = 0;
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
		buttonIntakeHit.whileActive(new IntakeStop());
	}
    
    public int runMode() //Made to return state of intake motor
    {
    	return runmode;
    }
    
    public void runIn()
    {
    	intakeMotor.set(0.5);
    	runmode = 1;
    }
    public void runOut()
    {
    	intakeMotor.set(-0.5);
    	runmode = -1;
    }
    public void stop()
    {
    	intakeMotor.set(0);
    	runmode = 0;
    }
}

