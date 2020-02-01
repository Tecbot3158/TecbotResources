package org.usfirst.frc.team3158.resources.PID;

import org.usfirst.frc.team3158.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;


public class ShooterPIDSubsystem extends PIDSubsystem {

	private SpeedController motorShooter;
	private Encoder shooterEncoder;
	int speedLevel=0;
	double shooterVoltage=1;
	double output=0;
	boolean shooter_hasEncoders=false;
	
	public ShooterPIDSubsystem(
							 double pidKP, double pidKI, double pidKD, 
							 int motorShooterPort, int shooterEncoderPort1,
							 int shooterEncoderPort2, boolean shooter_hasEncoders
							 )
	{
		super(pidKP, pidKI, pidKD);
		if(RobotMap.shooter_hasEncoders)
			shooterEncoder = new Encoder(shooterEncoderPort1, shooterEncoderPort2);
		switch(RobotMap.shooter_typeOfConfiguration){
			case CAN:{
				motorShooter = new CANTalon(motorShooterPort);
				break;
			}
			case TALON:{
				motorShooter = new Talon(motorShooterPort);
				break;
			}
		}
		setOutputRange(-1, 1);
		disable();
		
	}
	
	public void setVoltage(double voltage){
		motorShooter.set(voltage);
	}
	
	public void setPIDSpeed(double speed){
		setSetpoint(speed);
	}
	
	public void startPIDShooter(){
		enable();
	}
	
	public void stopPIDShooter(){
		disable();
	}
	
	public void stopShooter(){
		disable();
		setVoltage(0);
	}
	public void setShooterOff(){
		setVoltage(0);
	}
	public double getEncoder(){
		if(shooter_hasEncoders)
		return shooterEncoder.getRate();
		else return 0;
	}
	public double getError(){
		return 0;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return shooterEncoder.getRate();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		this.output=output;
//		motorShooter.set(-output);
		
	}
	public double getOutput(){
		return output;
	}
	public  void addShooterVoltage(){
		
		switch(speedLevel){
		
			case 0:{
				setVoltage(shooterVoltage*0.1);
				speedLevel = 1;
				break;
			}
			case 1:{
				setVoltage(shooterVoltage*0.2);
				speedLevel = 2;
				break;
			}
			case 2:{
				setVoltage(shooterVoltage*0.3);
				speedLevel = 3;
				break;
			}
			case 3:{
				setVoltage(shooterVoltage*0.4);
				speedLevel = 4;
				break;
			}
			case 4:{
				setVoltage(shooterVoltage*0.5);
				speedLevel = 5;
				break;
			}
			case 5:{
				setVoltage(shooterVoltage*0.6);
				speedLevel = 6;
				break;
			}
			case 6:{
				setVoltage(shooterVoltage*0.7);
				speedLevel = 7;
				break;
			}
			case 7:{
				setVoltage(shooterVoltage*0.8);
				speedLevel = 8;
				break;
			}
			case 8:{
				setVoltage(shooterVoltage*0.9);
				speedLevel = 9;
				break;
			}
			case 9:{
				setVoltage(shooterVoltage*1);
				speedLevel = 10;
				break;
			}
		}		
	}
		
	public void descreaseShooterVoltage(){
		switch(speedLevel){
			case 10:{
				setVoltage(shooterVoltage*.9);
				speedLevel = 9;
				break;
			}
			case 9:{
				setVoltage(shooterVoltage*.8);
				speedLevel = 8;
				break;
			}
			case 8:{
				setVoltage(shooterVoltage*.7);
				speedLevel = 7;
				break;
			}
			case 7:{
				setVoltage(shooterVoltage*.6);
				speedLevel = 6;
				break;
			}
			case 6:{
				setVoltage(shooterVoltage*.5);
				speedLevel = 5;
				break;
			}
			case 5:{
				setVoltage(shooterVoltage*.4);
				speedLevel = 4;
				break;
			}
			case 4:{
				setVoltage(shooterVoltage*.3);
				speedLevel = 3;
				break;
			}
			case 3:{
				setVoltage(shooterVoltage*.2);
				speedLevel = 2;
				break;
			}
			case 2:{
				setVoltage(shooterVoltage*.1);
				speedLevel = 1;
				break;
			}
			case 1:{
				setVoltage(shooterVoltage*0);
				speedLevel = 0;
				break;
			}
		}
	}

}

