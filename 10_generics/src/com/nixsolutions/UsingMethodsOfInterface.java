package com.nixsolutions;

/**
 * The Class UsingMethodsOfInterface.
 */
public class UsingMethodsOfInterface {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		//demonstration of Converting Float number to Double number.
		Float floatNum = new Float(12.44f);
		System.out.println("Value of Float number - " +  floatNum);
		Double doubleNum = new Double(0.0);
		FloatToDouble floatToDouble = new FloatToDouble();
		doubleNum = floatToDouble.get(floatNum);
		System.out.println("Converting Float number to Double number...");
		System.out.println("Value of Double number - " +  doubleNum);
		System.out.println("");
		
		
		//demonstration of Converting massive of Integers number to String format.
		MassIntToString massIntToString = new MassIntToString();
		String resultString = new String();
		int[] inputMass = {1,2,3,4,5,6,7,8,9};
		resultString = massIntToString.get(inputMass);
		System.out.println("Result String is - " +  resultString);
		

	}

}
