package org.usfirst.frc.team294.robot.subsystems;

import org.usfirst.frc.team294.robot.RobotMap;
import org.usfirst.frc.team294.robot.util.PotLimitedSpeedController;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ToteGrab extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
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
    	
    	open = true;
    }
    
    public void actuateClose()
    {
    	
    	open = false;
    }
}

