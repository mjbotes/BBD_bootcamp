package com.bbd.java.tetris.model;

import java.util.ArrayList;

interface Shapes{

    ArrayList<Point> points = new ArrayList<Point>();
    int[] color = new int[1];

    default public ArrayList<Point> getPoints(){
        return points;
    }

    default public void moveRight(){
        for (Point p : getPoints()) {
            p.moveR();
        }
    }

    default public int getColor(){
        return color[0];
    }

    default public void moveLeft(){
        for (Point p : getPoints()) {
            p.moveL();
        }
    }

    default public void moveDown(){
        for (Point p : getPoints()) {
            p.moveD();
        }
    }

    default public void rotate(int i){
        int[][] mat = toMatrix();
        int[] len = findLen();
        int[][] res = new int[len[0]][len[1]];

        for (int x = 0; x < len[0]; x++){
            for(int y = 0; y < len[1]; y++){
                res[x][y] = mat[y][x];
            }
        }
        for(int m = 0; m < len[0]; m++){
            int[] tmp = res[m];
            res[m] = res[len[0] - m - 1];
            res[len[0] - m - 1] = tmp;
        }
        points.clear();
        int[] min = findMin();
        for (int x = 0; x < len[0]; x++){
            for(int y = 0; y < len[1]; y++){
                if (res[x][y] == 1){
                    points.add(new Point(x + min[0], y + min[1]));
                }
            }
        }
    }

    default public int[] findMin(){
        int mX = 0;
        int mY = 0;

        for (Point p : getPoints()) {
            if (p.getX() < mX)
                mX = p.getX();
            if (p.getY() < mY)
                mY = p.getY();
        }
        int[] ret = {mX, mY};
        return ret;
    }

    default public int[] findMax(){
        int mX = 0;
        int mY = 0;

        for (Point p : getPoints()) {
            if (p.getX() > mX)
                mX = p.getX();
            if (p.getY() > mY)
                mY = p.getY();
        }
        int[] ret = {mX, mY};
        return ret;
    }

    default public int[] findLen(){
        int[] min = findMin();
        int[] max = findMax();
        int[] ret = {max[0] - min[0]  + 1, max[1] - min[1] + 1};
        return ret;
    }

    default public int[][] toMatrix(){
        int[] min = findMin();
        int[] len = findLen();
        int[][] mat = new int[len[1]][len[0]];
        for (Point p : getPoints()) {
            mat[p.getY() - min[1]][p.getX() -min[0]] = 1;
        }
        return mat;
    }

    default public void destroy(){
        points.clear();
        color[0] = 0;
    }
}