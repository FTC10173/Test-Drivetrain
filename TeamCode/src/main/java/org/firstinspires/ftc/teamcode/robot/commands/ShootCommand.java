package org.firstinspires.ftc.teamcode.robot.commands;

import com.pedropathing.geometry.Pose;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.robot.subsystems.Shooter;

import java.util.function.Function;
import java.util.function.Supplier;

public class ShootCommand extends CommandBase {
    private final Shooter shooter;
    private final Supplier<Pose> poseSupplier;
    private final Function<Pose, Double> powerSupplier;

    public ShootCommand(
            Shooter shooter,
            Supplier<Pose> poseSupplier,
            Function<Pose, Double> powerSupplier
    ) {
        this.shooter = shooter;
        this.poseSupplier = poseSupplier;
        this.powerSupplier = powerSupplier;

        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooter.setPower(
                powerSupplier.apply(poseSupplier.get())
        );
        shooter.startFlywheel();
    }

    @Override
    public void execute() {
        // Update power if needed
        shooter.setPower(
                powerSupplier.apply(poseSupplier.get())
        );
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stopFlywheel();
    }
}