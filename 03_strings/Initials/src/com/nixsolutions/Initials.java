package com.nixsolutions;

import java.util.ArrayList;
import java.util.Scanner;

public class Initials {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fio;
		String initials = "";
		System.out.println("Please eneter your full name(lastName firstName patronymic):\n");
		Scanner in = new Scanner(System.in);
		fio = in.nextLine();
		in.close();
		StringBuilder builder = new StringBuilder(fio);
		int index = 0;
		initials = "";
		while (index <= builder.lastIndexOf(" ") + 1) {
			initials += builder.substring(index, index + 1);
			index = builder.indexOf(" ", index) + 1;
		}
		System.out.println("StringBuilder: " + initials + "\n");
		String[] fioList = fio.split(" ");
		for (String name : fioList) {
			initials += name.substring(0, 1);
		}
		System.out.println("initials: " + initials + "\n");

	}

}
