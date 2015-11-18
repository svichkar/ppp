
public class Feltpen extends Basic {

	double index1 = 1;
	double index2 = 1.09;
	double index3 = 1.21;
	int countForIndex1 = 20;
	int countForIndex2 = 20;

	protected Feltpen(String color) {

		this.color = color;
	}

	@Override
	void write(StringBuilder mainStr, String strwrite) {

		int strlength = strwrite.length();
		
		//max length of string for  Feltpen
		int limit = (int) ((countForIndex1 + countForIndex2
				+ (balance - countForIndex1 * index1 - index2 * countForIndex2) / index3));

		strwrite = strwrite.length() > limit ? strwrite.substring(0, limit) : strwrite;

		strlength = strwrite.length();

		if (strlength >= (countForIndex1 + countForIndex2)) {

			this.balance = this.balance - ((strlength - countForIndex1 - countForIndex2) * index3
					+ countForIndex1 * index1 + countForIndex2 * index2);

		} else {

			if (strlength >= countForIndex1) {

				this.balance = this.balance - ((strlength - countForIndex2) * index2 + countForIndex1 * index1);
			} else {

				this.balance = this.balance - strlength * index1;
			}
		}
		System.out.println(color + " Feltpen " + strwrite + ' ' + this.balance + ' ' + limit);
		mainStr = mainStr.append(strwrite);

	}

}
