package com.nixsolutions;

import java.lang.reflect.InvocationTargetException;

/**
 * The Class OperationsWithAnnotations.
 */
public class OperationsWithAnnotations {

	/**
	 * The main method.
	 *
	 * @param arg the arguments
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws NoSuchMethodException the no such method exception
	 * @throws InvocationTargetException the invocation target exception
	 */
	public static void main(String[] arg)
			throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		UtilitarianAnnotation utilitarianAnnotation = new UtilitarianAnnotation();
		SimpleAnnotation simpleAnnotation = new SimpleAnnotation("Yuriy", "Golod");
		System.out.println("With Standart reflect:");
		String resultReflect = utilitarianAnnotation.getFieldWithAnnotationReflect(simpleAnnotation);
		System.out.println(resultReflect);
		System.out.println("With Commons reflect:");
		String resultCommons = utilitarianAnnotation.getFieldWithAnnotationCommons(simpleAnnotation);
		System.out.println(resultCommons);
	}

}
