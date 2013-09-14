package com.pacman.model;

public class Point {

    private int x;
    private int y;


    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(int x, int y, int size) {
        this.x = x * size;
        this.y = y * size;
    }

    public Point(Rectangle rectangle) {
        this.x = rectangle.getX();
        this.y = rectangle.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public Point multiply(int size){
        return new Point(x, y,size);
    }

}
