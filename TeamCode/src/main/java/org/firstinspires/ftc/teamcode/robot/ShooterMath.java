package org.firstinspires.ftc.teamcode.robot;

import com.pedropathing.geometry.Pose;
import com.seattlesolvers.solverslib.geometry.Translation2d;

/**
 * Utility class for math related to the shooter, turret, and scoring
 */
public final class ShooterMath {
    /**
     *
     * @param pose The robot's current pose
     * @return The power (from 0 to 1) that the shooter needs to spin up to
     */
    public static double getShooterPower(Pose pose) {
        Translation2d translationalPose = new Translation2d(
                pose.getX(),
                pose.getY()
        );

        Translation2d goal = MyConstants.GoalPoses.get(Alliance.isBlue());

        double distance = translationalPose.getDistance(goal);

        double powerOffset = pose.getX() > 24 ? -0.042 : 0;

        if (distance > 0) {
            return MyConstants.Shooter.INTERCEPT * Math.pow(MyConstants.Shooter.BASE, distance) + powerOffset;
        }

        return 0.45;
    }

    /**
     *
     * @param pose The robot's current pose
     * @return The error (in radians) the robot is from the goal
     */
    public static double getGoalError(Pose pose) {
        Translation2d goalPose = MyConstants.GoalPoses.getTurretGoal(Alliance.isBlue());

        double xDiff = goalPose.getX() - pose.getX();
        double yDiff = goalPose.getY() - pose.getY();

        double targetHeading = Math.atan2(yDiff, xDiff);

        double error = targetHeading - pose.getHeading();

        return Math.atan2(Math.sin(error), Math.cos(error));
    }

    /**
     * The servos' ranges need to be configured in Constants.java
     *
     * @param pose The robot's current pose
     * @return The angle (in degrees) the servos need to turn to
     */
    public static double getTurretTarget(Pose pose) {
        double errorRadians = getGoalError(pose);

        double turretDegrees = Math.toDegrees(errorRadians);

        // Clamp turret to physical turn radius -90 to +90
        return Math.max(-MyConstants.Turret.RANGE, Math.min(MyConstants.Turret.RANGE, turretDegrees));
    }
}
