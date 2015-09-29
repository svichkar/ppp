package com.nixsolutions;

import java.util.ArrayList;
import java.util.Scanner;

public class Initials {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fio;
		System.out.println("Please eneter your full name(lastName firstName patronymic):\n");
		fio = enterName();
		// by substring
		System.out.println("initials: " + getInitialsBySubString(fio) + "\n");
		// by StringBuilder
		System.out.println("StringBuilder: " + getInitialsByStringBuilder(fio) + "\n");

	}

	/**
	 * read text from console
	 */
	private static String enterName() {

		Scanner in = new Scanner(System.in);
		String fio = in.nextLine();
		in.close();

		return fio;
	}

	/**
	 * get initials by StringBuilder
	 */
	private static String getInitialsByStringBuilder(String startName) {
		StringBuilder builder = new StringBuilder(startName);
		int index = 0;
		String initials = "";
		do  {
			initials += builder.substring(index, index + 1);
			index = builder.indexOf(" ", index) + 1;
		}while(index !=0);
		return initials;
	}

	/**
	 * get initials by SubString
	 */
	private static String getInitialsBySubString(String startName) {
		String initials = "";
		String[] fioList = startName.split(" ");
		for (String name : fioList) {
			initials += name.substring(0, 1);
		}
		return initials;

	}
}
