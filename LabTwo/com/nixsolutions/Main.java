package com.nixsolutions;

import java.util.Random;

/**Main class is created to hold the main method for the "OOP" quiz. 
 * @author	kulishov */
public class Main {
	
	/**main method is used as an access point to run the program. 
	 * @author	kulishov 
	 * @param	args	defines parameters that are used. */
	public static void main(String[] args){
		Shape[] shapes = new Shape[10];
		generateShapes(shapes);
		modifySize(shapes);
		sortByArea(shapes);
	}
	
	/**generateShapes method is used to initialize array of Shapes
	 * with random Circle, Square, Triangle type objects.
	 * @author	kulishov
	 * @param	shapes	Array of Shape objects. */
	private static void generateShapes(Shape[] shapes){
		for (int i = 0; i < shapes.length; i++){
			Random rand = new Random();
			switch (rand.nextInt(3)){
			case 0:
				shapes[i] = new Circle();
				break;
			case 1:
				shapes[i] = new Square();
				break;
			case 2:
				shapes[i] = new Triangle();
				break;
			default:
				System.out.printf("Unexpected situation at array element %s.%n", i);
				break;
			}
		}
	}
	
	/**modifySize method is used to modify size field 
	 * for every Shape object in the incoming array.
	 * @author	kulishov
	 * @param	shapes	Array of Shape objects. */
	private static void modifySize(Shape[] shapes){
		for (int i = 0; i < shapes.length; i++){
			Random rand = new Random();
			shapes[i].changeSize(rand.nextFloat());
		}
	}
	
	/**sortByArea method is used to sort objects
	 * in the incoming Shape array by the area value.
	 * @author	kulishov
	 * @param	shapes	Array of Shape objects. */
	private static void sortByArea(Shape[] shapes){
		for (int i = 0; i < shapes.length; i++){
			for (int j = i + 1; j < shapes.length; j++){
				Shape temp;
				if (shapes[i].area() < shapes[j].area()){
					temp = shapes[i];
					shapes[i] = shapes[j];
					shapes[j] = temp;
				}
			}
		}
	}
}
