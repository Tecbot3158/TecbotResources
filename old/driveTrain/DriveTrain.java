package org.usfirst.frc.team3158.resources.driveTrain;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team3158.robot.Robot;
import org.usfirst.frc.team3158.robot.RobotMap;

import com.ctre.CANTalon;

public class DriveTrain extends Subsystem {
	
	SpeedController frontRightWheel, frontLeftWheel,  rearRightWheel,  rearLeftWheel, middleLeftWheel, middleRightWheel;	
	RobotDrive drive;
	DoubleSolenoid transmission;
	boolean encoders, reverse, driversLatch=false;
	boolean transmissionState = false;
	Encoder leftEncoder, rightEncoder;
	Command defaultCommand;
	
	//The constructor is to set all the motor types and ports, create the RobotDrive,
	//Set the solenoid ports if transmission exists and the encoders if exists
	//Requires the RobotMapTecbot
	
	public DriveTrain(){
		if(!RobotMap.class.getSuperclass().getName().equals("RobotMapTecbot"))throw new NullPointerException("RobotMap is not extending from RobotMapTecbot");
		switch(RobotMap.chassis_typeOfConfiguration){
		case MOTORS_2:
			switch(RobotMap.chassis_typeOfMotor){
			case CAN:
				rearRightWheel = new CANTalon (RobotMap.chassis_rearRightMotor);
				rearLeftWheel = new CANTalon (RobotMap.chassis_rearLeftMotor);
				break;
			case TALON:
				rearRightWheel = new Talon (RobotMap.chassis_rearRightMotor);
				rearLeftWheel = new Talon (RobotMap.chassis_rearLeftMotor);
				break;
			}
			drive = new RobotDrive(rearLeftWheel, rearRightWheel);
			break;
		case MOTORS_4:
			switch(RobotMap.chassis_typeOfMotor){
			case CAN:
				frontRightWheel = new CANTalon (RobotMap.chassis_frontRightMotor);
				frontLeftWheel = new CANTalon (RobotMap.chassis_frontLeftMotor);
				rearRightWheel = new CANTalon (RobotMap.chassis_rearRightMotor);
				rearLeftWheel = new CANTalon (RobotMap.chassis_rearLeftMotor);
				break;
			case TALON:
				frontRightWheel = new Talon (RobotMap.chassis_frontRightMotor);
				frontLeftWheel = new Talon (RobotMap.chassis_frontLeftMotor);
				rearRightWheel = new Talon (RobotMap.chassis_rearRightMotor);
				rearLeftWheel = new Talon (RobotMap.chassis_rearLeftMotor);
				break;
			}
			drive = new RobotDrive(frontLeftWheel, rearLeftWheel,
					   frontRightWheel, rearRightWheel);
			break;
		case MOTORS_6:
			switch(RobotMap.chassis_typeOfMotor){
			case CAN:
				frontRightWheel = new CANTalon (RobotMap.chassis_frontRightMotor);
				frontLeftWheel = new CANTalon (RobotMap.chassis_frontLeftMotor);
				rearRightWheel = new CANTalon (RobotMap.chassis_rearRightMotor);
				rearLeftWheel = new CANTalon (RobotMap.chassis_rearLeftMotor);
				middleLeftWheel = new CANTalon (RobotMap.chassis_middleLeftMotor);
				middleRightWheel = new CANTalon (RobotMap.chassis_middleRightMotor);
				break;
			case TALON:
				frontRightWheel = new Talon (RobotMap.chassis_frontRightMotor);
				frontLeftWheel = new Talon (RobotMap.chassis_frontLeftMotor);
				rearRightWheel = new Talon (RobotMap.chassis_rearRightMotor);
				rearLeftWheel = new Talon (RobotMap.chassis_rearLeftMotor);
				middleLeftWheel = new CANTalon (RobotMap.chassis_middleLeftMotor);
				middleRightWheel = new CANTalon (RobotMap.chassis_middleRightMotor);
				break;
			}
			drive = new RobotDrive(frontLeftWheel, rearLeftWheel,
					   			frontRightWheel, rearRightWheel,
					   			middleLeftWheel, middleRightWheel);
			break;	
		}
		
		if(RobotMap.chassis_transmission){
			transmission = new DoubleSolenoid(RobotMap.chassis_transmissionForward, RobotMap.chassis_transmissionReverse);
		}
		
		if(RobotMap.chassis_encoders){
			leftEncoder = new Encoder(RobotMap.chassis_encoder1[0],RobotMap.chassis_encoder1[1]);
			rightEncoder = new Encoder(RobotMap.chassis_encoder2[0],RobotMap.chassis_encoder2[1]);
			leftEncoder.setDistancePerPulse(RobotMap.chassis_encoder_ratio);
			rightEncoder.setDistancePerPulse(RobotMap.chassis_encoder_ratio);
		}
	}
	
