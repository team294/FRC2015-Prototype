package org.usfirst.frc.team294.robot.commands;

import org.usfirst.frc.team294.robot.Robot;
import org.usfirst.frc.team294.robot.subsystems.Pivot;
import org.usfirst.frc.team294.robot.subsystems.Telescope;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TelescopeToHeight extends Command {

	private final Telescope.Setpoint m_setpoint;
	
	private int height;
    public TelescopeToHeight(Telescope.Setpoint setpoint) {
    	requires(Robot.telescope);
       m_setpoint = setpoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.telescope.setPrefSetpoint(m_setpoint);
		System.out.println(m_setpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.telescope.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
