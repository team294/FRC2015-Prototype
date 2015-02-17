package org.usfirst.frc.team294.robot.commands.autoMode;

import org.usfirst.frc.team294.robot.Robot;

import com.team254.lib.trajectory.Trajectory;
import com.team254.lib.trajectory.TrajectoryFollower;
import com.team254.lib.util.ChezyMath;
/**
 * TrajectoryDriveController.java
 * This controller drives the robot along a specified trajectory.
 * @author Tom Bottiglieri
 */
public class TrajectoryDriveController extends AbstractController{

	public TrajectoryDriveController() {
		init();
	}
	Trajectory trajectory;
	TrajectoryFollower followerLeft = new TrajectoryFollower("left");
	TrajectoryFollower followerRight = new TrajectoryFollower("right");
	double direction;
	double heading;
	double kTurn = -3.0/80.0;

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
		Robot.drivetrain.resetDriveEncoders();
	}

	public int getFollowerCurrentSegment() {
		return followerLeft.getCurrentSegment();
	}

	public int getNumSegments() {
		return followerLeft.getNumSegments();
	}

	public void update() {
		if (onTarget()) {
			Robot.drivetrain.tankDrive(0.0, 0.0);
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
			Robot.drivetrain.tankDrive(speedLeft + turn, speedRight - turn);
		}
	}

	public void setTrajectory(Trajectory t) {
		this.trajectory = t;
	}

	public double getGoal() {
		return 0;
	}

}

