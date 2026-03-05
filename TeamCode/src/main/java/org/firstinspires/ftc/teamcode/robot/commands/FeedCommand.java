package org.firstinspires.ftc.teamcode.robot.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.robot.subsystems.Gate;
import org.firstinspires.ftc.teamcode.robot.subsystems.Intake;

public class FeedCommand extends CommandBase {
    private final Intake intake;
    private final Gate gate;

    public FeedCommand(Intake intake, Gate gate) {
        this.intake = intake;
        this.gate = gate;

        addRequirements(intake, gate);
    }

    @Override
    public void execute() {
        intake.intake();
        gate.open();
    }

    @Override
    public void end(boolean interrupted) {
        intake.stopIntake();
    }
}