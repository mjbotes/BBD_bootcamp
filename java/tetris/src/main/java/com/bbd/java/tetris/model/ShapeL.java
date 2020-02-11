package com.bbd.java.tetris.model;

public class ShapeL implements Shapes {

    public ShapeL(){
        points.add(new Point(0, 5));
        points.add(new Point(1, 3));
        points.add(new Point(1, 4));
        points.add(new Point(1, 5));
        color[0] = 3;
    }

}