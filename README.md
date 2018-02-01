# BunnyBotElmer
Let's hunt wabbits

This repo aligns with the BunnyBot that was uded in the 2017 contest.  Conceptually it included a shifting gearbox (2 speed), a moving arm
attached to the top and middle of the robot and would move forward and back under PID control to precise position, and a simple pneumatic
claw that would open or close from the end of the arm to grab game pieces from the field.

Three subsystems were developed:
1 - Drivetrain
2 - ArmJoint (the shoulder that moved)
3 - Claw

The Drivetrain was designed with 2 CIM motors per gearbox, a pneumatic shifter, and an encoder.  The motor controllers on the drive were
simple PWM controllers and all drive complexity was programmed from the roboRio.

The Claw was incredibly simple being just a single mechanism that would open or close on command.

The more complex mechanism control wise was the armjoint.  Physically it consisted of a long 1x2 aluminum extrusion that was rotated on the
end (hence the 'shoulder joint') using a sprocket that was connected by chain to a gearbox and out to a Bag motor that was controlled by a
Talon SRX motor controller.  There was a CTRE mag encoder stage mounted in the gear train to measure the rotation of the arm and provide feedback
to the control system.  The are was controlled using the internal position pid loop of the Talon SRX.
