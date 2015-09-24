package com.nixsolutions;

public class SrtingCutter {

  public static void main(String[] args) {
    String str1 = stringCutter("method deletes a sequence", 'e', 2, true);
    String str2 = stringCutter("method deletes a sequence", 'e', 3, false);
  }

  /**
   * Method will remove selected char from front(if deleteFromFrontOfString - true ) of input sting
   * 
   * @param input - input string
   * @param charToRemove - char which will be removed from string
   * @param howMuchChars - how much chars will be removed from start or end of input string
   * @param deleteFromFrontOfString - start point of deletion if true - front , if false - back
   * 
   * @return new string after chars removing
   */
  private static String stringCutter(String input, char charToRemove, int howMuchChars,
      boolean deleteFromFrontOfString) {
    System.out.println(input);
    StringBuffer strBuff = new StringBuffer(input);
    if (deleteFromFrontOfString) {
      for (int i = 0; i < howMuchChars; i++) {
        strBuff = strBuff.deleteCharAt(strBuff.toString().indexOf(charToRemove));
      }
    } else {
      for (int i = 0; i < howMuchChars; i++) {
        strBuff = strBuff.deleteCharAt(strBuff.toString().lastIndexOf(charToRemove));
      }
    }
    System.out.println(strBuff.toString());
    return strBuff.toString();

  }


}
