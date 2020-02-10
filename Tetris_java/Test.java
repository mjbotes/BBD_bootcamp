package test;

public class Test{
    public static void main(String[] args) {
        int i = (int)(Math.random() * 7);
        Board b = new Board(20, 10, i);
        for (int m = 0; m < 19; m++){
            int j = (int)(Math.random() * 7);
            b.moveSD(j);
        }
        while(b.getInprogress() == 1){
            int j = (int)(Math.random() * 7);
            b.moveSD(j);
        }
        System.out.println(b.makeTable());
    }
}