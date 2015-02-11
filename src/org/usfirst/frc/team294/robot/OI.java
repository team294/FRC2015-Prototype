package org.usfirst.frc.team294.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team294.robot.commands.*;
import org.usfirst.frc.team294.robot.commands.IntakeArmControl.IntakeArmAction;
import org.usfirst.frc.team294.robot.commands.ToteMotorControl.ToteMotorAction;
import org.usfirst.frc.team294.robot.commands.autoMode.AutoModeStart;
//import org.usfirst.frc.team294.robot.commands.SafeGrabOrReleaseTote.GrabOrRelease;
//import org.usfirst.frc.team294.robot.subsystems.Telescope.Setpoint;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {

	// Joystick controls
	public Joystick leftStick = new Joystick(0);
	public Joystick rightStick = new Joystick(1);
	public Joystick coStick = new Joystick(2);
	public Joystick testStick = new Joystick(3);
	//KinectStick kStick1 = KinectStick(1);
	//KinectStick kStick2 = KinectStick(2);

	// Joystick buttons
	public Button[] left = new Button[13];
	public Button[] right = new Button[13];
	public Button[] co = new Button[13];
	public Button[] test = new Button[13];

	public OI() {
		// Create buttons
		for (int i=1; i<13; ++i) {
			left[i] = new JoystickButton(leftStick, i);
			right[i] = new JoystickButton(rightStick, i);
			co[i] = new JoystickButton(coStick, i);
			test[i] = new JoystickButton(testStick, i);
		}

		left[4].whenPressed(new ToteMotorControl(ToteMotorAction.OPEN));
		left[4].whenReleased(new ToteMotorControl(ToteMotorAction.STOP));
		left[5].whenPressed(new ToteMotorControl(ToteMotorAction.CLOSE));
		left[5].whenReleased(new ToteMotorControl(ToteMotorAction.STOP));
		
		right[1].whenPressed(new ToteMotorControl(ToteMotorAction.PAN_RIGHT));
		left[1].whenPressed(new ToteMotorControl(ToteMotorAction.PAN_LEFT));
		
		test[2].whileHeld(new IntakeArmControl(IntakeArmAction.OPEN));
		test[3].whenPressed(new IntakeArmControl(IntakeArmAction.STOP));
		test[4].whenPressed(new IntakeArmControl(IntakeArmAction.CLOSE));
		
		test[1].whenReleased(new ToteMotorControl(ToteMotorAction.STOP));
		test[7].whenPressed(new AutoRotateXDegreesRel(45));

		
		test[6].whenPressed(new ToteMotorControl(ToteMotorAction.AUTO_OPEN));
		
		test[5].whileHeld(new TeleWithJoystick());
		
		//test[8].whenPressed(new DrivetrainStressTest());
		
		test[8].whenPressed(new AutoModeStart());
	}
}
