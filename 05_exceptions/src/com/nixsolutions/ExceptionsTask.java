package com.nixsolutions;

public class ExceptionsTask {

    public static void main(String[] args) {
	WriteTextInFile writeText = new WriteTextInFile();
	writeText.save("Hello World!", "Test.txt");
    }
}
