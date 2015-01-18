package org.usfirst.frc.team294.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team294.robot.commands.*;
import org.usfirst.frc.team294.robot.subsystems.Pivot;
import org.usfirst.frc.team294.robot.subsystems.Telescope.Setpoint;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released  and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	// Joystick controls
	public Joystick leftStick = new Joystick(0);
	public Joystick rightStick = new Joystick(1);
	public Joystick coStick = new Joystick(2);
	public Joystick testStick = new Joystick(3);
	//KinectStick kStick1 = KinectStick(1);
	//KinectStick kStick2 = KinectStick(2);

	// Joystick buttons
	Button[] left = new Button[13];
	Button[] right = new Button[13];
	Button[] co = new Button[13];
	Button[] test = new Button[13];

	public OI() {
		// Create buttons
		for (int i=1; i<13; ++i) {
			left[i] = new JoystickButton(leftStick, i);
			right[i] = new JoystickButton(rightStick, i);
			co[i] = new JoystickButton(coStick, i);
			test[i] = new JoystickButton(testStick, i);
		}

		left[3].whileActive(new PanIntakeLeft());
		right[3].whileActive(new PanIntakeRight());
		
		right[2].whenPressed(new CenterIntake());
		left[2].whenPressed(new CenterIntake());
		
		right[1].whenPressed(new GrabOrReleaseTote());

		right[4].whenPressed(new SingleStickDrive());
		right[3].whenPressed(new ArcadeDriveWithJoysticks());
		right[2].whenPressed(new TankDriveWithJoysticks());

		co[5].whenPressed(new TelescopeToHeight(Setpoint.k5Tote));
		co[4].whenPressed(new TelescopeToHeight(Setpoint.k1Tote));
		co[3].whenPressed(new TelescopeToHeight(Setpoint.k2Tote));
		co[2].whenPressed(new TelescopeToHeight(Setpoint.k3Tote));
		co[1].whenPressed(new TelescopeToHeight(Setpoint.k4Tote));
		
		co[5].whenPressed(new IntakeReverse());
		co[6].whenPressed(new IntakeStop());
	}
}