	//Returns encoders
	
	public Encoder getLeftEncoder(){
		return leftEncoder;
	}
	
	public Encoder getRightEncoder(){
		return rightEncoder;
	}
	
	//Invert the front orientation from the chassis
	
	public void changeOrientation(){
		reverse=!reverse;
	}
	
	//Change the axis orientation of the robot 
	//@Param reverse is to change a desired state true or false
	
	public void setOrientation(boolean reverse){
		this.reverse = reverse;
	}
	
	//Returns if the robot orientation is inverted
	
	public boolean getOrientation(){
		return reverse;
	}
	
	//Set a speed and rotation angle for the robot
	
	public void drive(double axis, double rotationAngle){
		if(reverse)		drive.arcadeDrive(-axis,rotationAngle);
		else 			drive.arcadeDrive(axis,rotationAngle);	
	}
	
	//Uses the joystick to move the robot
	
	public void drive(){
		if(driversLatch)	drive(Robot.oi.getJoy1().getY(), Robot.oi.getJoy1().getX());
		else 				drive(Robot.oi.getJoy().getY(), Robot.oi.getJoy().getX());
	}
	
	//Switch drivers controls
	
	public void invertDrivers(){
		driversLatch=!driversLatch;
	}
	
	//Set the main driver;
	
	public void setInvertDrivers(boolean invert){
		driversLatch=invert;
	}
	
	//Returns if the controls are switched
	
	public boolean driversInvertStatus(){
		return driversLatch;
	}
	
	//Stop DriveTrain
	
	public void stop(){
		drive.arcadeDrive(0, 0);
	}
	
	//Turn transmission on
	
	public void transmissionOn(){
		transmission.set(DoubleSolenoid.Value.kForward);
		transmissionState=true;
	}
	
	//Turn transmission off
	
	public void transmissionOff(){
		transmission.set(DoubleSolenoid.Value.kReverse);
		transmissionState=false;
	}
	
	//Set transmission to a desired state
	//@Param state is for enabling the transmission on a desired state
	
	public void setTransmission(boolean state){
		if(state)transmissionOn();
		else transmissionOff();		
	}
	
	//Switch transmission state
	
	public void transmissionLatch(){
		if(transmissionState)
			transmissionOff();
		else 
			transmissionOn();
	}
	
	//Returns the motors
	
	public SpeedController getFrontRightWheel(){
		return frontRightWheel;
	}
	
	public SpeedController getFrontLeftWheel(){
		return frontLeftWheel;
	}
	
	public SpeedController getRearRightWheel(){
		return rearRightWheel;
	}
	
	public SpeedController getRearLeftWheel(){
		return rearLeftWheel;
	}
	
	public SpeedController getMiddleRightWheel(){
		return middleRightWheel;
	}
	
	public SpeedController getMiddleLeftWheel(){
		return middleLeftWheel;
	}
	
	protected void setDefaultCommand(Command defaultCommand){
		this.defaultCommand = defaultCommand;
	}
	
	//Set the default command for the driveTrain if this subsystem is not on use
	
	protected void initDefaultCommand() {
		if(defaultCommand!=null)
		setDefaultCommand(defaultCommand);
	}
}
