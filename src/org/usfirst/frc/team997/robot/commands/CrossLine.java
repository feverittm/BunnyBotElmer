package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

//This auto routine is for if we are on the left or right starting position and our switch
//is on the opposite side.
public class CrossLine extends CommandGroup {

    public CrossLine() {
    	addSequential(new PDriveDistance(12 * PDriveDistance.ticksPerFoot));
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
