package com.epam.jwd.core_final.domain;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public long distance( Point point){
        return (long) Math.sqrt(Math.pow(this.getX() -
                point.getX(), 2) + Math.pow(this.getY() - point.getY(), 2));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
