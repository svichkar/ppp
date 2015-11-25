package com.nixsolutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringConverter {

	public static void getInitials(String fio) {
		String str = fio.trim();
		str = str.replaceAll("[,.!?]{1,}", " ");
		str = str.replaceAll("[\\s]{2,}", " ");
		String[] arrStr = str.split(" ");
		System.out.println("FIO: " + arrStr[0].substring(0, 1) + arrStr[1].substring(0, 1) + arrStr[2].substring(0, 1));
	}

	public static void checkIfAnagramma(String str1, String str2) {

		String str1WithoutGarbage = str1.replaceAll("[,.!?\\s]{1,}", " ").trim();
		String str2WithoutGarbage = str2.replaceAll("[,.!?\\s]{1,}", " ").trim();
		String str1Modified = str1WithoutGarbage.replaceAll("[\\s]{1,}", "");
		String str2Modified = str2WithoutGarbage.replaceAll("[\\s]{1,}", "");

		if (str1Modified.length() == str2Modified.length()) {
			char[] charArrayStr1 = str1Modified.toCharArray();
			char[] charArrayStr2 = str2Modified.toCharArray();
			if (charArrayStr1.length == charArrayStr2.length) {
				boolean res = false;
				for (int j = 0; j < charArrayStr1.length; j++) {
					res = str1Modified.contains(Character.toString(charArrayStr2[j]));
					if (!res) {
						System.out.println("Text: '" + str1WithoutGarbage + "' is NOT anagramma for text: '"
								+ str2WithoutGarbage + "'");
						break;
					}

				}
				if (res)
					System.out.println(
							"Text: '" + str1WithoutGarbage + "' is anagramma for text: '" + str2WithoutGarbage + "'");
			} else {
				System.out.println(
						"Text: '" + str1WithoutGarbage + "' is NOT anagramma for text: '" + str2WithoutGarbage + "'");
			}

		} else {
			System.out.println(
					"Text: '" + str1WithoutGarbage + "' is NOT anagramma for text: '" + str2WithoutGarbage + "'");
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sBuild = new StringBuilder();
		StringBuffer sBuff = new StringBuffer();

		String str1 = "Java";
		String str2 = "forever";

		String resStr1 = str1 + " " + str2;
		String resStr2 = (str1 + " ").concat(str2);
		sBuild.append(str1).append(" ").append(str2);
		String resStr3 = sBuild.toString();
		sBuff.append(str1).append(" ").append(str2);
		String resStr4 = sBuff.toString();

		System.out.print("Please enter your First Name, Last Name and Otchestvo:) : ");
		String fio = br.readLine();
		getInitials(fio);

		System.out.println();

		System.out.print("Please enter a text: ");
		String text = br.readLine();
		System.out.print("Please enter another text to check if anagramma: ");
		checkIfAnagramma(text, br.readLine());

	}
}
