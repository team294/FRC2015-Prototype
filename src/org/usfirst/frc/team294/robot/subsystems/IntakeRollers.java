package org.usfirst.frc.team294.robot.subsystems;

import org.usfirst.frc.team294.robot.RobotMap;
import org.usfirst.frc.team294.robot.util.MultiCANTalon;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeRollers extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	

	int[] motors=new int[]{RobotMap.kPWM_intakeWheelMotorLeft, RobotMap.kPWM_intakeWheelMotorRight};
	SpeedController intakeMotor = new MultiCANTalon(motors);
	
	//DigitalInput buttonIntake = new DigitalInput(RobotMap.KDIN_buttonIntake);
	//LimitSwitchTrigger buttonIntakeHit = new LimitSwitchTrigger(buttonIntake);
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
		//buttonIntakeHit.whileActive(new IntakeStop());
	}
	
	public boolean getButton()
	{
		return false;
		//return buttonIntakeHit.get();
	}

    public void stop()
    {
    	intakeMotor.set(0);
    } 
}

