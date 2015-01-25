
package org.usfirst.frc.team294.robot;


//import org.usfirst.frc.team294.robot.commands.IntakeReverse;

//import org.usfirst.frc.team294.robot.commands.IntakeStop;

import org.usfirst.frc.team294.robot.commands.TankDriveWithJoysticks;
import org.usfirst.frc.team294.robot.subsystems.CanGrab;
import org.usfirst.frc.team294.robot.subsystems.DoublePistonIntake;
import org.usfirst.frc.team294.robot.subsystems.Drivetrain;
import org.usfirst.frc.team294.robot.subsystems.IntakeRollers;
import org.usfirst.frc.team294.robot.subsystems.RangeFinder;
import org.usfirst.frc.team294.robot.subsystems.Telescope;
//import org.usfirst.frc.team294.robot.subsystems.ToteGrab;




import org.usfirst.frc.team294.robot.subsystems.ToteMotorClose;
import org.usfirst.frc.team294.robot.subsystems.compress;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends IterativeRobot {

	public static PowerDistributionPanel pdp;
	public static Gyro gyro;

	public static Drivetrain drivetrain;
	//public static ToteGrab toteGrab;
	public static CanGrab canGrab;
	public static Telescope telescope;
	public static IntakeRollers intakeRollers;
	public static RangeFinder rangeFinder;
	public static DoublePistonIntake doublePistonIntake;
	public static compress compressor;
	public static ToteMotorClose toteMotorClose;

	public static OI oi;

	public static boolean disabledTrigPressed = false;
	public static boolean disabledButton2Pressed = false;
	public static boolean disabledButton5Pressed = false;
	public static boolean disabledButton4Pressed = false;
	public static boolean disabledButton3Pressed = false;

	public static int autoMode = 0;

	public static float autoDelay = 0;

	public static int startPosition = 0;
	
    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	pdp = new PowerDistributionPanel();
    	
    	//toteGrab = new ToteGrab();
    	doublePistonIntake = new DoublePistonIntake();
    	canGrab = new CanGrab();
    	drivetrain = new Drivetrain();
    	telescope = new Telescope();
    	intakeRollers = new IntakeRollers();
    	rangeFinder = new RangeFinder();
    	compressor = new compress();
    	toteMotorClose = new ToteMotorClose();
    	
    	/*SmartDashboard.putData(drivetrain);
		SmartDashboard.putData(toteGrab);
		SmartDashboard.putData(canGrab);
		SmartDashboard.putData(intakeRollers);
		SmartDashboard.putData(rangeFinder);
		SmartDashboard.putData(telescope);
		//SmartDashboard.putData(); */

		/*SmartDashboard.putData(new IntakeRun());
		SmartDashboard.putData(new IntakeReverse());
		SmartDashboard.putData(new IntakeStop());
		SmartDashboard.putData(new JawClose());
		SmartDashboard.putData(new JawOpen());
		SmartDashboard.putData(new ShiftDown());
		SmartDashboard.putData(new ShiftUp());
		SmartDashboard.putData(new Engage());
		SmartDashboard.putData(new Shoot()); */
    	
		oi = new OI();
		//toteGrab = new ToteGrab();
        // instantiate the command used for the autonomous period
      
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        TankDriveWithJoysticks tank = new TankDriveWithJoysticks();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
