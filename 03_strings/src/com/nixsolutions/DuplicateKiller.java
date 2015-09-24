package com.nixsolutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DuplicateKiller {

  public static void main(String[] args) {

    String str = duplicateKiller(
        "Ќаписать программу, котора€ разбивает слово на буквы, удал€ет дубликаты(регистр игнорируетс€) и сортирует их в алфавитном пор€дке. ");
  }

  private static String duplicateKiller(String input) {
    List<String> listToRemove = new ArrayList<String>();
    char[] temp = input.toLowerCase().toCharArray();
    for (int i = 0; i < temp.length; i++) {
      for (int j = 0; j < temp.length; j++) {
        if (temp[i] == temp[j] && i != j) {
          listToRemove.add(String.valueOf(input.charAt(j)));
        }
      }
    }
    for (int i = 0; i < listToRemove.size(); i++) {
      input = input.toLowerCase().replace(listToRemove.get(i), "");
    }

    System.out.println(input);
    char[] toSort = input.toCharArray();
    Arrays.sort(toSort);
    System.out.println(String.valueOf(toSort));
    return String.valueOf(toSort);
  }

}
