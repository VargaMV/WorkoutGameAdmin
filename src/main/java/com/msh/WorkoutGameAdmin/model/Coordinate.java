package com.msh.WorkoutGameAdmin.model;

import lombok.Getter;

@Getter
public class Coordinate {
    private int x;
    private int y;

    public Coordinate() {

    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
