package org.usfirst.frc.team3158.resources.driveTrain;

import org.usfirst.frc.team3158.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DefaultDrive extends Command{
	boolean btnLatch=false;
	public DefaultDrive(){
		requires(Robot.driveTrain);
	}

    protected void initialize() {
    	btnLatch=false;
    }

	protected void execute() {
    	Robot.driveTrain.drive();
    	if(Robot.driveTrain.driversInvertStatus()){
    		if(Robot.oi.getJoy1().getPOV()==Robot.oi.right||Robot.oi.getJoy().getPOV()==Robot.oi.right){
    	    	if(!btnLatch){
    	    		Robot.driveTrain.invertDrivers();
    	    		btnLatch=true;
    	    	}
        	}else btnLatch=false;
    	}
    	else if(Robot.oi.getJoy().getPOV()==Robot.oi.right){
	    	if(!btnLatch){
	    		Robot.driveTrain.invertDrivers();
	    		btnLatch=true;
	    	}
    	}else btnLatch=false;
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }

}
