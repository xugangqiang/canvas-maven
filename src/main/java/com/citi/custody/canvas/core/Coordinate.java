package com.citi.custody.canvas.core;

import com.citi.custody.canvas.exception.IllegalCoordinateException;

public class Coordinate {

    private final int x;

    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
        validate(x);
        validate(y);
    }

    public void validate(int value) {
        if (value < 0) {
            throw new IllegalCoordinateException("negative value is not allowed");
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
