package com.nixsolutions;

import java.util.Random;

/**
 * Class for getting Initials from string that contains full name
 * 
 * @author maxb
 * 
 */
public class Initial {

	public static void main(String[] args) {

		String[] middleName = new String[] { "Иванович", "Николаевич",
				"Петрович", "Витольдович", "Изольдович", "Инокентьевич",
				"Икакевич" };
		String[] firstName = new String[] { "Онуфрий", "Икакий", "Сигизмунд",
				"Марьян", "Рудольф", "Эдуард", "Изольд" };
		String[] lastName = new String[] { "Янукович", "Ахметов", "Колесник",
				"Богатырев", "Кернес", "Царев", "Штеп", "Шульц" };
		// /creating array with full names
		String[] fullNames = new String[10];
		for (int i = 0; i < fullNames.length; i++) {
			fullNames[i] = firstName[new Random().nextInt(firstName.length)]
					+ " " + middleName[new Random().nextInt(middleName.length)]
					+ " " + lastName[new Random().nextInt(lastName.length)];

			System.out.printf("%1s = %2s%n", fullNames[i],
					getInitials(fullNames[i]));

		}

	}

	/**
	 * Method for getting initials from full name
	 * 
	 * @param fullName
	 *            String with full name in order firstName, middleName, lastName
	 * @return String with initials in capital letters
	 */
	public static String getInitials(String fullName) {
		StringBuilder result = new StringBuilder();
		String[] tempNames = fullName.split(" ");
		for (String str : tempNames) {
			result.append(str.trim().substring(0, 1).toUpperCase());
		}
		return result.toString();
	}

}
