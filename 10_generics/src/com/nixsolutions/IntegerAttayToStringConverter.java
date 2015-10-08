package com.nixsolutions;

public class IntegerAttayToStringConverter implements Converter<String, Integer[]> {

    @Override
    public String get(Integer[] i) {
	String temp = "";
	for (Integer integer : i) {
	    temp += integer + " ";
	}

	return temp.trim();
    }

}
