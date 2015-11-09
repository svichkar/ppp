/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basetypes;

import java.util.Scanner;

/**
 *
 * @author mednorcom
 * Task2: Написать программу которая будет просить пользователя вводить числа с плавающей точкой и 
 * выводить на экран введенное значение в экспоненциальном виде, если оно было введено в нормальном 
 * виде и, наоборот, выводить в нормальном если число было введено в экспоненциальном виде
 */
public class BaseTypes2 {

    public static void main(String[] args) {

        System.out.printf("Please enter your number\n");       
        String userInput = new Scanner(System.in).nextLine();      
        
        if (userInput.matches("\\d+e.*\\d+\n")) {
            Double parsedValue = Double.parseDouble(userInput);
            System.out.printf("Converted: %f\n",parsedValue);
        } else if (userInput.matches("\\d+.*\\d*\n")) {
            Double parsedValue = Double.parseDouble(userInput);
            System.out.printf("Converted: %e\n", parsedValue);
        } else {
            System.out.printf("Please enter decimal or exponential number"
                    + " like\na)3.14\nb)3e+10\n");
        }
        
    }
}
