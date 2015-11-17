package com.nixsolutions;

public class SaveMain {

	public static void main(String[] args) {
		MySave s = new MySave();
		s.save("My string", "D:/fileForString.txt");
		s.save("New string", "D:/fileForString.txt");
	}

}
