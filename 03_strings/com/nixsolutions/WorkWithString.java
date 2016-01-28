package com.nixsolutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;


public class WorkWithString {

    public static String initials(String firstName, String lastName, String patronymic) {
        return (firstName.substring(0, 1) + lastName.substring(0, 1) + patronymic.substring(0, 1)).toUpperCase();
    }

    public static boolean isAnagram(String line1, String line2) {
        String pattern = "[!.,;`'()-? ]";
        List chars1  = Arrays.asList(line1.replaceAll(pattern,"").split(""));
        List chars2 = Arrays.asList(line2.replaceAll(pattern,"").split(""));
        Collections.sort(chars1);
        Collections.sort(chars2);
        return chars1.toString().equals(chars2.toString());
    }

    public static void main(String[] argc) {
        //I have 1st variant
        System.out.println("1. Some variant for concat two String");
        String word1 = "Java", word2 = "forever";
        System.out.println("-" + word1 + " " + word2);
        System.out.println("-" + word1.concat(" ").concat(word2));
        System.out.println("-" + new StringBuilder(word1).append(" ").append(word2));
        System.out.println("2. Initials Dmitry Zinovyi Petrovich: " + initials("Dmitry", "Zinovti", "Petrovich"));
        System.out.println("3. Is \"покраснение\" and \"пенсионерка\" anagram? "
                + isAnagram("покраснение", "пенсионерка"));
        System.out.println("   Is \"полковник\" and \"клоповник\" anagram? "
                + isAnagram("полковник", "клоповник"));
    }

}
