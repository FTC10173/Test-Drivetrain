package org.firstinspires.ftc.teamcode.robot.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes.*;

import org.firstinspires.ftc.teamcode.Roadrunner.Localizer;
import org.firstinspires.ftc.teamcode.robot.Constants;
import org.firstinspires.ftc.teamcode.robot.subsystems.Limelight;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class DefaultLimelight extends CommandBase {
    private final Limelight limelight;
    private final Localizer poseEstimator;
    private final Consumer<Double> addOffset;
    private final Constants.Alliance alliance;

    public DefaultLimelight(Limelight limelight, Localizer poseEstimator, Constants.Alliance alliance, Consumer<Double> addOffset) {
        this.limelight = limelight;
        this.poseEstimator =  poseEstimator;
        this.addOffset = addOffset;
        this.alliance = alliance;

        addRequirements(limelight);
    }

    @Override
    public void execute() {
         LLResult result = limelight.getResults();

         if (result != null && result.isValid()) {
             poseEstimator.addLimelight(result);

             List<FiducialResult> fiducials = result.getFiducialResults();
             for (FiducialResult fiducial : fiducials) {
                 int id = fiducial.getFiducialId();
                 if (id == Constants.Tags.getID(alliance)) {
                     addOffset.accept(fiducial.getTargetXDegrees());
                     break;
                 }
             }
         }
    }
}