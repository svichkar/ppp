package com.nixsolutions;

import java.util.Arrays;
import java.util.Scanner;

/** Implements task 3 from variant 2 of 03_strings lab */
public class SortDedupedLetters {
  /** 
  * Removes duplicate characters from a string,
  * splits it to chars, then sorts alphabetically.
  */
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    System.out.println("Enter the word to remove duplicate letters and sort the rest: ");
    StringBuilder sb = new StringBuilder(scan.nextLine());
    
    boolean needMoreChecks = true;
    while (needMoreChecks) {
      needMoreChecks = false;
      for (int i = 0; i < sb.length(); i++) {
        for (int j = i + 1; j < sb.length(); j++) {
          Character char1 = Character.toLowerCase(sb.charAt(i));
          Character char2 = Character.toLowerCase(sb.charAt(j));
          if (char1.compareTo(char2) == 0) {
            sb.deleteCharAt(j);
            needMoreChecks = true;
          }
        }
      }
    }
    
    String[] stringArray = sb.toString().split("");
    Arrays.sort(stringArray, String.CASE_INSENSITIVE_ORDER);
    System.out.println("\nFinal String:");
    for (int i = 0; i < stringArray.length; i++) {
      System.out.print(stringArray[i]);
    }
  }

}