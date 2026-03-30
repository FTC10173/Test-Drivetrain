package org.firstinspires.ftc.teamcode.robot.commands;

import com.pedropathing.geometry.Pose;
import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.robot.subsystems.Gate;
import org.firstinspires.ftc.teamcode.robot.subsystems.Intake;

import java.util.function.Supplier;

public class FeedCommand extends CommandBase {
    private final Intake intake;
    private final Gate gate;
    private final Supplier<Pose> poseSupplier;

    public FeedCommand(Intake intake, Gate gate, Supplier<Pose> poseSupplier) {
        this.intake = intake;
        this.gate = gate;
        this.poseSupplier = poseSupplier;

        addRequirements(intake, gate);
    }

    @Override
    public void execute() {
        Pose pose = poseSupplier.get();

        if (pose.getX() > 24) {
            intake.farFeed();
        }
        else {
            intake.intake();
        }

        gate.open();
    }

    @Override
    public void end(boolean interrupted) {
        intake.stopIntake();
    }
}