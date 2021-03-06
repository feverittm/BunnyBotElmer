
package org.usfirst.frc.team997.robot;

import org.usfirst.frc.team997.robot.subsystems.Claw;
import org.usfirst.frc.team997.robot.subsystems.DriveTrain;
import org.usfirst.frc.team997.robot.commands.AutoBucket;
import org.usfirst.frc.team997.robot.commands.DoNothing;
import org.usfirst.frc.team997.robot.commands.DriveTo;
import org.usfirst.frc.team997.robot.commands.DriveToDistance;
import org.usfirst.frc.team997.robot.commands.Timercommand;
import org.usfirst.frc.team997.robot.subsystems.ArmJoint;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	public static DriveTrain driveTrain;
	public static Claw claw;
	public static OI oi;
    public static ArmJoint armJoint;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		//try {
			driveTrain = new DriveTrain();
		/*} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		//try {
			claw = new Claw();
		/*} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		//try {
		armJoint = new ArmJoint();
		/*} catch (Exception e) {
			e.printStackTrace();
		 }*/
		
		oi = new OI();
		SmartDashboard.putData("Auto mode", chooser);
		chooser.addObject("AutoBucket", new AutoBucket());	//drive forward, does arm+claw stuff
		//chooser.addObject("Drive forward", new DriveTo(36));	//doesn't work
		chooser.addObject("Timertest", new Timercommand(2));	//drive forward _ seconds
		chooser.addDefault("Do nothing", new DoNothing());
		//chooser.addObject("Test driveToDistance", new DriveToDistance(2872));	//doesn't work
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
		armJoint.autozero();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
	
	public void updateSmartDashboard() {
		//oi.updateDashboard();
		driveTrain.updateSmartDashboard();
		armJoint.updateSmartDashboard();
	}
	
	public static double clamp(double Max, double Min, double Val) {
    	if (Val < Min) {
    		return Min;
    	} else if (Val > Max) {
    		return Max;
    	} else {
    		return Val;
    	}
    }
	
	public static double JoystickDeadband(double x) {
		if(Math.abs(x) < 0.05) {
			return 0;
		}
		
		return x;
	}
}

