/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ArmSubsystem;

public class SpinOnceCommand extends CommandBase {
  private final ArmSubsystem m_subsystem;
  private int m_startEncoderPosition = 0;
  /**
   * Creates a new SpinOnceCommand.
   */
  public SpinOnceCommand(ArmSubsystem subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    int startEncoderPosition = m_subsystem.getEncoder();
    m_startEncoderPosition = startEncoderPosition;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_subsystem.drive(0.5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.drive(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    int encoderDistance = m_subsystem.getEncoder() - m_startEncoderPosition;
    if(encoderDistance > Constants.ticksPerRevolution)
    {
      return true;
    }
    else {
      return false;
    }
  }
}
