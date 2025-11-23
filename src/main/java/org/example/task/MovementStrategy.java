package org.example.task;

public record MovementStrategy(
        String movementMessage
) {
    @Override
    public String toString() {
        return movementMessage;
    }
}
