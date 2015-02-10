package org.usfirst.frc.team294.robot.commands.autoMode;

import java.util.Hashtable;

import org.usfirst.frc.team294.robot.Robot;

import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.io.TextFileDeserializer;
import com.team254.lib.trajectory.io.TextFileReader;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class AutoModeStart extends Command {

	protected Timer autoTimer = new Timer();

	public  AutoModeStart() {
		requires(Robot.drivetrain);
	}

	@Override
	protected void end() {
		Robot.drivetrain.stop();
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initialize() {
		this.loadPaths();
		new DrivePathAction(this.getPath("StraightAheadPath"), 10000000);
		
	}

	@Override
	protected void interrupted() {
		
	}

	@Override
	protected boolean isFinished() {
		return false;
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

	private Hashtable<String, Path> paths_ = new Hashtable<String, Path>();

	public void loadPaths() {
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

	public Path getPath(String name) {
		return (Path)paths_.get(name);
	}

	public Path getPath(int index) {
		return (Path)paths_.get(kPathNames[index]);
	}

}

