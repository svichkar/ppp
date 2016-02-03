package com.nixsolutions;

public class ExceptionLab {

	public static void main(String[] args) {
		SaveImpl saveFile = new SaveImpl();
		saveFile.save("Aloha!", "D:\\greeting.txt");
		saveFile.save("Aloha to u!", "D:\\greeting.txt");
	}
}
