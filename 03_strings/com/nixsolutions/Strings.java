package com.nixsolutions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by kozlovskij on 11/12/2015.
 */
public class Strings {
    protected static String reverse(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            sb.append(input.charAt(input.length() - i - 1));
        }
        return sb.toString();
    }

    protected static String deleteChars(String input, char temp, int quantity) {
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        sb.append(input);
        for (int i = 0; i < input.length() && counter < quantity; i++) {
            if (String.valueOf(temp).equalsIgnoreCase(String.valueOf(input.charAt(i)))) {
                sb.deleteCharAt(i - counter);
                counter++;
            }
        }
        return sb.toString();
    }

    protected static String deleteChars(String input, char temp, int quantity, boolean oppositeDirection) {
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        sb.append(input);
        if (oppositeDirection) {
            for (int i = 0; i < input.length() && counter < quantity; i++) {
                if (String.valueOf(temp).equalsIgnoreCase(String.valueOf(input.charAt(input.length() - i - 1)))) {
                    sb.deleteCharAt(sb.length() - i + counter - 1);
                    counter++;
                }
            }
            return sb.toString();
        } else {
            return Strings.deleteChars(input, temp, quantity);
        }
    }

    protected static String wordSort(String input) {
        String[] arrayTemp = input.split("");
        String temp;
        for (int i = 0; i < arrayTemp.length; i++) {
            for (int j = 0; j < arrayTemp.length; j++) {
                if (arrayTemp[i].compareToIgnoreCase(arrayTemp[j]) < 0) {
                    temp = arrayTemp[i];
                    arrayTemp[i] = arrayTemp[j];
                    arrayTemp[j] = temp;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrayTemp.length; i++) {
            if (i == 0) {
                sb.append(arrayTemp[i]);
            }
            if (i > 0 && arrayTemp[i].compareToIgnoreCase(arrayTemp[i - 1]) != 0) {
                sb.append(arrayTemp[i]);
            }
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println(Strings.reverse("You know nothing Jon Snow"));
        System.out.println(Strings.deleteChars("You know nothing Jon Snow", 'o', 3));
        System.out.println(Strings.deleteChars("You know nothing Jon Snow", 'o', 3, true));
        System.out.println(Strings.wordSort("You know nothing Jon Snow"));
    }
}
