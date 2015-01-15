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
	public static int kPWM_leftMotor1 = 0;
 	public static int kPWM_leftMotor2 = 1;
 	public static int kPWM_rightMotor1 = 3;
 	public static int kPWM_rightMotor2 = 4;

    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
     
  // PWM
 	public static int kPWM_intakeWheelMotor = 7;
 	public static int kPWM_elevator = 8;

 	// Digital Inputs
 	public static int kDIN_leftDriveEncoderA = 0;
 	public static int kDIN_leftDriveEncoderB = 1;
 	public static int kDIN_rightDriveEncoderA = 2;
 	public static int kDIN_rightDriveEncoderB = 3;
 	public static int kDIN_toteLimitIntake = 4;
 	public static int kDIN_canLimitIntake = 5;
 	public static int kDIN_totePressurePlate = 6;
 	public static int kDIN_kickerEncoderB = 7;
 	public static int kDIN_kickerResetLimitSwitch = 8;
 	public static int kDIN_buttonIntake = 9;

 	// Analog Inputs
 	public static int kAIN_elevatorPot = 0;


 	// Solenoids
 	public static int kSOL_shifterPiston_reverse = 0;
 	public static int kSOL_shifterPiston_forward = 1;
 	public static int kSOL_totePistonModule = 1;
 	public static int kSOL_totePiston_reverse = 3;
 	public static int kSOL_totePiston_forward = 2;
 	public static int kSOL_canPistonModule = 1;
 	public static int kSOL_canPiston = 4;
 	public static int kSOL_system_module = 1;
 	public static int kSOL_system = 0;
	public static int kSOL_shiftLight;//TODO
	public static int kAIN_rangeFinder;//TODO
	public static int kSOL_rangeLight;//TODO
}
