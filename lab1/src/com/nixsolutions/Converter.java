package com.nixsolutions;

import java.io.*;
import java.math.BigDecimal;

public class Converter {

	public void converter() {
		
		System.out.println("Enter number to convertion here : ");
		 String inputString = null; 
		try{
		    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		     inputString = bufferRead.readLine();
		  		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
				
		BigDecimal bd = new BigDecimal(inputString);
		System.out.println(bd.doubleValue());

		
		
	}
	
}
