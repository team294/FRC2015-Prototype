package org.usfirst.frc.team294.robot.triggers;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 *
 */
public class JoystickPOV extends Button {
    
    GenericHID m_joystick;
    int m_povValue;
    /**
     * Create a joystick button for triggering commands
     * @param joystick The GenericHID object that has the button (e.g. Joystick, KinectStick, etc)
     * @param buttonNumber The button number (see {@link GenericHID#getRawButton(int) }
     */
    public JoystickPOV(GenericHID joystick, int povValue) {
        m_joystick = joystick;
        m_povValue = povValue;
    }
    
    public boolean get() {
    	return m_joystick.getPOV() == m_povValue;
    }
}
