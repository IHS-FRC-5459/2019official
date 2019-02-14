/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc5459.Proto2019ver1.commands;

import org.usfirst.frc5459.Proto2019ver1.Robot;
import org.usfirst.frc5459.Proto2019ver1.sensors.PixyCam2;
import org.usfirst.frc5459.Proto2019ver1.sensors.PixyCamBlock;

import edu.wpi.first.wpilibj.command.Command;

public class AutoHatchAssist extends Command {
  int[] measuredY = new int[]{124,100,75,39,3, 0};
  int[] targetX = new int[]{184,190,198,217,237,239};
  boolean finished = false ;
  int NullTries = 0 ;

  public AutoHatchAssist() {
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
    PixyCamBlock centerBlock =  PixyCam2.GetCentermostBlock();



    if (centerBlock == null)
    {
      NullTries ++ ;
      System.out.println("Center block null time: " + NullTries) ;

      if (NullTries > 5)
      {
        finished = true ;
      }
      return ;
    }


    int desiredX = 0;

    NullTries = 0 ;
   
      for(int i =0; i < measuredY.length; i++)
      {
        if(centerBlock.yCenter >= measuredY[i])
        {
          desiredX = targetX[i];
          break ;
        }
      }
  
      int xDisplacement = desiredX - centerBlock.xCenter;

      double intensityIncrement = xDisplacement * 0.002 ;

      Robot.drive.setLeft (-1.0 * (.25 + intensityIncrement));
      Robot.drive.setRight(-1.0 * (.25 - intensityIncrement));

      System.out.println ("Desired X=" + desiredX + " Block X=" + centerBlock.xCenter +
                          " Block y=" + centerBlock.yCenter + " Intensity Increment=" + intensityIncrement);
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
    end() ;
  }
}
