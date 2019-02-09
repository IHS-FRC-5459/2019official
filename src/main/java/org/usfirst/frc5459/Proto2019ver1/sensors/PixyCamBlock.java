/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc5459.Proto2019ver1.sensors;

/**
 * Add your docs here.
 */
public class PixyCamBlock {
    public int sigNumber, xCenter, yCenter, width, height, angle, trackingIndex, age ;

    public PixyCamBlock(int sigNumber, int xCenter, int yCenter, int width, int height, int angle, int trackingIndex, int age)
    {
        this.sigNumber = sigNumber;
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.width = width;
        this.height = height;
        this.angle = angle;
        this.trackingIndex = trackingIndex;
        this.age = age;
    }


}
