package org.example.task.strategy;

public class PlaneStrategy extends BaseStrategy implements MovementStrategy {
    private static final String movementMessage = "moved by plane";

    public PlaneStrategy() {
        super(movementMessage);
    }
}
