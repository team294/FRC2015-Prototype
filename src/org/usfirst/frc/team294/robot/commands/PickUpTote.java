package org.usfirst.frc.team294.robot.commands;

import org.usfirst.frc.team294.robot.Robot;
import org.usfirst.frc.team294.robot.commands.IntakeArmControl.IntakeArmAction;
import org.usfirst.frc.team294.robot.commands.ToteMotorControl.ToteMotorAction;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team294.robot.commands.TelescopeToPos;
import org.usfirst.frc.team294.robot.subsystems.Telescope;

/**
 *
 */
public class PickUpTote extends CommandGroup {
    
    public  PickUpTote() {
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

    	addSequential(new TelescopeLowerSlow());
    	//addParallel(new IntakeArmControl(IntakeArmAction.MOTOR_IN));
    	//addSequential(new IntakeArmControl(IntakeArmAction.CLOSE));
    	//addSequential(new IntakeArmControl(IntakeArmAction.STOP));
    	addSequential(new ToteMotorControl(ToteMotorAction.OPEN));
    	addSequential(new TelescopeToPos(Telescope.TelescopePosition.PICKUP));
    	//addSequential(new IntakeArmControl(IntakeArmAction.OPEN));
    	addSequential(new ToteMotorControl(ToteMotorAction.WIDE_TOTE));
    	addSequential(new TelescopeToPos(Telescope.TelescopePosition.CARRY));
    	
    }
}
