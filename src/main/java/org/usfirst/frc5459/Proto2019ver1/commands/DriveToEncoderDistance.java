/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc5459.Proto2019ver1.commands;

import org.usfirst.frc5459.Proto2019ver1.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveToEncoderDistance extends Command {

  double targetDistance;
	double currentDistance;
	boolean finished;
  double targetIntensity;
  //CALCULATE AVERAGE ENCODER
  
  public DriveToEncoderDistance(double Distance, double Intensity) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.targetDistance = Distance;
    this.targetIntensity = Intensity;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.rightEncoder.reset();
    Robot.leftEncoder.reset();
    finished = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double yaw = Robot.vexGyro.getAngle();
    double yawIntensity = yaw / 50;
    
    double averageDistance = ((Robot.leftEncoder.getDistance() + Robot.rightEncoder.getDistance()) / 2);
    
    double distanceRemaining = targetDistance - averageDistance;

    System.out.println("Distance Remaining: " + distanceRemaining + " Encoder Average: " + averageDistance);

    Robot.drive.setLeft(targetIntensity - yawIntensity);
    Robot.drive.setRight(targetIntensity + yawIntensity);
    if (distanceRemaining < 0)
    {
      finished = true;
  	}
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return finished;
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
