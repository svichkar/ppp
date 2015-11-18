
public class Pen extends Basic {

	double index = 1.15;

	Pen(String color) {

		this.color = color;
	}

	@Override
	void write(StringBuilder mainStr, String strwrite) {

		//max length of string for  Feltpen
		int limit = (int) (this.balance / index);
		int strlength = strwrite.length();

		strwrite = strwrite.length() > limit ? strwrite.substring(0, limit) : strwrite;

		strlength = strwrite.length();
		this.balance = this.balance - strlength * index;

		System.out.println(color + " Pen " + strwrite + ' ' + this.balance + ' ' + limit);

		mainStr = mainStr.append(strwrite);
	}
}
