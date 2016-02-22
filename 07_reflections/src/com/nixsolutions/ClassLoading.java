package com.nixsolutions;

/**
 * The Class ClassLoading.
 */
public class ClassLoading {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String pathToNeededClass = ("D:\\Java\\javappp\\06_collections\\bin");
		CustomClassLoader customClassLoader = new CustomClassLoader(pathToNeededClass,
				ClassLoader.getSystemClassLoader());
		Class<?> myLoadedClass = customClassLoader.loadClass("com.nixsolutions.CustomCollection");
		Object customCollection = myLoadedClass.newInstance();
		System.out.println("Custom class loader info:");
		System.out.println(myLoadedClass.getClassLoader().toString());
		System.out.println(customCollection.getClass().toString());
		System.out.println();

		System.out.println("Java class loader info:");
		Class<?> standartClass = customClassLoader.loadClass("java.lang.String");
		String stringClass = (String) standartClass.newInstance();
		System.out.println(standartClass.getClassLoader());
		System.out.println(stringClass.getClass().toString());

	}
}
