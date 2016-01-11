package com.nixsolutions;

public class ProgramRunner {

	public static void main(String[] args) {

		String inputString = "You have a sugare and I have a spice, come with we and we will feel nice"; 
		String toDelete = "a";
		int n = 3; 
		boolean direction = true;
		
		WordsChanger test = new WordsChanger();
		
		//execution task #1
		System.out.println(test.stringReverse(inputString));
		//execution task #2
		System.out.println(test.symbolsDeletter(inputString, toDelete, n, direction));
		//execution task #3
		System.out.println(test.wordModifier(inputString));
		
	}

}
