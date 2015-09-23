package com.nixsolutions;

import java.util.Random;

/** This class is implemented for running the program for OOP task
 * @author maxb
 *
 */
public class Main {

	/**This is entry point into second java lesson task
	 * @author maxb
	 * @param args - define arguments that can be used. Not required for our case
	 */
	public static void main(String[] args) {
		Shape[] arrayFigure = new Shape[10];
		generateFigure(arrayFigure);
		resizeEachFigure(arrayFigure);
				
		sortShapesByArea(arrayFigure);
		System.out.printf("Min area of shape %1.5f \nMax area of shape %2.5f",
				arrayFigure[0].area(), arrayFigure[9].area());
	}

	/**This method is implemented for generating array of Shape objects (Circle, Square, Triangle)  
	 * @author maxb
	 * @param shapes Array of Shape objects
	 */
	public static void generateFigure(Shape[] shapes) {
		for (int i = 0; i < shapes.length; i++) {
			Random rnd = new Random();
			switch (rnd.nextInt(3)) {
			case 0:
				shapes[i] = new Triangle();
				break;
			case 1:
				shapes[i] = new Circle();
				break;
			case 2:
				shapes[i] = new Square();
				break;
			default:

				System.out
						.printf("Unexpected error during filling array of shapes. Element %s",
								i);
				break;
			}

		}

	}

	/**This method is implemented for changing size of each Shape object in the array
	 * @author maxb
	 * @param shapes Array of Shape objects
	 */
	public static void resizeEachFigure(Shape[] shapes) {
		Random rnd = new Random();
		for (int i = 0; i < shapes.length; i++) {
			shapes[i].resize(rnd.nextFloat());
		}
	}

	/**This method is implemented for sorting out Shape objects in array by size of area
	 * @author maxb 
	 * @param shapes Array of Shape objects
	 */
	public static void sortShapesByArea(Shape[] shapes) {
		for (int i = 0; i < shapes.length; i++) {
			for (int j = 0; j < shapes.length - i - 1; j++) {
				Shape someShape;
				if (shapes[j].area() > shapes[j + 1].area()) {
					someShape = shapes[j];
					shapes[j] = shapes[j + 1];
					shapes[j + 1] = someShape;
				}
			}

		}
	}

}
