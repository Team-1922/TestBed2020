/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.MotorCommutation;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.fasterxml.jackson.databind.ser.std.StdArraySerializers.BooleanArraySerializer;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.playingwithfusion.*;
import com.playingwithfusion.TimeOfFlight.RangingMode;

public class ArmSubsystem extends SubsystemBase {

  private WPI_TalonSRX armTalon = new WPI_TalonSRX(Constants.armTalon);
  private boolean m_limitsEnabled = false;
  private final TimeOfFlight m_rangeSensor = new TimeOfFlight(1);
  /**
   * Creates a new ExampleSubsystem.
   */
  public ArmSubsystem() {
    armTalon.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    armTalon.setSelectedSensorPosition(0, 0, 10);
    armTalon.setNeutralMode(NeutralMode.Brake);
    armTalon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed,0);
    armTalon.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed,0);
    armTalon.configForwardSoftLimitThreshold(Constants.softLimitsStart, 0);
    armTalon.configReverseSoftLimitThreshold(Constants.softLimitsEnd, 0);
    armTalon.config_kF(0, 1.1);
    armTalon.config_kP(0, 20);
    armTalon.selectProfileSlot(0, 0);
    armTalon.configForwardSoftLimitEnable(true,0);
    armTalon.configReverseSoftLimitEnable(true,0);
    m_limitsEnabled = true;
    m_rangeSensor.setRangingMode(RangingMode.Short, 40);
  }

  public void driveToTarget(double targetPos)
  {
    armTalon.set(ControlMode.MotionMagic, targetPos);
  }

  public void drive(double speed) {
    SmartDashboard.putNumber("drive speed -1 to 1", speed);
    SmartDashboard.putNumber("current position", getEncoder());
    SmartDashboard.putBoolean("limits enabled", m_limitsEnabled);
    SmartDashboard.putNumber("Range", (int)m_rangeSensor.getRange());
    SmartDashboard.putNumber("Range Sigma", (int)m_rangeSensor.getRangeSigma());
    
    armTalon.set(ControlMode.PercentOutput, speed);
  }

  public void driveToPosition(int position){
    armTalon.set(ControlMode.Position, position);
  }

  public int getError()
  {
    return armTalon.getClosedLoopError();
  }


  public int getEncoder() {
    return armTalon.getSensorCollection().getQuadraturePosition();
  }

  public void zeroEncoder() {
    armTalon.getSensorCollection().setQuadraturePosition(0, 0);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public boolean limitsEnabled(){ 
    return m_limitsEnabled; 
  }

public void enableLimitSwitch(boolean set){
  armTalon.overrideLimitSwitchesEnable(set);
}

public void enableLimits(boolean set){
  m_limitsEnabled = set;
  
  armTalon.overrideLimitSwitchesEnable(set);
  armTalon.overrideSoftLimitsEnable(set);
}

  public void setSoftLimitsEnabled(boolean val){
    armTalon.configForwardSoftLimitEnable(val,0);
    armTalon.configReverseSoftLimitEnable(val,0);
  }

  public boolean isFwdClosed() {
    if (armTalon.isFwdLimitSwitchClosed() == 1){
      return true;
    }
    return false;
  }

  public double range() {
    return m_rangeSensor.getRange();
 
  }

  public boolean isRevClosed() {
    if(armTalon.isRevLimitSwitchClosed()==1) {
      return true;
    }
    return false;
  }
}
