package org.firstinspires.ftc.teamcode.robot.autos;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public static class BlueCloseFull {
    public PathChain shootPreload;
    public PathChain intake1;
    public PathChain shootIntake1;
    public PathChain intake2;
    public PathChain shootIntake2;
    public PathChain intake3;
    public PathChain shootIntake3;
    public PathChain park;

    public BlueCloseFull(Follower follower) {
        shootPreload = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(25.000, 127.000),
                                new Pose(32.000, 120.000),
                                new Pose(48.000, 96.000),
                                new Pose(60.000, 84.000)
                        )
                )
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        intake1 = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(60.000, 84.000),
                                new Pose(36.000, 84.000)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(135), Math.toRadians(180))
                .addPath(
                        new BezierLine(
                                new Pose(36.000, 84.000),
                                new Pose(14.000, 84.000)
                        )
                )
                .setTangentHeadingInterpolation()
                .build();

        shootIntake1 = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(14.000, 84.000),
                                new Pose(36.000, 84.000),
                                new Pose(48.000, 96.000)
                        )
                )
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        intake2 = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(48.000, 96.000),
                                new Pose(48.000, 60.000),
                                new Pose(36.000, 60.000)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(-135), Math.toRadians(180))
                .addPath(
                        new BezierLine(
                                new Pose(36.000, 60.000),
                                new Pose(14.000, 60.000)
                        )
                )
                .setTangentHeadingInterpolation()
                .build();

        shootIntake2 = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(14.000, 60.000),
                                new Pose(36.000, 60.000),
                                new Pose(60.000, 84.000)
                        )
                )
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        intake3 = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(60.000, 84.000),
                                new Pose(48.000, 36.000),
                                new Pose(36.000, 36.000)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(-135), Math.toRadians(180))
                .addPath(
                        new BezierLine(
                                new Pose(36.000, 36.000),
                                new Pose(14.000, 36.000)
                        )
                )
                .setTangentHeadingInterpolation()
                .build();

        shootIntake3 = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(14.000, 36.000),
                                new Pose(36.000, 36.000),
                                new Pose(48.000, 72.000),
                                new Pose(60.000, 84.000)
                        )
                )
                .setTangentHeadingInterpolation()
                .setReversed()
                .build();

        park = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(60.000, 84.000),
                                new Pose(24.000, 84.000)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(-135), Math.toRadians(180))
                .build();
    }
}