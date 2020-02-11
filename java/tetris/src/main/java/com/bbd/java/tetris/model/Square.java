package com.bbd.java.tetris.model;

public class Square implements Shapes {

    public Square(){
        points.add(new Point(0, 4));
        points.add(new Point(1, 4));
        points.add(new Point(0, 5));
        points.add(new Point(1, 5));
        color[0] = 7;
    }

}