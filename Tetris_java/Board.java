package test;

import java.util.Random;

public class Board{
    Shapes cShape;
    int inProgress;
    int sizeX;
    int sizeY;
    int[][] board;

    public Board(int sy, int sx, int i){
        sizeX = sx;
        sizeY = sy;
        board = new int[sy][sx];
        for (int y = 0; y < sizeY; y++){
            for(int x = 0; x < sizeX; x++){
                board[y][x] = 0;
            }
        }
        chooseShape(i);
        inProgress = 1;
    }

    public int getInprogress(){
        return inProgress;
    }

    public void chooseShape(int i){
        switch (i){
            case 0:
                cShape = new ShapeT();
                break;
            case 1:
                cShape = new ShapeBL();
                break;
            case 2:
                cShape = new ShapeL();
                break;
            case 3:
                cShape = new ShapeBZ();
                break;
            case 4:
                cShape = new ShapeZ();
                break;
            case 5:
                cShape = new Rectangle();
                break;
            case 6:
            default:
                cShape = new Square();
                break;
        }
        if (canPlacePiece() != 1){
            inProgress=0;
        }
    }

    public void moveSR(){
        if(canMoveR() == 1)
        cShape.moveRight();
    }

    public void moveSL(){
        if (canMoveL() == 1)
            cShape.moveLeft();
    }

    public void moveSD(int i){
        if (canMoveD() == 1){
            cShape.moveDown();
        } else {
            lockPiece();
            if (inProgress == 1){
            chooseShape(i);
            }
            checkLines();
        }
    }

    public void rotS(){
        if (canRotate() == 1)
            cShape.rotate(1);
    }

    public int canMoveD(){
        for (Point p: cShape.getPoints()){
            int x = p.getX();
            int y = p.getY();
            if (y + 1 >= sizeY || board[y + 1][x] != 0 || inProgress == 0){
                return 0;
            }
        }
        return 1;
    }

    public int canMoveR(){
        for (Point p: cShape.getPoints()){
            int x = p.getX();
            int y = p.getY();
            if (x + 1 < sizeX || board[x + 1][y] != 0){
                return 0;
            }
        }
        return 1;
    }

    public int canMoveL(){
        for (Point p: cShape.getPoints()){
            int x = p.getX();
            int y = p.getY();
            if (x - 1 > 0 || board[x - 1][y] != 0){
                return 0;
            }
        }
        return 1;
    }
    public int canRotate(){
        int[][] mat = cShape.toMatrix();
        int[] len = cShape.findLen();
        int[][] res = new int[len[0]][len[1]];

        for (int x = 0; x < len[0]; x++){
            for(int y = 0; y < len[1]; y++){
                res[x][y] = mat[y][x];
            }
        }
        for(int m = 0; m <= len[0]; m++){
            int[] tmp = res[m];
            res[m] = res[len[0] - m - 1];
            res[len[0] - m - 1] = tmp;
        }
        for (int x = 0; x < len[0]; x++){
            for(int y = 0; y < len[1]; y++){
                if (res[x][y] == 1){
                    if (board[x][y] == 1 || y >= sizeY || x >= sizeX || x <= 0)
                        return 0;
                }
            }
        }
        return 1;
    }

    public void lockPiece(){
        int[][] mat = cShape.toMatrix();
        int[] min = cShape.findMin();
        int[] len = cShape.findLen();

        for (int y = 0; y < len[1]; y++){
            for(int x = 0; x < len[0]; x++){
                if (mat[y][x] == 1){
                    board[y + min[1]][x + min[0]] = cShape.getColor();
                }
            }
        }
        cShape.destroy();
        cShape = null;
    }

    public void checkLines() {
        int a = 0;
        for(int y = 0; y < sizeY; y++){
            a = 1;
            for(int x = 0; x < sizeX; x++){
                if(board[y][x] == 0)
                    a = 0;                    
            }
            if (a == 1){
                for (int c = y + 1; c < 24; c++){
                    board[c -1] = board[c];
                }
                for (int p = 0; p < 10; p++){
                    board[24][p] = 0;
                }
            }
        }
    }

    public String numToColor(int i){
        switch (i){
            case 0:
                return "white";
            case 1:
                return "blue";
            case 2:
                return "green";
            case 3:
                return "red";
            case 4:
                return "lime";
            case 5:
                return "yellow";
            case 6:
                return "orange";
            default:
            case 7:
                return "purple";
        }
    }

    public String[][] getBoard(){
        String[][] ret = new String[sizeY][sizeX];
        for (int y = 0; y < sizeY; y++){
            for(int x = 0; x < sizeX; x++){
                ret[y][x] = numToColor(board[y][x]);
            }
        }
        if (cShape != null){
            for(Point a: cShape.getPoints()){
                ret[a.getY()][a.getX()] = numToColor(cShape.getColor());
            }
        }
        return ret;
    }

    public String makeTable(){
        String[][] tble = getBoard();
        String s = new String();
        StringBuilder str = new StringBuilder("<table>");
        for (int y = 0; y < sizeY; y++){
            str.append("<tr>");
            for(int x = 0; x < sizeX; x++){
                str.append("<td bgcolor=\"");
                str.append(tble[y][x]);
                str.append("\">1</td>");
            }
            str.append("</tr>");
        }
        str.append("</table>");
        return str.toString();
    }

    public int canPlacePiece(){
        for (Point p: cShape.getPoints()){
            if(board[p.getY()][p.getX()] != 0){
                cShape = null;
                return 0;
            }
        }
        return 1;
    }

    public void printBoard(){
        for(int i = 0; i < sizeY; i++){
            for(int j = 0; j < sizeX; j++){
                System.out.print(board[i][j]);
                System.out.print(" ");
            }
            System.out.println("<br />");
        }
        System.out.println("<br /><br />");
    }
}


