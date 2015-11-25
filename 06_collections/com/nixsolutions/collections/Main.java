package com.nixsolutions.collections;

public class Main {

	public static void main(String[] args) {

		CustomCollection<String> strCol = new CustomCollection<>();
		for (int i = 0; i < 20; i++) {
			strCol.add("Collection's stuff # " + i);
		}
		for (String str : strCol) {
			strCol.remove(str);
		}
		System.out.println("printed");

	}

}
