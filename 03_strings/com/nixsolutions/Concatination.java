package com.nixsolutions;

public class Concatination {
	public static void main(String[] args) {
		String strJava = "Java";
		String strForever = "forever";
		
		String result1 = strJava + " " + strForever;
		String result2 = strJava.concat(" ").concat(strForever);
		String result3 = String.format("%s %s", strJava, strForever);
		
		StringBuilder strBldr = new StringBuilder();
		String result4 = strBldr.append(strJava).append(" ").append(strForever).toString();

		StringBuilder strBldr2 = new StringBuilder(strJava);
		String result5 = strBldr2.insert(strJava.length() ," " + strForever).toString();
		
		System.out.println("1st variant (string + string): " + result1);
		System.out.println("2nd variant (string concat): " + result2);
		System.out.println("3rd variant (string format): " + result3);
		System.out.println("4th variant (sting builder - append): " + result4);
		System.out.println("5th variant (sting builder - insert): " + result5);
	}
}
