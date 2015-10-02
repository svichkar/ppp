package com.nixsolutions;


public class SavingDataToFile {

	public static void main(String[] args) {
		MySave ms = new MySave();
		String[] words = new String[] { "", "else", "switch", "for", "while", "",
				"case", "catch", "throw", "finally", "if" };

		int counter = 0;
		String file = ".\\temp\\test.txt";
		while (counter < words.length) {
			System.out.printf("Writing word \"%1s\" to file \"%2s\" ....%n",
					words[counter], file);
			ms.save(words[counter], file);
			counter++;
		}
	}

}
