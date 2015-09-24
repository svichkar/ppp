package com.nixsolutions;

public class Reverse {

  public static void main(String[] args) {
  
    String revercedString = reverse("abcdefghigk");
  }
  
  private static String reverse (String inputString){
    
    char[] temp = inputString.toCharArray();
    for (int i = 0; i < temp.length/2; i++) {
      char ch; 
      ch = temp[i] ;
      temp[i]=  temp[temp.length-i-1];
      temp[temp.length-i-1] = ch;
    }
    String outputStr = String.valueOf(temp);
    System.out.println(outputStr);
    return outputStr;
  } 

}
