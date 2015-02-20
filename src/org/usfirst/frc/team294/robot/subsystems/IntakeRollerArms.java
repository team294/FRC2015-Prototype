package org.usfirst.frc.team294.robot.subsystems;

import org.usfirst.frc.team294.robot.RobotMap;
import org.usfirst.frc.team294.robot.util.MultiCANTalon;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeRollerArms extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	int[] motors=new int[]{RobotMap.intakeWheelMotorLeft, RobotMap.intakeWheelMotorRight};
	MultiCANTalon intakeMotors = new MultiCANTalon(motors);
	DoubleSolenoid armPistons = new DoubleSolenoid(RobotMap.kSOL_IntakePistons1, RobotMap.kSOL_IntakePistons2);
	Solenoid secondStage = new Solenoid(RobotMap.kSOL_IntakePistons3);
	
	private boolean bothOpen=false;
	//DigitalInput buttonIntake = new DigitalInput(RobotMap.KDIN_buttonIntake);
	//LimitSwitchTrigger buttonIntakeHit = new LimitSwitchTrigger(buttonIntake);
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
		//buttonIntakeHit.whileActive(new IntakeStop());
		//this.close();
		intakeMotors.SetInverted(1, true);
	}
	
	public boolean isOpen(){
		return bothOpen;
	}
	
	public CANTalon getMainIntake()
	{
		return intakeMotors.getCANTalon(0);
	}
	
	public void setMotorSpeed(double speed)
	{
		System.out.println("hi");
		intakeMotors.set(speed);
	}
	
	public synchronized void close(){
		
		setMotorSpeed(-.8);
		/*if(!(armPistons.get() == DoubleSolenoid.Value.kReverse))
		{
		armPistons.set(DoubleSolenoid.Value.kReverse);
		}*/
	}
	
	public synchronized void openAll()
	{
		armPistons.set(DoubleSolenoid.Value.kForward);
		secondStage.set(true);
	}
	
	public synchronized void closeAll()
	{
		armPistons.set(DoubleSolenoid.Value.kReverse);
		secondStage.set(false);
	}
	
	public synchronized void openMid()
	{
		armPistons.set(DoubleSolenoid.Value.kForward);
		secondStage.set(false);
	}
	
	public synchronized void openMotor(){
		setMotorSpeed(-.8);
		
	}
	public synchronized void closeMotor(){
		setMotorSpeed(.8);
		
	}
	
	public boolean getButton()
	{
		return false;
		//return buttonIntakeHit.get();
	}

    public void stop()
    {
    	intakeMotors.set(0);
    	//armPistons.free();
    } 
}

