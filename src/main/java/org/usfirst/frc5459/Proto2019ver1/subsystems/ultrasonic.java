/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc5459.Proto2019ver1.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Ultrasonic;

/**
 * Add your docs here.
 */
public class ultrasonic extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  AnalogInput exampleAnalog ;

  public ultrasonic () 
  {
    //AnalogInput ai;
    //ai = new AnalogInput(0);
    int bits;
    
    //example
    exampleAnalog = new AnalogInput(1);
    // int bits;
     exampleAnalog.setOversampleBits(4);
    bits = exampleAnalog.getOversampleBits();
    exampleAnalog.setAverageBits(2);
    bits = exampleAnalog.getAverageBits();
    
  //https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599718-analog-inputs
    //(digital input 1, digital output 1), may need to be changed
    AnalogInput.setGlobalSampleRate(62500);
    
  }

  //Ultrasonic ultra = new Ultrasonic(1,1);
  
  
  public double getVoltage() {
   double volts;
  // exampleAnalog = new AnalogInput(1);
   volts = exampleAnalog.getVoltage();
   System.out.println("volts is: "+ volts);
   return volts;
  }
  public double getAvgVoltage() {
    double volts;
    //exampleAnalog = new AnalogInput(0);
    volts = exampleAnalog.getAverageVoltage();
    System.out.println("avg volts is: "+ volts);
    return volts;
   }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    //ultra.setAutomaticMode(true);

  }
}
