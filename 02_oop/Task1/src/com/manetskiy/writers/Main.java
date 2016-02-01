package com.manetskiy.writers;


import java.util.Random;

public class Main {
    private static Random rand = new Random();

    public static void main(String[] args) {
        StringBuilder outputText;
        SimpleWriter writers[] = new SimpleWriter[10];

        for (int i = 0; i < writers.length; i++) {
            int randomWriter = rand.nextInt(3);
            int randomDurability = rand.nextInt((300 - 100) + 1) + 100; //generates durability between 100 and 300
            switch (randomWriter) {
                case 0:
                    writers[i] = new Pencil(randomDurability);
                    break;
                case 1:
                    writers[i] = new Marker(randomDurability);
                    break;
                case 2:
                    writers[i] = new Pen(randomDurability);
                    break;
            }
        }

        for (int i = 0; i < 10; i++) {
            outputText = writers[i].write(generateRandomString());
            System.out.println("Output: " + outputText);
            writers[i].erase(outputText, outputText.length() - 1);
            System.out.println("Output after erasing: " + outputText);
            System.out.println(writers[i].toString());
            System.out.println();
        }

        System.out.println();

        for (int i = 0; i < writers.length; i++) {
            for (int j = 0; j < writers.length - 1; j++) {
                if (writers[j].getDurability() < writers[j + 1].getDurability()) {
                    SimpleWriter temp = writers[j];
                    writers[j] = writers[j + 1];
                    writers[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < writers.length; i++) {
            System.out.println(writers[i].toString());
        }

    }

    private static StringBuilder generateRandomString() {
        StringBuilder toReturn = new StringBuilder();
        rand = new Random();
        int i = 0;
        while (i < (rand.nextInt((5 - 3) + 1) + 3)) {
            char temp = (char) (rand.nextInt(92) + 32);
            if (Character.isLetterOrDigit(temp)) {
                toReturn.append(temp);
                ++i;
            }
        }
        return toReturn;
    }
}
