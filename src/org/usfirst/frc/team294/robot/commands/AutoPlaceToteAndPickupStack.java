package org.usfirst.frc.team294.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPlaceToteAndPickupStack extends CommandGroup 
{
    
    public  AutoPlaceToteAndPickupStack()
    {
    	addSequential(new TonguesToPos(100,100)); // Tongues out, replace values
    	addSequential(new TelescopeToPos(700));	// To pick up tote position
    	addSequential(new TonguesToPos(200,200)); //Tongues in, replace values
    	addSequential(new TelescopeToPos(650)); // Lifts totes just off ground
    }
    
}
