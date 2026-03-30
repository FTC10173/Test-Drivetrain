package org.firstinspires.ftc.teamcode.robot.commands;

import com.pedropathing.geometry.Pose;
import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.robot.subsystems.Turret;

import java.util.function.DoubleSupplier;
import java.util.function.Function;
import java.util.function.Supplier;

public class DefaultTurret extends CommandBase {
    private final Turret turret;
    private final Supplier<Pose> poseSupplier;
    private final DoubleSupplier headingSupplier;
    private final Function<Pose, Double> targetSupplier;

    public DefaultTurret(
            Turret turret,
            Function<Pose, Double> targetSupplier,
            Supplier<Pose> poseSupplier,
            DoubleSupplier headingSupplier
    ) {
        this.turret = turret;
        this.poseSupplier = poseSupplier;
        this.headingSupplier = headingSupplier;
        this.targetSupplier = targetSupplier;

        addRequirements(turret);
    }

    @Override
    public void execute() {
        Pose pose = poseSupplier.get();
        double correctedHeading = headingSupplier.getAsDouble();

        double targetHeading = targetSupplier.apply(
                new Pose(
                        pose.getX(), pose.getY(), correctedHeading
                )
        );

        turret.set(targetHeading);
    }
}