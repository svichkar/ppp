package com.nixsolutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task2 {
    public static void main(String[] args) {
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	String inputString = "";
	try {
	    inputString = reader.readLine().toString(); // read typed value
	} catch (IOException e) {
	    e.printStackTrace();
	}
	if (inputString.toLowerCase().contains("e")) {
	    System.out.format("%.10f", Double.valueOf(inputString)); // print
								     // value in
								     // float
								     // format
	} else {
	    System.out.format("%.5E", Double.valueOf(inputString)); // print
								    // value in
								    // exponential
								    // format
	}
    }
}
