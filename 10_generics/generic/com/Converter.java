package generic.com;

public interface Converter<I, E> {
	I get(E arg);
}
