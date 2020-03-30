/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class ArmSubsystem extends SubsystemBase {

  private WPI_TalonSRX armTalon = new WPI_TalonSRX(Constants.armTalon);

  /**
   * Creates a new ExampleSubsystem.
   */
  public ArmSubsystem() {
    armTalon.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    armTalon.setSelectedSensorPosition(0, 0, 10);
  }

  public void drive(double speed) {
    armTalon.set(speed);
  }

  public int getEncoder() {
    return armTalon.getSensorCollection().getQuadraturePosition();
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
