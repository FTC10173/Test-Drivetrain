package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.robot.MyConstants;

public class Turret extends SubsystemBase {
    private final ServoEx turret0;
    private final ServoEx turret1;
    private double targetAngle = MyConstants.Turret.RANGE_MAX_ANGLE / 2;
    private boolean locked = false;
    private double turretDegrees = 0;

    public static final double CENTER = MyConstants.Turret.RANGE_MAX_ANGLE / 2.0;

    public Turret(HardwareMap hardwareMap) {
        turret0 = new ServoEx(
                hardwareMap, "leftTurret",
                MyConstants.Turret.RANGE_MIN_ANGLE,
                MyConstants.Turret.RANGE_MAX_ANGLE
        );
        turret1 = new ServoEx(
                hardwareMap, "rightTurret",
                MyConstants.Turret.RANGE_MIN_ANGLE,
                MyConstants.Turret.RANGE_MAX_ANGLE
        );

        turret0.setInverted(false);
        turret1.setInverted(false);
    }

    @Override
    public void periodic() {
        turret0.set(targetAngle);
        turret1.set(targetAngle);
    }

    public void set(double turretTarget) {
        if (!locked) {
            turretDegrees = turretTarget;

            double servoDegrees = turretTarget * MyConstants.Turret.GEAR_RATIO;

            double servoTarget = (MyConstants.Turret.RANGE_MAX_ANGLE / 2.0) + servoDegrees;

            double center = MyConstants.Turret.RANGE_MAX_ANGLE / 2.0;

            double maxServoOffset = MyConstants.Turret.RANGE * MyConstants.Turret.GEAR_RATIO;

            double min = center - maxServoOffset;
            double max = center + maxServoOffset;

            servoTarget = Math.max(min, Math.min(max, servoTarget));

            this.targetAngle = servoTarget;
        } else {
            turretDegrees = 0;
        }
    }

    public void autoSet(double turretTarget) {
        turretDegrees = turretTarget;

        double servoDegrees = turretTarget * MyConstants.Turret.GEAR_RATIO;

        double servoTarget = (MyConstants.Turret.RANGE_MAX_ANGLE / 2.0) + servoDegrees;

        double maxServoOffset = MyConstants.Turret.RANGE * MyConstants.Turret.GEAR_RATIO;

        double min = CENTER - maxServoOffset;
        double max = CENTER + maxServoOffset;

        servoTarget = Math.max(min, Math.min(max, servoTarget));

        turret0.set(servoTarget);
        turret1.set(servoTarget);
    }

    public boolean isHealthy() {
        return turret0 != null && turret1 != null;
    }

    public void updateTelemetry(Telemetry telemetry) {
        telemetry.addData(getName() + " Healthy", isHealthy());
        telemetry.addData(getName() + " targetAngle", targetAngle);
        telemetry.addData(getName() + " turretDegrees", turretDegrees);
    }

    public void stop() {

    }

    public double getTargetAngle() {
        return targetAngle;
    }

    public double getTurretDegrees() {
        return turretDegrees;
    }

    public void lockTurret() {
        if (!locked) {
            locked = true;
            targetAngle = MyConstants.Turret.RANGE_MAX_ANGLE / 2;
        } else {
            locked = false;
        }
    }
}
