/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc5459.Proto2019ver1.commands;

import org.usfirst.frc5459.Proto2019ver1.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveForwardBlind extends Command {
  double targetDistance;
	double currentDistance;
	boolean finished;
  double targetIntensity;
  //CALCULATE AVERAGE ENCODER
  
  public DriveForwardBlind(double Intensity) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    //distance in inches
    //1 in = 14.3 ticks
    requires(Robot.drive);
    //this.targetDistance = DistanceInInches;
    this.targetIntensity = Intensity;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.drive.resetEncoders();
    Robot.vexGyro.reset();
    System.out.println("going to forwardBlind");
    
    finished = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double yaw = Robot.vexGyro.getAngle();
    

    if (yaw > 360)
    {
      System.out.println ("Correcting reported yaw of:" + yaw ) ;
        yaw = yaw - 360 ;
    }
    else if (yaw < -360)
    {
      System.out.println ("Correcting reported yaw of:" + yaw ) ;
      yaw = yaw + 360 ;
    }
    double yawIntensity = yaw / 100;

    double averageDistance = ((Robot.drive.getLeftEncoder() + Robot.drive.getRightEncoder()) / 2);

    //assume 180 ticks for 1 rotation
    //for 3 feet, wheel diameter is 4 in
    //2.86 rotations
    //double distanceRemaining = targetDistance - (averageDistance/14.3);

    System.out.println(" Encoder Average: " + averageDistance + " Yaw deg:" + yaw);

    Robot.drive.setLeft(-1.0*(targetIntensity - yawIntensity));
    Robot.drive.setRight(-1.0*(targetIntensity + yawIntensity));
    // if (distanceRemaining < 0)
    // {
    //   finished = true;
    // }
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    
    return finished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("drive to is finished");
    Robot.drive.setLeft(0);
    Robot.drive.setRight(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
