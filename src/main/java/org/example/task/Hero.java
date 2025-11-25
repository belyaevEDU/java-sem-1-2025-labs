package org.example.task;

import org.example.task.strategy.MovementStrategy;

public class Hero {
    private String name;
    private MovementStrategy movementStrategy;
    private Point currentPoint;

    public Hero(String name, MovementStrategy movementStrategy) {
        this.name = name;
        this.movementStrategy = movementStrategy;
        this.currentPoint = new Point(0, 0);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point getPoint() {
        return this.currentPoint;
    }

    public void setMovementStrategy(MovementStrategy newMovementStrategy) {
        this.movementStrategy = newMovementStrategy;
    }

    public void move(Point newPoint) {
        this.movementStrategy.move(this, newPoint);
        this.currentPoint = newPoint;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof Hero) {
            Hero hero = (Hero) obj;
            return this.name.equals(hero.getName());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "name: " + name
                + ", currentPoint: " + currentPoint
                + ", movement strat: \"" + movementStrategy + "\"";
    }
}
