package org.usfirst.frc.team294.robot.commands;

import org.usfirst.frc.team294.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TonguesToPos extends Command {
	
	private int posLeft;
	private int posRight;
	private int threshold=5;	
	
    public TonguesToPos(int posLeft, int posRight) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.posLeft = posLeft;
    	this.posRight = posRight;
    	requires(Robot.toteGrab);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    
    	Robot.toteGrab.setLeftPosition(posLeft);
    	Robot.toteGrab.setRightPosition(posRight);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {

    	return (Math.abs(Robot.toteGrab.getLeftMotor().getPosition() - posLeft) < threshold) && (Math.abs(Robot.toteGrab.getRightMotor().getPosition() - posRight) < threshold);
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("TOUNGE TO POS INTERRUPTED");
    	Robot.toteGrab.stop();
    }
}
