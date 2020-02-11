package com.bbd.java.tetris.model;

public class ShapeZ implements Shapes {

    public ShapeZ(){
        points.add(new Point(0, 3));
        points.add(new Point(0, 4));
        points.add(new Point(1, 4));
        points.add(new Point(1, 5));
        color[0] = 5;
    }

}