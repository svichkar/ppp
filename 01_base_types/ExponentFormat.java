import java.util.Scanner;

/**
 * Created by rybkin on 11/6/2015.
 */
public class ExponentFormat {
    public static void main(String[] args) {
        double value;
        System.out.println("Enter number casual or exponential format: ");
        Scanner scanIn = new Scanner(System.in);
        String ourNumber = scanIn.nextLine();
        try {
            if (ourNumber.toLowerCase().contains("e")) {

                value = Double.parseDouble(ourNumber);
                System.out.printf(String.format("Your number in casual format is: %.20f", value).replaceAll("\\.?0*$", ""));
            } else {
                value = Double.parseDouble(ourNumber);
                System.out.println(String.format("Your number in exponential format is %e: ", value));
            }
        } catch (Exception e) {
            System.out.println("Error. You should enter proper exponential number like \"123E2\" or just a digits like \"12345.01\"");
        }

        scanIn.close();
    }
}
