package com.nixsolutions;

import java.util.Arrays;
/**
 * Created by Rybkinrolla on 12.11.2015.
 */
public class WorkWithString {
    public static void main(String[] args) {
        String firstString = "Java";
        String secondString = "forever";
        System.out.println(firstString + " " + secondString);
        System.out.println(firstString.concat(" ").concat(secondString));
        System.out.println((firstString + secondString).replace("va","va "));
        StringBuilder sb = new StringBuilder();
        System.out.println(sb.append(firstString).append(" ").append(secondString));
        StringBuilder sb2 = new StringBuilder(firstString);
        System.out.println(sb2.insert(4," ").insert(5,secondString));
        System.out.println();
        getFirstCharacterOfEachWord(" Vitaliy Nikolaevich   Rybkin  ");
        System.out.println();
        System.out.println(isAnagram("Polkovnik","Kl..o   povnik-..,"));
        System.out.println(isAnagram("Polkovnik","Klopovnii"));
    }
    public static void getFirstCharacterOfEachWord(String fullName){
        StringBuilder sb = new StringBuilder();
        String[] resultString = fullName.trim().split("[^A-Z]");
        for (int i = 0; i < resultString.length; i++) {
            sb.append(resultString[i]);
        }
        System.out.println(sb.toString());
    }
    public static boolean isAnagram(String phrase, String anagram){
        char[] startPhrase = phrase.replaceAll("\\W","").toLowerCase().toCharArray();
        char[] anagramPhrase = anagram.replaceAll("\\W","").toLowerCase().toCharArray();
        Arrays.sort(startPhrase);
        Arrays.sort(anagramPhrase);
        return String.valueOf(startPhrase).equals(String.valueOf(anagramPhrase));
    }
}
