/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.io.Writer;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ArmSubsystem;

public class HomeCommand extends CommandBase {
  private final ArmSubsystem m_subsystem;
  private boolean m_backingOff = false;
 
  /**
   * Creates a new HomeCommand.
   */
  public HomeCommand(ArmSubsystem subsystem) {
    m_subsystem = subsystem;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_subsystem.setSoftLimitsEnabled(false);
    m_backingOff = false;
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if (m_backingOff == false) {
      m_subsystem.drive(Constants.homingSpeed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.drive(0);
    
  }

 

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    int currentPosition  = m_subsystem.getEncoder();

    if (m_backingOff == false) {
      if (m_subsystem.isFwdClosed() && m_subsystem.isRevClosed()) {
        return false;
      }
      else {
        m_backingOff = true;
        m_subsystem.zeroEncoder();
        m_subsystem.enableLimitSwitch(false);
        m_subsystem.driveToTarget(Constants.softLimitsStart);
        
      }
    }
    else {
      // trying to back off of the hard limit
      
      if (m_subsystem.getEncoder() <= Constants.softLimitsStart){
        m_subsystem.enableLimitSwitch(true);
        m_subsystem.setSoftLimitsEnabled(true);         
        m_backingOff = false;
        return true;
      }
      
    }

    return false;
 
    
   }
}
