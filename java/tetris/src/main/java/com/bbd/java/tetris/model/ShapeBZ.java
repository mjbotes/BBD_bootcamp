package com.bbd.java.tetris.model;

public class ShapeBZ implements Shapes {

    public ShapeBZ(){
        points.add(new Point(0, 5));    
        points.add(new Point(0, 4));
        points.add(new Point(1, 4));
        points.add(new Point(1, 3));
        color[0] = 4;
    }

}