package org.example.task.strategy;

public class WalkingStrategy extends BaseStrategy implements MovementStrategy {
    private static final String movementMessage = "moved by walking";

    public WalkingStrategy() {
        super(movementMessage);
    }
}
