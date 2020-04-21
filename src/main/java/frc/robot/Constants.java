/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final int armTalon = 2;

    public static final double homingSpeed = 0.1;
    public static final int joystickPort = 0;

    public static final int ticksPerRevolution = 4096;
    public static final int armTravel = 2420; //the max ticks the arm travels
    public static final int softLimitsStart = -100; // ten ticks off the 
    public static final int softLimitsEnd = -armTravel + 100; // ten ticks off the 

    public static final int XBoxAButton = 1;
    public static final int XBoxBButton = 2;
    public static final int XBoxXButton = 3;
    public static final int XBoxYButton = 4;
    public static final int XBoxLBumper = 5;
    public static final int XBoxRBumper = 6;

    public static final double maxTOFShortModeRange = 2000.0; //millimeters

    public static final int servoChannel = 0;

}
