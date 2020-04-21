/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.commands.ArmCommand;
import frc.robot.commands.ServoCommand;
import frc.robot.commands.EnableLimitsCommand;
import frc.robot.commands.HomeCommand;
import frc.robot.commands.SpinOnceCommand;
import frc.robot.commands.RangeSpeedCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ServoSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private final Joystick m_joystick = new Joystick(Constants.joystickPort);
  private final XboxController m_XBoxController = new XboxController(2);

  // The robot's subsystems and commands are defined here...
  private final ArmSubsystem m_armSubsystem = new ArmSubsystem();
  private final ServoSubsystem m_servoSubsystem = new ServoSubsystem();


  private final ArmCommand m_armCommand = new ArmCommand(m_armSubsystem, m_joystick);
  private final ServoCommand m_servoCommand = new ServoCommand(m_servoSubsystem, m_XBoxController);
  private final SpinOnceCommand m_spinOnceCommand = new SpinOnceCommand(m_armSubsystem);
  private final HomeCommand m_homeCommand = new HomeCommand(m_armSubsystem);
  private final EnableLimitsCommand m_limitsCommand = new EnableLimitsCommand(m_armSubsystem);
  private final RangeSpeedCommand m_rangeSpeedCommand = new RangeSpeedCommand(m_armSubsystem);
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    configureDefaultCommands();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    new JoystickButton(m_XBoxController, Constants.XBoxAButton).whenPressed(m_homeCommand);
    new JoystickButton(m_XBoxController, Constants.XBoxYButton).whenPressed(m_spinOnceCommand);
    new JoystickButton(m_XBoxController, Constants.XBoxBButton).whenPressed(m_limitsCommand);
    new JoystickButton(m_XBoxController, Constants.XBoxXButton).whenPressed(m_rangeSpeedCommand);

  //   new JoystickButton(m_XBoxController, Constants.XBoxRBumper).whenReleased(m_servoCommand);

   
  }

  private void configureDefaultCommands() {
    m_armSubsystem.setDefaultCommand(m_armCommand);
    m_servoSubsystem.setDefaultCommand(m_servoCommand);
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_armCommand;
  }
}
