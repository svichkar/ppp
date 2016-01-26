package floating;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Scanner;

public class FloatingPoint {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line;

        while (true) {
            System.out.print("Enter floating point number or \"N\" to exit: ");
            line = sc.next();
            if (line.equals("N")) break;
            convertFloating(line);
        }
    }

    private static void convertFloating(String line){

        try {
            BigDecimal parsedLine = new BigDecimal(line);
            if (line.toUpperCase().contains("E")){
                System.out.printf(parsedLine.toPlainString());
                System.out.println();
            }
            else{
                DecimalFormat formatter = new DecimalFormat("0.#######E0");
                System.out.println(formatter.format(parsedLine));
            }
        }
        catch (NumberFormatException e){
            System.out.print("Not a valid number. ");
        }
    }
}
