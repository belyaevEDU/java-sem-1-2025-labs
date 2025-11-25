package org.example.task.strategy;

public class HorseStrategy extends BaseStrategy implements MovementStrategy {
    private static final String movementMessage = "moved by horse";

    public HorseStrategy() {
        super(movementMessage);
    }
}
