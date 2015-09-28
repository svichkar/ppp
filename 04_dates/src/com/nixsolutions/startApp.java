package com.nixsolutions;

public class startApp {

  
  public static void main(String[] args) {
      DatesProcessor dp = new DatesProcessor();
      dp.mondayFinder();
      dp.showDateInDifferenLocales();
      dp.dateCalc();
      dp.isDateFirdayThirteenth();
      dp.getLenghtOfEachMonthForParticularYear();

  }
  
  

}
