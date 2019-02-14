/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc5459.Proto2019ver1.commands;

import org.usfirst.frc5459.Proto2019ver1.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class FlipCommand extends Command {
  Command SwitchToCamera1 = new SwitchToCamera1();
  Command SwitchToCamera2 = new SwitchToCamera2();
  public FlipCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.flipped == true){
      Robot.flipped = false;
      System.out.println("this is flip command!, flipped is now: "+ Robot.flipped);
 
      SwitchToCamera2.start();
    } 
    else  {
      Robot.flipped = true;
      System.out.println("this is flip command!, flipped is: now:"+ Robot.flipped);
 
      SwitchToCamera1.start();
    } 
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
