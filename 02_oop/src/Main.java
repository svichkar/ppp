import java.util.Arrays;
import java.util.Random;

public class Main {

	public static void main(String[] args) {

		StringBuilder MainSb = new StringBuilder("");
		String AllChars = "abcdefghijklmnopqrstuvwxyzABCEDFGHIJKLMNOPQRSTUVWXYZ1234567890";
		Random rn = new Random();
		int rnd;
		Basic[] arr = new Basic[10];

		// Creating array
		for (int i = 0; i < 10; i++) {

			rnd = rn.nextInt(3);
			if (rnd == 0) {
				arr[i] = new Feltpen("redFeltpen");
				arr[i].prepearing();
			}
			if (rnd == 1) {
				arr[i] = new Pen("greenPen");
				arr[i].prepearing();
			}
			if (rnd == 2) {
				arr[i] = new Pencil("pinkPencil");
				arr[i].prepearing();

			}
		}

		// Write to StringBuilder
		for (int i = 0; i < 10; i++) {

			int length = rn.nextInt(2) + 3;
			String strtowrite = generateString(rn, AllChars, length);
			arr[i].write(MainSb, strtowrite);

			if (arr[i] instanceof Pencil) {
				((Pencil) arr[i]).delete(MainSb);
			}
		}

		System.out.println(MainSb);

		// Sort array by balance
		Arrays.sort(arr);
		for (int i = 0; i < 10; i++) {

			System.out.println(arr[i].balance);
		}
	}

	public static String generateString(Random rng, String characters, int length) {

		char[] text = new char[length];
		for (int i = 0; i < length; i++) {

			text[i] = characters.charAt(rng.nextInt(characters.length()));
		}
		return new String(text);
	}
}
