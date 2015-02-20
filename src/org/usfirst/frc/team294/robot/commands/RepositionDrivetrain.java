package org.usfirst.frc.team294.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RepositionDrivetrain extends CommandGroup {
    
    public  RepositionDrivetrain(boolean leftSide) {
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
 
    	if(leftSide)
    	{
    		addSequential(new AutoRotateXDegreesRel(45, false));
        	addSequential(new AutoDriveWithTimer(true, .25));
        	addSequential(new AutoRotateXDegreesRel(-45));
        	addSequential(new AutoDriveWithTimer(false, .4));
    	}
    	else
    	{
    	addSequential(new AutoRotateXDegreesRel(-45, true));
    	addSequential(new AutoDriveWithTimer(true, .25));
    	addSequential(new AutoRotateXDegreesRel(45));
    	addSequential(new AutoDriveWithTimer(false, .4));
    	}
    }
}
