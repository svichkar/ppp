package com.nixsolutions;

import java.util.*;

/**
 * Created by sobolenko on 2/3/2016.
 */
public class Adder {
    Map<String, List<Number>> newMap = new Hashtable<String, List<Number>>();
    List<Number> innerNumber = new ArrayList<Number>();

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
    public static void main(String[] args)
    {
        String keyString = generateRandomString();

    }
}
