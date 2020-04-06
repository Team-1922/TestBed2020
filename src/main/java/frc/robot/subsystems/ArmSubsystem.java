/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
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
    armTalon.setNeutralMode(NeutralMode.Brake);
    armTalon.config_kF(0, 1.1);
    armTalon.config_kP(0, 20);
    armTalon.selectProfileSlot(0, 0);
  }

  public void driveToTarget(double targetPos)
  {
    armTalon.set(ControlMode.MotionMagic, targetPos);
  }

  public void drive(double speed) {
    armTalon.set(ControlMode.PercentOutput, speed);
  }

  public int getError()
  {
    return armTalon.getClosedLoopError();
  }


  public int getEncoder() {
    return armTalon.getSensorCollection().getQuadraturePosition();
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
