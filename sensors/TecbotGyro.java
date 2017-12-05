package org.usfirst.frc.team3158.resources.sensors;

import org.usfirst.frc.team3158.robot.RobotMap;

public class TecbotGyro {
	private Navx navx;
	private Gyro gyro;
	private boolean isNavx;
	public TecbotGyro(){
		isNavx=RobotMap.isNavx;
		if(isNavx)
			navx=new Navx();
		else
			gyro=new Gyro();
	}
	public void run(){
		if(isNavx)
			navx.run();
		else
			gyro.run();
	}
	public double getAngle(){
		if(isNavx)
			return navx.getGyro();
		else
			return gyro.getAngle();
	}
	public double getRate(){
		if(isNavx)
			return navx.getMagnitud();
		else
			return gyro.getRate();
	}
	public double getAcceleration(){
		if(isNavx)
			return navx.getAcceleration();
		else
			return 0;
	}
	public double getYaw(){
		if(isNavx)
			return navx.getYaw();
		else
			return 0;
	}
	public void reset(){
		if(isNavx)
			navx.reset();
		else
			gyro.reset();
	}
	public void initGyro(){
		if(!isNavx)
			gyro.initGyro();
	}
	public void setSensitivity(double sensitivity){
		if(!isNavx)
			gyro.setSensitivity(sensitivity);
	}
	public void calibrate(){
		if(!isNavx)
			gyro.calibrate();
	}
	public double getPitch(){
		if(isNavx)
			return navx.getPitch();
		else
			return 0;
	}
	public boolean getCollision(){
		if(isNavx)
			return navx.getCollision();
		else
			return false;
	}
	public double getCollisionX(){
		if(isNavx)
			return navx.getCollisionX();
		else
			return 0;
	}
	public double getCollisionY(){
		if(isNavx)
			return navx.getCollisionY();
		else
			return 0;
	}
	

}
