package org.firstinspires.ftc.teamcode.robot.subsystems;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.HardwareMap;

import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake extends SubsystemBase {
    private final MotorEx intakeMotor;

    public Intake(HardwareMap hardwareMap) {
        // configure motor
        intakeMotor = new MotorEx(hardwareMap, "intake");
        intakeMotor.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);
        intakeMotor.setInverted(false);
    }

    public void setPower(double power) {
        intakeMotor.set(power);
    }

    public void intake() {
        setPower(1.0);
    }

    public void farFeed() {
        setPower(0.5);
    }

    public void outtake() {
        setPower(-1.0);
    }

    public void stopIntake() {
        setPower(0);
    }

    public void stop() {
        stopIntake();
    }

    public boolean isHealthy() {
        return intakeMotor != null;
    }

    public void updateTelemetry(Telemetry telemetry) {
        telemetry.addData(getName() + " Healthy", isHealthy());
    }
}