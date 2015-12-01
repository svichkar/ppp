package generic.com;

public class ConvertArr implements Converter<String, Integer[]> {

	@Override
	public String get(Integer arg[]) {
		String str = "";
		for (Integer e : arg) {
			str += e.toString() + " ";
		}
		return str.substring(0, str.length() - " ".length());
	}
}
