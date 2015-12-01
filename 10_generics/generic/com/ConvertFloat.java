package generic.com;

public class ConvertFloat implements Converter<Double, Float> {
	@Override
	public Double get(Float arg) {
		return arg.doubleValue();
	}
}
