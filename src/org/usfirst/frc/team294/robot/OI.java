package org.usfirst.frc.team294.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team294.robot.commands.*;
import org.usfirst.frc.team294.robot.commands.IntakeArmControl.IntakeArmAction;
import org.usfirst.frc.team294.robot.commands.ToteMotorControl.ToteMotorAction;
import org.usfirst.frc.team294.robot.subsystems.Telescope;
import org.usfirst.frc.team294.robot.triggers.JoystickPOV;

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
	public Button[] co_pov = new Button[4];

	public OI() {
		// Create buttons
		for (int i=1; i<13; ++i) {
			left[i] = new JoystickButton(leftStick, i);
			right[i] = new JoystickButton(rightStick, i);
			co[i] = new JoystickButton(coStick, i);
			test[i] = new JoystickButton(testStick, i);
		}
		co_pov[0] = new JoystickPOV(coStick, 0);
		co_pov[1] = new JoystickPOV(coStick, 90);
		co_pov[2] = new JoystickPOV(coStick, 180);
		co_pov[3] = new JoystickPOV(coStick, 270);

		left[4].whenPressed(new ToteMotorControl(ToteMotorAction.OPEN));
		left[4].whenReleased(new ToteMotorControl(ToteMotorAction.STOP));
		left[5].whenPressed(new ToteMotorControl(ToteMotorAction.CLOSE));
		left[5].whenReleased(new ToteMotorControl(ToteMotorAction.STOP));
		
		right[1].whenPressed(new ToteMotorControl(ToteMotorAction.PAN_RIGHT));
		left[1].whenPressed(new ToteMotorControl(ToteMotorAction.PAN_LEFT));
		
		right[3].whenPressed(new RepositionDrivetrain(false)); //False for right reposition
		left[3].whenPressed(new RepositionDrivetrain(true)); //True for left reposition
		
		//co[1].whenPressed(new TelescopeToPos(Telescope.TelescopePosition.PICKUP));
		//co[2].whenPressed(new TelescopeToPos(Telescope.TelescopePosition.CARRY));
		//co[3].whenPressed(new TelescopeToPos(Telescope.TelescopePosition.GROUND_1TOTE));
		//co[8].whileHeld(new TeleWithJoystick(coStick, 1));
		
		//co[11].whenPressed(new ToteMotorControl(ToteMotorAction.OPEN));
		//co[10].whenPressed(new ToteMotorControl(ToteMotorAction.WIDE_TOTE));
		//co[9].whenPressed(new PickUpTote());
		//co[6].whenPressed(new ToteMotorControl(ToteMotorAction.OPEN_SLIGHT));
		//co[7].whenPressed(new ToteMotorControl(ToteMotorAction.NARROW_TOTE));
		
		left[11].whenPressed(new IntakeArmControl(IntakeArmAction.OPEN));
		left[10].whenPressed(new IntakeArmControl(IntakeArmAction.WIDE_TOTE));
		right[10].whenPressed(new RunIntakeTillBump());

		co[1].whenPressed(new PickUpTote());
		co[2].whenPressed(new RunIntakeTillBump());
		co[3].whenPressed(new ToteOpenCloseWideToggle());
		co[4].whenPressed(new IntakeArmOpenCloseWideToggle());
		co[5].whenPressed(new TelescopeDecrement());
		co[6].whenPressed(new TelescopeIncrement());
		
		
		//co[4].whileHeld(new IntakeArmControl(IntakeArmControl.IntakeArmAction.MOTOR_IN));
		//co[5].whileHeld(new IntakeArmControl(IntakeArmControl.IntakeArmAction.STOP));

		//test[2].whileHeld(new IntakeArmControl(IntakeArmAction.OPEN));
		//test[3].whenPressed(new IntakeArmControl(IntakeArmAction.STOP));
		//test[4].whenPressed(new IntakeArmControl(IntakeArmAction.CLOSE));
		
		
		//testing INTAKE ARM ACTION
		//test[1].whenPressed(new IntakeArmControl(IntakeArmAction.OPEN));
		//test[4].whenPressed(new IntakeArmControl(IntakeArmAction.CLOSE));
		//test[3].whenPressed(new IntakeArmControl(IntakeArmAction.OPENMID));
		
		
		//test[2].whenPressed(new IntakeArmControl(IntakeArmAction.MOTOROUT));
		//test[6].whenPressed(new IntakeArmControl(IntakeArmAction.MOTORIN));
		//test[7].whenPressed(new IntakeArmControl(IntakeArmAction.STOP));
		
		
		
		//test[1].whenReleased(new ToteMotorControl(ToteMotorAction.STOP));
		//test[7].whenPressed(new AutoRotateXDegreesRel(45));
		
		test[8].whenPressed(new ToteMotorControl(ToteMotorAction.CLOSE));
		test[8].whenReleased(new ToteMotorControl(ToteMotorAction.STOP));
		test[7].whenPressed(new ToteMotorControl(ToteMotorAction.OPEN));
		test[7].whenReleased(new ToteMotorControl(ToteMotorAction.STOP));

		test[2].whenPressed(new ToteMotorControl(ToteMotorAction.LEFTCONTROL));
		test[2].whenReleased(new ToteMotorControl(ToteMotorAction.STOP));
		test[3].whenPressed(new ToteMotorControl(ToteMotorAction.LEFTCONTROL2));
		test[3].whenReleased(new ToteMotorControl(ToteMotorAction.STOP));
		
		test[1].whenPressed(new ToteMotorControl(ToteMotorAction.RIGHTCONTROL));
		test[1].whenReleased(new ToteMotorControl(ToteMotorAction.STOP));
		test[4].whenPressed(new ToteMotorControl(ToteMotorAction.RIGHTCONTROL2));
		test[4].whenReleased(new ToteMotorControl(ToteMotorAction.STOP));
		
		
		
		//test[5].whenPressed(new ToteMotorControl(ToteMotorAction.NARROW_TOTE));
		
		//test[6].whenPressed(new ToteMotorControl(ToteMotorAction.AUTO_OPEN));
		//test[5].whileHeld(new ToteMotorControl(ToteMotorAction.LEFTCONTROL));
		//test[5].whileHeld(new ToteMotorControl(ToteMotorAction.RIGHTCONTROL));
		test[5].whileHeld(new TeleWithJoystick(testStick, 1));
		
		//test[8].whenPressed(new DrivetrainStressTest());
	}
}
