	package org.usfirst.frc.team294.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
	public static int kPWM_leftMotor1 = 1;
 	public static int kPWM_leftMotor2 = 2;
 	public static int kPWM_rightMotor1 = 3;
 	public static int kPWM_rightMotor2 = 4;
 	

    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
     
  // PWM

 	public static int kPWM_telescope1 = 5;
 	public static int kPWM_telescope2 = 6;
 	
 	public static int kPWM_toteCloseIntake = 7;
 	public static int kPWM_toteCloseIntake2 = 8;
 	
 	public static int kPWM_intakeWheelMotorRight = 9;
 	public static int kPWM_intakeWheelMotorLeft = 10;

 	// Digital Inputs
 	public static int kDIN_leftDriveEncoderA = 0;
 	public static int kDIN_leftDriveEncoderB = 1;
 	public static int kDIN_rightDriveEncoderA = 2;
 	public static int kDIN_rightDriveEncoderB = 3;
 	//public static int KDIN_buttonIntake = 1;

 	// Analog Inputs
 	public static int kAIN_telescopePot = 3;

 	// Solenoids

 	public static int kSOL_canPiston = 4;
 	public static int kSOL_IntakePistons1 = 2;
 	public static int kSOL_IntakePistons2 = 0;
 	public static int kSOL_OtherPiston1 = 1;
 	public static int kSOL_OtherPiston2 = 3;
 	
 	public static int kSOL_system_module = 1;
 	public static int kSOL_system = 0;
	public static int kSOL_shiftLight;//TODO
	public static int kAIN_rangeFinder;//TODO
	public static int kSOL_rangeLight;//TODO
	
}
