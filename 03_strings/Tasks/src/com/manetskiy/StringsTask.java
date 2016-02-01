package com.manetskiy;

import java.util.*;

public class StringsTask {
    public static void main(String[] args) {
        //System.out.println(reverseString("The first node in the network is a satellite that was launched from Baikonur"));
        //System.out.println(deleteChars("Why does Jon Snow know nothing?", 'o', 3));
        //System.out.println(removeDuplicatedChars("An ordered collection (also known as a sequence)"));
        //System.out.println(removeDuplicatedChars2("An ordered collection (also known as a sequence)"));

    }

    public static String reverseString(String inputString) {
        char[] chars = inputString.toCharArray();
        char temp;
        int begin = 0;
        int end = inputString.length() - 1;
        while (end > begin) {
            temp = chars[begin];
            chars[begin] = chars[end];
            chars[end] = temp;
            begin++;
            end--;
        }
        return new String(chars);
    }

    static String deleteChars(String s, char forDelete, int numberOfChars) {
        StringBuilder sb = new StringBuilder(s);
        int counter = 0;
        for (int i = 0; i < sb.length(); i++) {
            if ((sb.charAt(i) == forDelete) && counter < numberOfChars) {
                sb.deleteCharAt(i);
                counter++;
            }
        }
        return sb.toString();
    }

    //fast way
    static char[] removeDuplicatedChars(String s) {
        char[] chars = s.toLowerCase().toCharArray();
        Arrays.sort(chars);
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (int i = 0; i < chars.length; i++) {
            while (true) {
                if ((counter + 1) < chars.length) {
                    if (chars[counter] == (chars[counter + 1])) {
                        counter++;
                    } else {
                        i = counter;
                        sb.append(chars[i]);
                        counter++;
                        break;
                    }
                } else {
                    i = counter;
                    sb.append(chars[chars.length - 1]);
                    break;
                }
            }
        }
        return sb.toString().toCharArray();
    }

    //slow way using Set
    static char[] removeDuplicatedChars2(String s) {
        char[] chars = s.toLowerCase().toCharArray();
        Set<Character> setChars = new HashSet<>();

        for (int i = 0; i < chars.length; i++) {
            setChars.add(chars[i]);
        }
        Character[] ch = setChars.toArray(new Character[setChars.size()]);
        char[] toReturn = new char[ch.length];

        for (int i = 0; i < toReturn.length; i++) {
            toReturn[i] = ch[i];
        }
        Arrays.sort(toReturn);

        return toReturn;
    }


}
