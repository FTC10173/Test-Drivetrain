package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.robot.MyConstants;

public class Gate extends SubsystemBase {
    private final double openAngle;
    private final double closedAngle;
    private final ServoEx leftGate;
    private final ServoEx rightGate;

    public Gate(HardwareMap hardwareMap) {
        openAngle = MyConstants.Gate.OPEN_ANGLE;
        closedAngle = MyConstants.Gate.CLOSED_ANGLE;

        // configure gate servo
        leftGate = new ServoEx(
                hardwareMap, "leftGate",
                MyConstants.Gate.MIN_ANGLE,
                MyConstants.Gate.MAX_ANGLE
        );
        leftGate.setInverted(true);

        rightGate = new ServoEx(
                hardwareMap, "rightGate",
                MyConstants.Gate.MIN_ANGLE,
                MyConstants.Gate.MAX_ANGLE
        );
        rightGate.setInverted(false);
    }

    private void set(double angle) {
        leftGate.set(angle);
        rightGate.set(angle);
    }

    public boolean isHealthy() {
        return leftGate != null && rightGate != null;
    }

    public void updateTelemetry(Telemetry telemetry) {
        telemetry.addData(getName() + " Healthy", isHealthy());
    }

    public void open() {
        set(openAngle);
    }

    public void close() {
        set(closedAngle);
    }
}
