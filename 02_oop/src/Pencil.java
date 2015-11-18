
public class Pencil extends Basic {

	int sharpness = 0;
	double index = 0.95;
	double indexSharp = 3;
	int countForSharp = 20;

	protected Pencil(String color) {

		this.color = color;
	}

	/**
	 * Writes to console StringBuilder
	 * 
	 * @param StringBuilder
	 * @param String
	 * @return StringBuilder that had been written
	 */
	@Override
	void write(StringBuilder mainStr, String strwrite) {

		double valueOnePart = index * countForSharp + indexSharp;
		int countParts = (int) (this.balance / valueOnePart);
		//max length of string for  Feltpen
		int limit = (int) ((this.balance - countParts * valueOnePart) / index) + (int) (countParts * countForSharp);

		int strlength = strwrite.length();

		strwrite = strwrite.length() > limit ? strwrite.substring(0, limit) : strwrite;
		strlength = strwrite.length();

		sharpness = sharpness + strlength;

		this.balance = this.balance - strlength * index - indexSharp * (sharpness / countForSharp);

		System.out.println(color + " Pencil " + strwrite + ' ' + this.balance + ' ' + limit);
		mainStr = mainStr.append(strwrite);
	}

	/**
	 * Removes the last symbol in StringBuilder
	 * 
	 * @param stringBuilder
	 */
	void delete(StringBuilder mainStr) {

		int index = mainStr.length() - 1;
		mainStr = mainStr.deleteCharAt(index);

	}
}
