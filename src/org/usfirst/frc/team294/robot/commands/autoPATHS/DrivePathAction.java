package org.usfirst.frc.team294.robot.commands.autoPATHS;

import org.usfirst.frc.team294.robot.Robot;
import com.team254.lib.trajectory.Path;

import edu.wpi.first.wpilibj.Timer;

public class DrivePathAction extends AbstractAction {

	double heading;
	Path path;

	public DrivePathAction(Path route, double timeout) {
		path = route;
		setTimeout(timeout);
	}

	public boolean execute() {
		// We need to set the Trajectory each update as it may have been flipped from under us
		System.out.println("\nValue sent to left drivetrain from path: "+path.getLeftWheelTrajectory()
							+"\nValue sent to right drivetrain from path: "+path.getRightWheelTrajectory()+"\n");
		Robot.trajectoryDriveController.loadProfileNoReset(path.getLeftWheelTrajectory(), path.getRightWheelTrajectory());
		return isTimedOut() || Robot.trajectoryDriveController.onTarget();
	}

	public void init() {
		System.out.println("Init Drive " + Timer.getFPGATimestamp());

		Robot.drivetrain.resetDriveEncoders();
	//	Robot.driveController.loadProfile(path.getLeftWheelTrajectory(), path.getRightWheelTrajectory(), 1.0, heading);
	//	Robot.drivetrain.useController(Robot.driveController);
	//	Robot.driveController.enable();

	//	Robot.drivetrain.resetEncoders();
		Robot.trajectoryDriveController.loadProfile(path.getLeftWheelTrajectory(), path.getRightWheelTrajectory(), 1.0, heading);
		Robot.drivetrain.useController(Robot.trajectoryDriveController);
		Robot.trajectoryDriveController.enable();
	}

	public void done() {
		System.out.println("Done Drive " + Timer.getFPGATimestamp());
		Robot.trajectoryDriveController.disable();
		Robot.drivetrain.tankDrive(0, 0);

	}
}
