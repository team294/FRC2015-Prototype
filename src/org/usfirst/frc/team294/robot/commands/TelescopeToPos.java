package org.usfirst.frc.team294.robot.commands;

import org.usfirst.frc.team294.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TelescopeToPos extends Command {
	
	public static enum TelescopePosition {
		PICKUP,
		CARRY,
		GROUND_1TOTE,
		GROUND_2TOTE,
		GROUND_3TOTE,
		STEP_1TOTE,
		STEP_2TOTE,
		STEP_3TOTE,
		SCORE_1TOTE,
		SCORE_2TOTE,
		SCORE_3TOTE
	}
	
	private int pos;
	private TelescopePosition action = null;
    public TelescopeToPos(int pos) {
    	this.pos = pos;
    	requires(Robot.telescope);
    }
    public TelescopeToPos(TelescopePosition action){
    	requires(Robot.telescope);
    	this.action = action;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (action != null) {
	    	pos = Robot.telescope.getPotVal();
	    	int bottom_pos = Robot.telescope.getReverseLimit();
	    	int top_pos = Robot.telescope.getForwardLimit();
	    	switch (action) {
	    	case PICKUP: pos = bottom_pos; break;
	    	case CARRY: pos = bottom_pos + 40; break;
	    	case GROUND_1TOTE: pos = top_pos - (670 - 417); break;
	    	case GROUND_2TOTE: pos = top_pos - (670 - 525); break;
	    	case GROUND_3TOTE: break;
	    	case STEP_1TOTE: break;
	    	case STEP_2TOTE: break;
	    	case STEP_3TOTE: break;
	    	case SCORE_1TOTE: break;
	    	case SCORE_2TOTE: break;
	    	case SCORE_3TOTE: break;
	    	}
    	}
    	if (Robot.telescope.getPotVal() < pos)
    		Robot.telescope.getMainTelescope().setProfile(0); //Values going up
    	else
    		Robot.telescope.getMainTelescope().setProfile(1); //Values going down
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.telescope.setPosition(pos);
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
