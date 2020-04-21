/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class ServoSubsystem extends SubsystemBase {

  private Servo m_servo = new Servo(Constants.servoChannel);

  /**
   * Creates a new ExampleSubsystem.
   */
  public ServoSubsystem() {
  }

  public void driveToTarget(double targetPos)
  {
  }

  public void spin(double speed) {
    SmartDashboard.putNumber("Servo spin", speed);
    m_servo.setAngle(speed);
    
    
  }

  public void goToAngle(double angle){

  }

  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  
}
