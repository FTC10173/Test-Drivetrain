package org.firstinspires.ftc.teamcode.robot.autos.test;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.Constants;
import org.firstinspires.ftc.teamcode.robot.autos.AutoBuilder;

@Autonomous(name = "GateTest Auto", group = "2025-2026")
public final class GateTest extends LinearOpMode {

    @Override
    public void runOpMode() {

        Pose2d startPose = Constants.StartingPoses.BLUE_CLOSE;
        Pose2d BLUE_CLOSE_BACK = Constants.ShootingPoses.BLUE_CLOSE_BACK;
        Pose2d BLUE_CLOSE = Constants.ShootingPoses.BLUE_CLOSE;
        Pose2d INTAKE_GATE = Constants.GatePoses.BLUE_INTAKE;
        Pose2d PARK = Constants.ParkingPoses.BLUE_CLOSE;

        double FEED_TIME = Constants.Intake.FEED_TIME_SEC;

        AutoBuilder autoBuilder = new AutoBuilder(
                hardwareMap,
                startPose,
                Constants.Alliance.BLUE,
                AutoBuilder.Side.CLOSE
        )
                .moveAndShoot(FEED_TIME, BLUE_CLOSE)
                .alignWithArtifacts(22)
                .straightIntake(true)
                .moveAndShoot(FEED_TIME, BLUE_CLOSE_BACK)
                .moveToPose(new Pose2d(12, -24, Math.toRadians(270)))
                .intakeGate(INTAKE_GATE, FEED_TIME)
                .moveToPose(new Pose2d(12, -24, Math.toRadians(270)))
                .moveAndShoot(FEED_TIME, BLUE_CLOSE_BACK)
                .alignWithArtifacts()
                .straightIntake()
                .moveAndShoot(FEED_TIME, BLUE_CLOSE_BACK)
                .alignWithArtifacts()
                .straightIntake()
                .moveAndShoot(FEED_TIME, BLUE_CLOSE_BACK)
                .moveToPose(PARK);

        waitForStart();

        if (isStopRequested()) return;

        autoBuilder
                .run()
                .stop();
    }
}
