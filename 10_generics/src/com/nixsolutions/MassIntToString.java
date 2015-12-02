package com.nixsolutions;

/**
 * The Class MassIntToString.
 */
public class MassIntToString implements Conventer<int[], String>{

	/* (non-Javadoc)
	 * @see com.nixsolutions.Conventer#get(java.lang.Object)
	 */
	@Override
	public String get(int[] data) {
		int[] inputIntegerMass;
		String resultString = new String();
		inputIntegerMass = data.clone();
		for(int i = 0; i<inputIntegerMass.length; i++)
		{
			resultString += inputIntegerMass[i]+" ";
		}
		return resultString;
	}

}
