package com.nixsolutions;

public class StringConverter<E, T> implements Converter<T, E> {
	private E[] intMass;

	public StringConverter() {
	}

	@Override
	public <T> T get(E element) {
		T strElem =(T)"";
		this.intMass = (E[]) element;
		for (int i=0; i<intMass.length;i++)
			strElem = (T)(strElem.toString() + (intMass[i] + " ").toString());
		return (T) strElem;
	}

}
