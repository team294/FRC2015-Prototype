package org.usfirst.frc.team294.robot.commands.autoMode;

/**
 * previously Controller.java
 *
 * @author tombot
 */
public abstract class AbstractController implements Loopable {
	  protected boolean enabled = false;
	  
	  public abstract void update();
	  public abstract void reset();
	  public abstract double getGoal();
	  
	  public void enable() {
	    enabled = true;
	  }
	  
	  public void disable() {
	    enabled = false;
	  }

	  public boolean enabled() {
	    return enabled;
	  }
	}
