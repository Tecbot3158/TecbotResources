package org.usfirst.frc.team3158.resources.driveTrain;

import org.usfirst.frc.team3158.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

public class SwerveTecbot extends DriveTrain{
	
	SpeedController steerLeftMotor, steerRightMotor;
	Encoder steerLeftMotorEncoder, steerRightMotorEncoder;
	
	
	SwerveTecbot(){
		super();
		switch(RobotMap.swerve_typeOfMotor){
			case CAN: 
				steerLeftMotor = new CANTalon(RobotMap.swerve_steerLeftMotor);
				steerRightMotor = new CANTalon(RobotMap.swerve_steerRightMotor);
				break;
			case TALON: 
				steerLeftMotor = new Talon(RobotMap.swerve_steerLeftMotor);
				steerRightMotor = new Talon(RobotMap.swerve_steerRightMotor);
				break;
			default:
				steerLeftMotor = new CANTalon(RobotMap.swerve_steerLeftMotor);
				steerRightMotor = new CANTalon(RobotMap.swerve_steerRightMotor);
				break;
		}
		if(RobotMap.swerve_encoders){
			steerLeftMotorEncoder = new Encoder(RobotMap.swerve_encoderLeft[0], RobotMap.swerve_encoderLeft[1]);
			steerRightMotorEncoder = new Encoder(RobotMap.swerve_encoderRight[0], RobotMap.swerve_encoderRight[1]);
			steerLeftMotorEncoder.setDistancePerPulse(RobotMap.swerve_encoder_ratio);
		}
	}
	
	Encoder steerLeftMotorEncoder(){
		return steerLeftMotorEncoder;
	}
	
	Encoder steerRightMotorEncoder(){
		return steerLeftMotorEncoder;
	}
	
	SpeedController steerLeftMotor(){
		return steerLeftMotor;
	}
	
	SpeedController steerRightMotor(){
		return steerRightMotor;
	}	
	
	
}
