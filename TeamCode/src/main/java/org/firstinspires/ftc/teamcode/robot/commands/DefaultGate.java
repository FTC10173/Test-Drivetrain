package org.firstinspires.ftc.teamcode.robot.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.robot.subsystems.Gate;
import org.firstinspires.ftc.teamcode.robot.subsystems.Intake;

public class DefaultGate extends CommandBase {
    private final Gate gate;

    public DefaultGate(Gate gate) {
        this.gate = gate;

        addRequirements(gate);
    }

    @Override
    public void execute() {
        gate.close();
    }
}