	package org.usfirst.frc.team294.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public static int leftMotor1 = 1;
 	public static int leftMotor2 = 2;
 	public static int rightMotor1 = 3;
 	public static int rightMotor2 = 4;
 	

    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
     
  // PWM

 	public static int telescope1 = 5;
 	public static int telescope2 = 6;
 	
 	public static int toteCloseIntake = 7;
 	public static int toteCloseIntake2 = 8;
 	
 	public static int intakeWheelMotorRight = 9;
 	public static int intakeWheelMotorLeft = 10;

 	// Digital Inputs
 	public static int kDIN_leftDriveEncoderA = 1;
 	public static int kDIN_leftDriveEncoderB = 2;
 	public static int kDIN_rightDriveEncoderA = 3;
 	public static int kDIN_rightDriveEncoderB = 4;
 	//public static int KDIN_buttonIntake = 1;

 	// Analog Inputs

 	// Solenoids

 	public static int kSOL_canPiston = 4;
 	public static int kSOL_IntakePistons1 = 3;
 	public static int kSOL_IntakePistons2 = 1;
 	
 	public static int kSOL_system_module = 1;
 	public static int kSOL_system = 0;
	public static int kSOL_shiftLight;//TODO
	public static int kAIN_rangeFinder;//TODO
	public static int kSOL_rangeLight;//TODO
	
}
