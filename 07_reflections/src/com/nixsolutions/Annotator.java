package com.nixsolutions;

import java.lang.reflect.Field;

public class Annotator {

    public Annotator(Class pclass) {
	Field[] methods = pclass.getFields();
	for (Field field : methods) {
	    if (field.isAnnotationPresent(ToString.class)) {
		// ToString annotations = field.getAnnotation(ToString.class);
		try {
		    field.setAccessible(true);

		    try {
			Object erw = field.get(pclass.newInstance());

			System.out.println(
				"This class contain next field \"" + field.getName() + "\" with value - " + erw);
		    } catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }

		} catch (IllegalArgumentException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} catch (IllegalAccessException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }
	}

    }

}
