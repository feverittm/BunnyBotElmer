package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArcadeDrive extends Command {

    public ArcadeDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double[] volts = this.getVoltages();
    	
    	volts = Robot.driveTrain.DecellCheck(volts[0], volts[1]);
    	
    	Robot.driveTrain.SetVoltages(volts[0], volts[1]);
    }
    
    public double[] getVoltages() {
    	double[] volts = new double[2];
    	double left = 0;
    	double right = 0;
    	
    	double valueForward = Robot.oi.GamePad.getRawAxis(1);
    	double valueSide = Robot.oi.GamePad.getRawAxis(4);
    	
    	left = valueForward;
    	right = valueForward;
    	if (valueSide > 0) {
    		right -= valueSide;
    		left += valueSide;
    	} else if (valueSide < 0) {
    		right += valueSide;
    		left -= valueSide;
    	}
    	
    	volts[0] = left;
    	volts[1] = -right;
    	return volts;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.StopVoltage();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}