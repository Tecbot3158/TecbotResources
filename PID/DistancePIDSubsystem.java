package org.usfirst.frc.team3158.resources.PID;

import org.usfirst.frc.team3158.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class DistancePIDSubsystem extends PIDSubsystem {


public static double ratio=0.0;

	public DistancePIDSubsystem() {
		super(0, 0, 0);
		// TODO Auto-generated constructor stub
	}

	public DistancePIDSubsystem(String name, double p, double i, double d) {
		super(name, p, i, d);
		// TODO Auto-generated constructor stub
	}

	public DistancePIDSubsystem(double p, double i, double d, double period) {
		super(p, i, d, period);
		// TODO Auto-generated constructor stub
	}

	public DistancePIDSubsystem(String name, double p, double i, double d, double f) {
		super(name, p, i, d, f);
		// TODO Auto-generated constructor stub
	}

	public DistancePIDSubsystem(double p, double i, double d, double period, double f) {
		super(p, i, d, period, f);
		// TODO Auto-generated constructor stub
	}

	public DistancePIDSubsystem(String name, double p, double i, double d, double f, double period) {
		super(name, p, i, d, f, period);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		
		return Robot.driveTrain.getLeftEncoder().getDistance(); //Ignoren el comentario 
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		ratio=output;
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
