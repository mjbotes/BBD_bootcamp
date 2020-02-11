package com.bbd.java.tetris.model;

public class Point{
    int x;
    int y;

    public Point(int a, int b){
        x = b;
        y = a;
    }

    public void moveD(){
        ++y;
    }

    public void moveR(){
       ++x;
    }

    public void moveL(){
        --x;
    }

    public void setX(int a){
        x = a;
    }

    public void setY(int a){
        y = a;
    }

    public void set(int a, int b){
        x = a;
        y = b;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}