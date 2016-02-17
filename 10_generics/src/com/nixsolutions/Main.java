package com.nixsolutions;

import java.util.*;

/**
 * Created by sobolenko on 2/17/2016.
 */
public class Main {
    static Random random = new Random();

    public static void main(String[] args) {
        Map<String, List<Number>> newMap = new Hashtable<String, List<Number>>();
        //String keyString = generateRandomString();
        //List<Number> number = generateRandomNumber();
        for(int i=0;i<10;i++)
        {
            newMap.put(generateRandomString(),generateRandomNumber());
        }
        Adder adder = new Adder();
        adder.addNumbers(generateRandomString(),generateRandomNumber());
    }

    public static String generateRandomString() {
        Random random = new Random();
        char newChar;
        String result = "";
        for (int i = 0; i < (random.nextInt(5) + 3); i++) {
            newChar = (char) (random.nextInt(95) + 32);
            if (Character.isLetterOrDigit(newChar)) {
                result += newChar;
            }
        }
        return result;
    }

    public static List<Number> generateRandomNumber() {
        List<Number> newNumber = new ArrayList<Number>();
        int sw = random.nextInt(3)+1;
        switch (sw)
        {
            case 1:
                for (int i = 0; i < (random.nextInt(7) + 3); i++) {
                    newNumber.add(random.nextInt(95));
                }
                break;
            case 2:
                for (int i = 0; i < (random.nextInt(7) + 3); i++) {
                    newNumber.add(random.nextFloat()*10);
                }
                break;
            case 3:
                for (int i = 0; i < (random.nextInt(7) + 3); i++) {
                    newNumber.add(random.nextLong());
                }
                break;
        }
        return newNumber;
    }
}
