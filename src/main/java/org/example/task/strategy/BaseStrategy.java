package org.example.task.strategy;

import org.example.task.Hero;
import org.example.task.Point;

public class BaseStrategy implements MovementStrategy {
    private final String movementMessage;

    public BaseStrategy(String movementMessage) {
        this.movementMessage = movementMessage;
    }

    @Override
    public void move(Hero hero, Point point) {
        System.out.println(hero.getName() + " "
                + movementMessage
                + " from " + hero.getPoint() + " to " + point);
    }

    @Override
    public String toString() {
        return this.movementMessage;
    }
}
