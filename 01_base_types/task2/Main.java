package task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.*;

public class Main {
	
public static void main(String[] args) throws IOException{
		
		System.out.println("¬ведите число с плавающей точкой:");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = reader.readLine();
		
		if(input.contains("e")||input.contains("E"))			
			System.out.printf("Not Exponential: %f", Double.valueOf(input));				
		else
			System.out.printf("Exponential: %E", Double.valueOf(input));		
		
	}

}
