
package org.usfirst.frc.team294.robot;

import org.usfirst.frc.team294.robot.commands.autoMode.MultiLooper;
import org.usfirst.frc.team294.robot.commands.autoMode.Navigator;
import org.usfirst.frc.team294.robot.commands.autoMode.TrajectoryDriveController;
import org.usfirst.frc.team294.robot.subsystems.CanGrab;
import org.usfirst.frc.team294.robot.subsystems.Drivetrain;
import org.usfirst.frc.team294.robot.subsystems.IntakeRollerArms;
import org.usfirst.frc.team294.robot.subsystems.RangeFinder;
import org.usfirst.frc.team294.robot.subsystems.Telescope;
import org.usfirst.frc.team294.robot.subsystems.ToteGrabber;

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
	public static TrajectoryDriveController trajectoryDriveController;//virtual drivetrain controller that follows paths in auto
	//public static ToteGrab toteGrab;
	public static CanGrab canGrab;
	public static Telescope telescope;
	public static IntakeRollerArms intakeRollerArms;
	public static RangeFinder rangeFinder;
	//public static compress compressor;
	public static ToteGrabber toteGrab;

	public static OI oi;

	public static int autoMode = 0;

	public static float autoDelay = 0;

	public static int startPosition = 0;
	
	public static Navigator navigator;
	
	public static MultiLooper autoUpdater100Hz = new MultiLooper(1.0 / 100.0);
	
    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	pdp = new PowerDistributionPanel();
    	
    	canGrab = new CanGrab();
    	drivetrain = new Drivetrain();
    	telescope = new Telescope();
    	intakeRollerArms = new IntakeRollerArms();
    	rangeFinder = new RangeFinder();
    	toteGrab = new ToteGrabber();

    	trajectoryDriveController = new TrajectoryDriveController();
    	navigator = new Navigator(drivetrain);
    	
    	autoUpdater100Hz.addLoopable(navigator);
    	autoUpdater100Hz.addLoopable(trajectoryDriveController);
    	
    	SmartDashboard.putData(drivetrain);
		SmartDashboard.putData(toteGrab);
		SmartDashboard.putData(canGrab);
		SmartDashboard.putData(intakeRollerArms);
		SmartDashboard.putData(rangeFinder);
		SmartDashboard.putData(telescope);
		
		SmartDashboard.putNumber("LeftGrabPos", Robot.toteGrab.getLeftPos());
		SmartDashboard.putNumber("RightGrabPos", Robot.toteGrab.getRightPos());
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
        SmartDashboard.putNumber("YAW", Robot.drivetrain.getYaw());

        SmartDashboard.putNumber("right encoder get", drivetrain.getRightEncoderDistance());
        SmartDashboard.putNumber("left encoder get", drivetrain.getLeftEncoderDistance());
        
        SmartDashboard.putBoolean("leftToteBump:", Robot.toteGrab.getLeftMotor().isRevLimitSwitchClosed());
        SmartDashboard.putBoolean("rightToteBump:", Robot.toteGrab.getRightMotor().isFwdLimitSwitchClosed());
        
        SmartDashboard.putNumber("Left Tote Position:", Robot.toteGrab.getLeftPos());
        SmartDashboard.putNumber("Right Tote Position:", Robot.toteGrab.getRightPos());
        
        SmartDashboard.putNumber("Telescope Position",  Robot.telescope.getPotVal());
        
        if(Robot.toteGrab.getRightMotor().isFwdLimitSwitchClosed()){
    		Robot.toteGrab.resetRightEnc();
		}
        if(Robot.toteGrab.getLeftMotor().isFwdLimitSwitchClosed()){
    		Robot.toteGrab.resetLeftEnc();
		}

    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
       
    }
}
