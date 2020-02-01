package org.usfirst.frc.team3158.resources.driveTrain;

import org.usfirst.frc.team3158.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;



public class PIDGyro extends PIDSubsystem {

	public PIDGyro(double p, double i, double d) {
		super(p, i, d);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return Robot.tecbotGyro.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		Robot.driveTrain.drive(0, output);
	}
	
	public double getPosition(){
		return Robot.tecbotGyro.getAngle();
	}
	
	public void setAngle(double angle){
		setSetpoint(angle);
		Robot.tecbotGyro.reset();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
	
	
	
}

