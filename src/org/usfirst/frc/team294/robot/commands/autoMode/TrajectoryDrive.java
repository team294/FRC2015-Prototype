package org.usfirst.frc.team294.robot.commands.autoMode;

import org.usfirst.frc.team294.robot.Robot;

import com.team254.lib.trajectory.Trajectory;
import com.team254.lib.util.ChezyMath;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TrajectoryDrive extends Command {

	public TrajectoryDrive() {
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
	 * PID + Feedforward controller for following a Trajectory.
	 *
	 * @author Jared341
	 */
	public class TrajectoryFollower {

		private double kp_;
		private double kd_;
		private double kv_;
		private double ka_;
		private double last_error_;
		private double current_heading = 0;
		private int current_segment;
		private Trajectory profile_;
		private String name;

		public TrajectoryFollower() {

		}

		public void configure(double kp, double ki, double kd, double kv, double ka) {
			kp_ = kp;
			kd_ = kd;
			kv_ = kv;
			ka_ = ka;
		}

		public void reset() {
			last_error_ = 0.0;
			current_segment = 0;
		}

		public void setTrajectory(Trajectory profile) {
			profile_ = profile;
		}

		public TrajectoryFollower(String name) {
			this.name = name;
		}

		public double calculate(double distance_so_far) {

			if (current_segment < profile_.getNumSegments()) {
				Trajectory.Segment segment = profile_.getSegment(current_segment);
				double error = segment.pos - distance_so_far;
				double output = kp_ * error + kd_ * ((error - last_error_)
						/ segment.dt - segment.vel) + (kv_ * segment.vel
								+ ka_ * segment.acc);

				last_error_ = error;
				current_heading = segment.heading;
				current_segment++;
				SmartDashboard.putNumber(name + "FollowerSensor", distance_so_far);
				SmartDashboard.putNumber(name + "FollowerGoal", segment.pos);
				SmartDashboard.putNumber(name + "FollowerError", error);
				return output;
			} else {
				return 0;
			}
		}

		public double getHeading() {
			return current_heading;
		}

		public boolean isFinishedTrajectory() {
			return current_segment >= profile_.getNumSegments();
		}

		public int getCurrentSegment() {
			return current_segment;
		}

		public int getNumSegments() {
			return profile_.getNumSegments();
		}
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
}