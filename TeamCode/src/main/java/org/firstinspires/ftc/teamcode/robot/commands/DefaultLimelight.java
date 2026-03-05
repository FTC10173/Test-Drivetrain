package org.firstinspires.ftc.teamcode.robot.commands;

import com.acmerobotics.dashboard.Mutex;
import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes.*;

import org.firstinspires.ftc.teamcode.Roadrunner.Localizer;
import org.firstinspires.ftc.teamcode.robot.Constants;
import org.firstinspires.ftc.teamcode.robot.subsystems.Limelight;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.DoubleSupplier;
import java.util.function.Function;

public class DefaultLimelight extends CommandBase {
    private final Limelight limelight;
    private final Localizer poseEstimator;
    private final Function<Constants.Alliance, Double> headingSupplier;
    private final DoubleSupplier turretSupplier;
    private final Constants.Alliance alliance;

    public DefaultLimelight(Limelight limelight, Localizer poseEstimator, Constants.Alliance alliance, Function<Constants.Alliance, Double> headingSupplier, DoubleSupplier turretSupplier) {
        this.limelight = limelight;
        this.poseEstimator =  poseEstimator;
        this.headingSupplier = headingSupplier;
        this.turretSupplier = turretSupplier;
        this.alliance = alliance;

        addRequirements(limelight);
    }

    @Override
    public void execute() {
         LLResult result = limelight.getResults();

        limelight.updateHeading(headingSupplier.apply(alliance), turretSupplier.getAsDouble());


        if (result != null && result.isValid()) {
             poseEstimator.addLimelight(result);
         }
    }
}