package com.nixsolutions;

import java.util.Scanner;

/** Implements task 1 from variant 2 of 03_strings lab */
public class ReverseWord {
  /**
  * Scans the string from the user input, converts it to 
  * the array of chars, then replaces opposite chars in
  * for loop until the middle of the array is reached. Finally
  * prints the reversed string.
  */
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter the word to reverse the order of the letters: ");
    char[] charArray = scan.next().toCharArray();
    
    for (int i = 0; i < charArray.length / 2; i++) {
      char temp = charArray[i];
      charArray[i] = charArray[charArray.length - 1 - i];
      charArray[charArray.length - 1 - i] = temp;
    }
    System.out.println("Reversed word:\n" + String.valueOf(charArray));
  }

}