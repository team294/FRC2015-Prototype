package org.usfirst.frc.team294.robot.subsystems;

import org.usfirst.frc.team294.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ToteGrab extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	//DoubleSolenoid totePiston = new DoubleSolenoid(RobotMap.kSOL_totePistonModule, RobotMap.kSOL_totePiston_forward, RobotMap.kSOL_totePiston_reverse);
	Solenoid totePiston = new Solenoid(RobotMap.kSOL_totePiston);
	private boolean open = true;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public boolean isOpen()
    {
    	return open;
    }
    
    public void actuateOpen()
    {
    	totePiston.set(true);
    	open = true;
    }
    
    public void actuateClose()
    {
    	totePiston.set(false);
    	open = false;
    }
}

