/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc5459.Proto2019ver1.sensors;

import edu.wpi.first.wpilibj.I2C;
/**
 * Add your docs here.
 */
public class PixyCam2 {

    static   {
        mI2C = new I2C(I2C.Port.kOnboard, 0x54) ;
   }
   static I2C mI2C;

   private static byte[] sendAndRecieve(int type, byte[]outbytes, int MaxExpectedReturnPayload){
    // Find length of desired output data packet
    // If no packet supplied, then use zero 

    int length ;

    if (outbytes == null){
        length = 0;
    }else{
        length = outbytes.length ;
    }

    //System.out.println( "sr out length =" + length) ;
    // Prepare initial 4 bytes of outgoing data packet

    byte[] myOut = new byte[length + 4];
    myOut[0] = (byte)174;
    myOut[1] = (byte)193;
    myOut[2] = (byte)type;
    myOut[3] = (byte)length;

    if(length > 0){
        for(int i = 0; i < length; i++){
            myOut[4 + i] = outbytes[i];
        }
    }
   //byte[] getver = new byte[]  {(byte) 174, (byte) 193, 14, 0};   
   //boolean bok = mI2C.writeBulk(getver, 4) ;

    boolean bok = mI2C.writeBulk(myOut, myOut.length) ;

    //System.out.println("write result" + bok + "myout.length =" + myOut.length) ;
    byte [] retval = new byte[ MaxExpectedReturnPayload + 6];

    bok = mI2C.readOnly(retval, retval.length) ;

    //System.out.println ("Read result " + bok + " retval length" + retval.length) ;

    //mI2C.close(); 

    return retval ;
    }

    public static String getVersion(){   

        // Send and received packet for operation 14, GetVersion.
        // No input data required

        byte [] retval = sendAndRecieve(14, null, 32) ;

        // If operation succeeds, then majot version is in output array 
        // element 8 and minor is in output array element 9

        if(retval != null){
            int major = retval[8];
            int minor = retval[9];
            return("FW  Major: " + major + " Minor: " + minor );
        }else{
            return("Get Version Failed");
        }
    }

    private static String bytesToHex1(byte[] hashInBytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hashInBytes.length; i++) {
            sb.append(Integer.toString((hashInBytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static byte [] SetLamps (Boolean Upper, boolean Lower) {
        byte[] lamps = new byte[2];

        if (Upper == true){
         lamps[0] = 1;
        }else{
            lamps [0] = 0 ;}

        if (Lower == true){
            lamps [1] = 1;
        }else{
            lamps[1] = 0 ;
        }

        byte[] retvect = sendAndRecieve(22, lamps, 32); 

        return retvect ;
    }
}
