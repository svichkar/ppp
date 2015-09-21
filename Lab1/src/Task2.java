import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task2 {

	public static void main(String[] args) throws IOException {
		System.out.println("Enter a floating-point number: ");
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader keyboard = new BufferedReader(in);		
		String inputString = keyboard.readLine();
		Double d = Double.parseDouble(inputString);
		if (inputString.toLowerCase().contains("e")) {
			System.out.printf("%1.1f",d);
		} else {
			System.out.printf("%1.1E", d);
		}
	}
}
