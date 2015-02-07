package org.usfirst.frc.team294.robot.commands.autoMode;

//import java.util.Hashtable;

import java.util.Hashtable;

import org.usfirst.frc.team294.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.io.TextFileDeserializer;
import com.team254.lib.trajectory.io.TextFileReader;

/**
 *
 */
public class AutoMode extends CommandGroup {
	protected Timer autoTimer = new Timer();
	public  AutoMode() {
		System.out.println("Starting auto mode!");
		try {
			autoTimer.reset();
			autoTimer.start();
			//routine();
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Ending auto mode!");

		// Add Commands here:
		// e.g. addSequential(new Command1());
		//      addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		//      addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.

	}
	public final static String[] kPathNames = { 
		"InsideLanePathFar",
		"CenterLanePathFar",
		"WallLanePath",
		"InsideLanePathClose", 
		"StraightAheadPath",
	};
	public final static String[] kPathDescriptions = { 
		"Inside, Far", 
		"Middle Lane",
		"Wall Lane",
		"Inside, Close",
		"Straight ahead",
	};

	static Hashtable paths_ = new Hashtable();

	public static void loadPaths() {
		Timer t = new Timer();
		t.start();
		TextFileDeserializer deserializer = new TextFileDeserializer();
		for (int i = 0; i < kPathNames.length; ++i) {

			TextFileReader reader = new TextFileReader("file://" + kPathNames[i] + 
					".txt");

			Path path = deserializer.deserialize(reader.readWholeFile());
			paths_.put(kPathNames[i], path);
		}
		System.out.println("Parsing paths took: " + t.get());
	}

	public static Path get(String name) {
		return (Path)paths_.get(name);
	}

	public static Path getByIndex(int index) {
		return (Path)paths_.get(kPathNames[index]);
	}




	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




	public class DrivePathAction extends Action {

		double heading;
		Path path;

		public DrivePathAction(Path route, double timeout) {
			path = route;
			setTimeout(timeout);
		}

		public boolean execute() {
			// We need to set the Trajectory each update as it may have been flipped from under us
			driveController.loadProfileNoReset(path.getLeftWheelTrajectory(), path.getRightWheelTrajectory());
			return isTimedOut() || driveController.onTarget();
		}

		public void init() {
			System.out.println("Init Drive " + Timer.getFPGATimestamp());
			Robot.drivetrain.resetEncoders();
			driveController.loadProfile(path.getLeftWheelTrajectory(), path.getRightWheelTrajectory(), 1.0, heading);
			drivebase.useController(driveController);
			driveController.enable();
		}

		public void done() {
			System.out.println("Done Drive " + Timer.getFPGATimestamp());
			driveController.disable();
			drivebase.setLeftRightPower(0, 0);
		}
	}



	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



	public abstract class Action{

		public boolean shouldRun = true;

		public abstract boolean execute();

		public abstract void init();

		public abstract void done();
		Timer t = new Timer();
		double timeout = 10000000;

		public void kill() {
			done();
			shouldRun = false;
		}

		public void run() {
			t.start();
			if (shouldRun) {
				init();
			}
			while (shouldRun && !execute() && !isTimedOut()) {
				try {
					Thread.sleep(30);
				} catch (InterruptedException ex) {
				}
			}
			if (shouldRun) {
				done();
			}
		}

		public void setTimeout(double timeout) {
			this.timeout = timeout;
		}

		public boolean isTimedOut() {
			return t.get() >= timeout;
		}
	}



	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
