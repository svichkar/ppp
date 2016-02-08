package com.nixsolutions;

import java.util.Scanner;

/** Implements task 2 from variant 2 of 03_strings lab */
public class RemoveSpecifiedChars {
    /**
     * Removes specified characters specified number of times from string based
     * on user input.
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the string to remove specified letters: ");
        StringBuilder sb = new StringBuilder(scan.nextLine());

        System.out.println(
                "Enter a single character to remove from the string: ");
        Character c = Character.toLowerCase(scan.next().charAt(0));

        System.out.println("Enter the number of instances of char to remove: ");
        int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < sb.length(); j++) {
                if (c.compareTo(Character.toLowerCase(sb.charAt(j))) == 0) {
                    sb.deleteCharAt(j);
                    break;
                }
            }
        }
        System.out.println("\nFinal string:\n" + sb);
    }

}