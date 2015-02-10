package org.usfirst.frc.team294.robot.commands;

import org.usfirst.frc.team294.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DrivetrainStressTest extends CommandGroup {

	public  DrivetrainStressTest() {
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
		requires(Robot.drivetrain);
		Timer inner=new Timer();
		Timer outer=new Timer();
		double speedIterator=1.0;
		while(outer.get()<3600.0){
			while(inner.get()<2.0){
				addSequential(new AutoDriveStraight(100.0, 1*speedIterator));
			}
			while(inner.get()>2.0){
				addSequential(new AutoDriveStraight(-100.0, 1*speedIterator));
			}
			speedIterator-=0.3;
			inner.stop();
			inner.reset();
			inner.start();
			if(speedIterator<0.2){
				speedIterator=1.0;
			}
		}
	}
}
