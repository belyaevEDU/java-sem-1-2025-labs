package org.example.task.strategy;

import org.example.task.Hero;
import org.example.task.Point;

public interface MovementStrategy {
    void move(Hero hero, Point point);
}
