package com.nixsolutions;

import javax.swing.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * Created by sobolenko on 1/26/2016.
 */
public class ExponentialForm {
    public static void main(String[] args) throws ParseException {
        String input = "";
        float number = 0;
        boolean isExeption = false;
        do {
            try {
                input = JOptionPane.showInputDialog("Please type float number");
                number = Float.parseFloat(input);
                isExeption = false;
            } catch (NumberFormatException e) {
                isExeption = true;
            }
        } while (isExeption);
        BigDecimal bigDecimal = new BigDecimal(input);
        if (input.equals(String.valueOf(bigDecimal))) {
            DecimalFormat newFormatter = new DecimalFormat("0.00E00");
            System.out.println(newFormatter.format(number));
        } else {
            DecimalFormat newFormatter = new DecimalFormat();
            System.out.println(newFormatter.format(number));
        }
    }
}
