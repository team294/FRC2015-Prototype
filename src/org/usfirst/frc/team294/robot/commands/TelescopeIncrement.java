package org.usfirst.frc.team294.robot.commands;

import org.usfirst.frc.team294.robot.Robot;
import org.usfirst.frc.team294.robot.subsystems.Telescope;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TelescopeIncrement extends Command {

    public TelescopeIncrement() {
    	requires(Robot.telescope);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Telescope.TelescopePosition action = Robot.telescope.getPositionTarget();
    	switch (action) {
    	case PICKUP: action = Telescope.TelescopePosition.CARRY; break;
    	case CARRY: action = Telescope.TelescopePosition.GROUND_1TOTE; break;
    	case GROUND_1TOTE: action = Telescope.TelescopePosition.GROUND_2TOTE; break;
    	case GROUND_2TOTE: action = Telescope.TelescopePosition.GROUND_3TOTE; break;
    	case GROUND_3TOTE: break;
    	case STEP_1TOTE: break;
    	case STEP_2TOTE: break;
    	case STEP_3TOTE: break;
    	case SCORE_1TOTE: break;
    	case SCORE_2TOTE: break;
    	case SCORE_3TOTE: break;
    	}
		Robot.telescope.setPositionTarget(action);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.telescope.gotoPosition();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (timeSinceInitialized() < 0.2)
    		return false;
    	return Robot.telescope.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.telescope.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.telescope.stop();
    }
}
