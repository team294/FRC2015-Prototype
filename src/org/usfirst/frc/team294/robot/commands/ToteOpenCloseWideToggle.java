package org.usfirst.frc.team294.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToteOpenCloseWideToggle extends ToteMotorControl {

    public ToteOpenCloseWideToggle() {
    	super(ToteMotorAction.WIDE_TOTE);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (action == ToteMotorAction.OPEN)
    		action = ToteMotorAction.WIDE_TOTE;
    	else
    		action = ToteMotorAction.OPEN;
    	super.initialize();
    }
}
