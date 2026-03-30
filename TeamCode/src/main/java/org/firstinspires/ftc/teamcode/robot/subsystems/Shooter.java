package org.firstinspires.ftc.teamcode.robot.subsystems;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.HardwareMap;

import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.motors.MotorGroup;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.robot.MyConstants;

public class Shooter extends SubsystemBase {
    private final MotorGroup flywheel;
    private double targetPower = 0;
    private double targetVelocity = 0;
    private boolean isRunning = false;
    MotorEx leftFlywheel;
    MotorEx rightFlywheel;

    double testPower = 0.1;

    boolean control = false;

    public Shooter(HardwareMap hardwareMap) {
        leftFlywheel = new MotorEx(hardwareMap, "leftFlywheel");
        rightFlywheel = new MotorEx(hardwareMap, "rightFlywheel");

        flywheel = new MotorGroup(leftFlywheel, rightFlywheel);

        flywheel.setInverted(false);
        flywheel.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        flywheel.setRunMode(Motor.RunMode.VelocityControl);

        flywheel.setVeloCoefficients(
                MyConstants.Shooter.kP,
                MyConstants.Shooter.kI,
                MyConstants.Shooter.kD
        );
        flywheel.setFeedforwardCoefficients(
                MyConstants.Shooter.kS,
                MyConstants.Shooter.kV,
                MyConstants.Shooter.kA
        );
    }

    @NonNull
    public Motor getMotor(HardwareMap hardwareMap, String id, Boolean inverted, Motor.GoBILDA type) {
        Motor motor = new Motor(hardwareMap, id, type);
        motor.setInverted(inverted);
        motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
        motor.setRunMode(Motor.RunMode.VelocityControl);

        motor.setVeloCoefficients(
                MyConstants.Shooter.kP,
                MyConstants.Shooter.kI,
                MyConstants.Shooter.kD
        );
        motor.setFeedforwardCoefficients(
                MyConstants.Shooter.kS,
                MyConstants.Shooter.kV,
                MyConstants.Shooter.kA
        );

        return motor;
    }

    @Override
    public void periodic() {
        if (isRunning) {
            flywheel.set(targetPower);
        } else {
            flywheel.set(0);
        }
    }

    public void setPower(double power) {
        if (control) {
            power -= 0.02;
        }

        this.targetPower = power;
        this.targetVelocity = MyConstants.Shooter.MAX_RPM * power;
    }

    public void startFlywheel() {
        isRunning = true;
    }

    public void stopFlywheel() {
        isRunning = false;
    }

    public void stop() {
        isRunning = false;
    }

    public double getTest() {
        return testPower;
    }

    public boolean isReady() {
        return getVelocity() >= (targetVelocity - MyConstants.Shooter.VELOCITY_TOLERANCE);
    }

    public boolean isReady(double speedPercent) {
        return getVelocity() >= (targetVelocity * speedPercent - MyConstants.Shooter.VELOCITY_TOLERANCE);
    }

    public double getVelocity() {
        return flywheel.getVelocity();
    }

    public double getTargetPower() {
        return targetPower;
    }

    public double getTargetVelocity() {
        return targetVelocity;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean isHealthy() {
        return flywheel != null;
    }

    public void updateTelemetry(Telemetry telemetry) {
        telemetry.addData(getName() + " Healthy", isHealthy());
        telemetry.addData(getName() + " Test", getTest());
        telemetry.addData(getName() + " Power", getTargetPower());
        telemetry.addData(getName() + " Velocity", getVelocity());
        telemetry.addData(getName() + " Target", getTargetVelocity());
        telemetry.addData(getName() + " Ready", isReady());
        telemetry.addData(getName() + " Control", control);
    }
}