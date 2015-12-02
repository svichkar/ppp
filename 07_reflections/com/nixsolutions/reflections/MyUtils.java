package com.nixsolutions.reflections;

import java.lang.reflect.Field;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.ReflectionUtils;

/**
 * utils class which contains 2 static methods. Both methods get all annotated
 * fields, merge them in a string and return this string.
 * 
 * @author kryzhanovskiy
 *
 */
public class MyUtils {
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * the method gets all annotated fields, merge them in a string and returns
	 * this string. Only default java methods are in use
	 * 
	 * @param someObject
	 * @return String with values of the all this Object fields merged in to the
	 *         string
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static String getAnnotationFields(Object someObject)
			throws IllegalArgumentException, IllegalAccessException {
		LOG.entry(someObject.getClass());
		Field[] field = someObject.getClass().getDeclaredFields();
		StringBuilder objFields = new StringBuilder();

		for (Field fl : field) {
			if (fl.isAnnotationPresent(ToString.class)) {
				fl.setAccessible(true);
				objFields = objFields.append(fl.getName() + ": "
						+ fl.get(someObject).toString() + "; ");
			}
		}
		return LOG.exit(objFields.toString().trim());
	}

	/**
	 * the method gets all annotated fields, merge them in a string and returns
	 * this string. GUAVA lib used for the processing
	 * 
	 * @param someObject
	 * @return String with values of the all this Object fields merged in to the
	 *         string
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static String googleGetAnnotFileds(Object someObject)
			throws IllegalArgumentException, IllegalAccessException {
		LOG.entry(someObject.getClass());
		StringBuilder objFields = new StringBuilder();
		Set<Field> field = ReflectionUtils.getAllFields(someObject.getClass(),
				ReflectionUtils.withAnnotation(ToString.class));

		for (Field fl : field) {
			fl.setAccessible(true);
			objFields = objFields.append(
					fl.getName() + ": " + fl.get(someObject).toString() + "; ");
		}
		return LOG.exit(objFields.toString().trim());
	}
}
