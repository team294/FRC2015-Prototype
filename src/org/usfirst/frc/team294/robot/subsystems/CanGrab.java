package org.usfirst.frc.team294.robot.subsystems;

import org.usfirst.frc.team294.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CanGrab extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	Solenoid canPiston = new Solenoid(RobotMap.kSOL_canPiston);
	private boolean open = true;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public boolean isOpen()
    {
    	return open;
    }
    
    public void grab()
    {
    	canPiston.set(true);
    	open = false;
    }
    public void release()
    {
    	canPiston.set(false);
    	open = true;
    }
}

