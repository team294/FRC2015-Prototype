package org.usfirst.frc.team294.robot.commands;

import org.usfirst.frc.team294.robot.commands.ToteMotorControl.ToteMotorAction;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeArmOpenCloseWideToggle extends IntakeArmControl {

    public IntakeArmOpenCloseWideToggle() {
    	super(IntakeArmAction.WIDE_TOTE);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (act == IntakeArmAction.OPEN)
    		act = IntakeArmAction.WIDE_TOTE;
    	else
    		act = IntakeArmAction.OPEN;
    	super.initialize();
    }
}
