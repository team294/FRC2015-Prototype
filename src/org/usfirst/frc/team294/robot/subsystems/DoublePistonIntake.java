package org.usfirst.frc.team294.robot.subsystems;

import org.usfirst.frc.team294.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DoublePistonIntake extends Subsystem {
    
	DoubleSolenoid pistonLeft = new DoubleSolenoid(RobotMap.kSOL_IntakePistons1, RobotMap.kSOL_IntakePistons2);
	DoubleSolenoid pistonOther = new DoubleSolenoid(RobotMap.kSOL_OtherPiston1, RobotMap.kSOL_OtherPiston2);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void pistonOut()
    {
    	pistonLeft.set(DoubleSolenoid.Value.kForward);
    }
    public void pistonIn()
    {
    	pistonLeft.set(DoubleSolenoid.Value.kReverse);
    }
    public void pistonOtherOut()
    {
    	pistonOther.set(DoubleSolenoid.Value.kForward);
    }
    public void pistonOtherIn()
    {
    	pistonOther.set(DoubleSolenoid.Value.kReverse);
    }
}

