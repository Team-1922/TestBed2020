/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.ServoSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.*;
import edu.wpi.first.wpilibj2.command.CommandBase;


/**
 * An example command that uses an example subsystem.
 */
public class ServoCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ServoSubsystem m_subsystem;
  private final XboxController m_controller;

  /**
   * Creates a new ServoCommand.
   *
   * @param subsystem The subsystem used by this command.
   * @param button The button to control this command.
   */
public ServoCommand(ServoSubsystem subsystem, XboxController controller) {
    m_subsystem = subsystem;
    m_controller = controller;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putString("Servo Command started ", "true");
    m_subsystem.spin(45);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
   //  m_subsystem.drive(m_joystick.getY());
   double speed = m_controller.getY(Hand.kRight);
   //SmartDashboard.putNumber("Servo speed", speed);
    // m_subsystem.spin(speed);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  //  m_subsystem.spin(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    SmartDashboard.putString("Servo Command is Finished ", "false");
    return false;
  }
}
