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

public class AutoMode extends Command {
	
	protected Timer autoTimer = new Timer();
	 
	public  AutoMode() {
		requires(Robot.drivetrain);
	}
	
	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
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
	
}

