package com.nixsolutions;

        import java.util.Random;

/**
 * Created by kozlovskij on 11/11/2015.
 */
public class Main {
    public static void main(String[] args) {
        Figures figures[] = new Figures[10];
        Random random = new Random();
        int type;
        for (int i = 0; i < figures.length; i++) {
            type = random.nextInt(3);
            switch (type) {
                case 0:
                    figures[i] = new Circle();
                    break;
                case 1:
                    figures[i] = new Square();
                    break;
                case 2:
                    figures[i] = new Triangle();
                    break;
                default:
                    System.err.println("Error in switch");
                    i--;
                    break;
            }
            figures[i].reSize(Math.random());
        }
        Figures temp;
        for (int i = 0; i < figures.length; i++) {
            for (int j = 0; j < figures.length; j++) {
                if (figures[i].getSquare() < figures[j].getSquare()) {
                    temp = figures[i];
                    figures[i] = figures[j];
                    figures[j] = temp;
                }
            }
        }
        for (Figures elem : figures) {
            System.out.println(elem.toString());
        }
    }
}
