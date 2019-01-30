/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc5459.Proto2019ver1.commands;
import org.usfirst.frc5459.Proto2019ver1.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.*;

public class TurnToGyroCommand extends Command {
  int direction ;
	double YawTarget = 0.0 ;
  boolean Finished = false ;

    
  
  public TurnToGyroCommand(int direction) {
    System.out.println("Auto Turn ctor");
    this.direction = direction ;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.vexGyro.reset();
    YawTarget = 90 * direction;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    double intensity;

    double Zangle = Robot.vexGyro.getAngle() ;

    //calculate yaw angle remaining
    double AbsYawAngleRemaining = Math.abs(YawTarget - Robot.vexGyro.getAngle());
    System.out.println("Z:" + Zangle + " Target: " + YawTarget + " Remaining"+AbsYawAngleRemaining);
    //if less than a degree, rotation is done
    if(AbsYawAngleRemaining < 12){
      intensity = 0;
      Finished = true;
      return;
    }
    //if less than 20 degrees, use quater power
    else if(AbsYawAngleRemaining < 500){
      intensity = 0.25;
    }
    //otherwisse use half power
    else{
      intensity = 0.5;
    }
    
    Robot.drive.setLeft(-1 * (intensity * direction));
    Robot.drive.setRight(intensity * direction);
    SmartDashboard.putNumber("Gyro Angle", Robot.vexGyro.getAngle());
 
//    Finished = true ;
}

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Finished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drive.setLeft(0);
    Robot.drive.setRight(0);
    
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
