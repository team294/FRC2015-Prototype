package org.usfirst.frc.team294.robot.commands.autoMode;

import org.usfirst.frc.team294.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

import com.team254.lib.Controller;
import com.team254.lib.trajectory.Trajectory;
import com.team254.lib.trajectory.TrajectoryFollower;
import com.team254.lib.util.ChezyMath;
/**
 *
 */
public class FollowTrajectory extends Command {

	public FollowTrajectory() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}




	/**
	 * TrajectoryDriveController.java
	 * This controller drives the robot along a specified trajectory.
	 * @author Tom Bottiglieri
	 */
	public class TrajectoryDriveController{

		public TrajectoryDriveController() {
			init();
		}
		Trajectory trajectory;
		TrajectoryFollower followerLeft = new TrajectoryFollower("left");
		TrajectoryFollower followerRight = new TrajectoryFollower("right");
		double direction;
		double heading;
		double kTurn = -3.0/80.0;
		private boolean enabled;

		public boolean onTarget() {
			return followerLeft.isFinishedTrajectory(); 
		}

		private void init() {
			followerLeft.configure(1.5, 0, 0, 1.0/15.0, 1.0/34.0);
			followerRight.configure(1.5, 0, 0, 1.0/15.0, 1.0/34.0);
		}

		public void loadProfile(Trajectory leftProfile, Trajectory rightProfile, double direction, double heading) {
			reset();
			followerLeft.setTrajectory(leftProfile);
			followerRight.setTrajectory(rightProfile);
			this.direction = direction;
			this.heading = heading;
		}

		public void loadProfileNoReset(Trajectory leftProfile, Trajectory rightProfile) {
			followerLeft.setTrajectory(leftProfile);
			followerRight.setTrajectory(rightProfile);
		}

		public void reset() {
			followerLeft.reset();
			followerRight.reset();
			Robot.drivetrain.resetEncoders();
		}

		public int getFollowerCurrentSegment() {
			return followerLeft.getCurrentSegment();
		}

		public int getNumSegments() {
			return followerLeft.getNumSegments();
		}

		public void update() {
			if (!enabled) {
				return;
			}

			if (onTarget()) {
				Robot.drivetrain.tankDrive(0.0,0.0);
				//Robot.drivetrain.setLeftRightPower(0.0, 0.0);
			} else  {
				double distanceL = direction * Robot.drivetrain.getLeftEncoderDistance();
				double distanceR = direction * Robot.drivetrain.getRightEncoderDistance();

				double speedLeft = direction * followerLeft.calculate(distanceL);
				double speedRight = direction * followerRight.calculate(distanceR);

				double goalHeading = followerLeft.getHeading();
				double observedHeading = Robot.drivetrain.getGyroAngleInRadians();

				double angleDiffRads = ChezyMath.getDifferenceInAngleRadians(observedHeading, goalHeading);
				double angleDiff = Math.toDegrees(angleDiffRads);

				double turn = kTurn * angleDiff;
				drivebase.setLeftRightPower(speedLeft + turn, speedRight - turn);
			}
		}

		public void setTrajectory(Trajectory t) {
			this.trajectory = t;
		}

		public double getGoal() {
			return 0;
		}
	}

}
