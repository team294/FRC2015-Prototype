package org.usfirst.frc.team294.robot.subsystems;

import org.usfirst.frc.team294.robot.RobotMap;
import org.usfirst.frc.team294.robot.util.MultiCANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeRollerArms extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	int[] motors=new int[]{RobotMap.kPWM_intakeWheelMotorLeft, RobotMap.kPWM_intakeWheelMotorRight};
	SpeedController intakeMotor = new MultiCANTalon(motors);
	DoubleSolenoid thing = new DoubleSolenoid(RobotMap.kSOL_IntakePistons1, RobotMap.kSOL_IntakePistons2);
	
	private boolean bothOpen=false;
	//DigitalInput buttonIntake = new DigitalInput(RobotMap.KDIN_buttonIntake);
	//LimitSwitchTrigger buttonIntakeHit = new LimitSwitchTrigger(buttonIntake);
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
		//buttonIntakeHit.whileActive(new IntakeStop());
		this.close();
	}
	
	public boolean isOpen(){
		return bothOpen;
	}
	
	public synchronized void close(){
		thing.set(DoubleSolenoid.Value.kReverse);
	}
	
	public synchronized void open(){
		thing.set(DoubleSolenoid.Value.kForward);
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

