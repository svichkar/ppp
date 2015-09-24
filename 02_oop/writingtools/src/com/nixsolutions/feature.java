package com.nixsolutions;

import java.nio.channels.SelectableChannel;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * main class
 */
public class feature {

	/**
	 * main method
	 */
	public static void main(String[] args) throws Exception {
		ArrayList<WritingObjects> objects = generateObjectsCollection();
		System.out.print("Generated collection of the objects:\n");
		for (WritingObjects obj : objects)
			System.out.print(obj.getClass().getSimpleName() + "\n");
		System.out.print("Preparation collection of the objects:\n");
		for (WritingObjects obj : objects)
			((WritingTools) obj).preparationForWriting();

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			System.out.print(i + " iteration\n");
			for (WritingObjects obj : objects) {
				System.out.print("Take object " + obj.getClass().getSimpleName() + "\n");
				Random r = new Random(System.nanoTime());
				int randomCount = r.nextInt(5) + 3;
				for (int j = 0; j < randomCount; j++)
					builder.insert(builder.length(), ((WritingTools) obj).write());
				System.out.print("object " + obj.getClass().getSimpleName() + " write next characters:\n");
				System.out.print(builder.toString() + "\n");
				System.out.print("Erase last symbol:\n");
				builder = ((WritingTools) obj).erase(builder);
				System.out.print(
						"object " + obj.getClass().getSimpleName() + " erase last character, new StringBuilder:\n");
				System.out.print(builder.toString() + "\n");
			}
		}
		System.out.print("\n\n\n\n\n\n\n\n\n\nObject before sorting by the rest of writing means:\n");
		for (WritingObjects obj : objects)
			System.out.print(
					obj.getClass().getSimpleName() + " with rest of writing means: " + obj.restOfWritingMeans + "\n");
		ArrayList<WritingObjects> objectsSort = sort(objects);
		System.out.print("\n\n\n\n\n\n\n\n\n\nObject after sorting by the rest of writing means:\n");
		for (WritingObjects obj : objectsSort)
			System.out.print(
					obj.getClass().getSimpleName() + " with rest of writing means: " + obj.restOfWritingMeans + "\n");

	}

	/**
	 * create writing tool by user choice
	 * 
	 * @param userChoice
	 *            user choice
	 */
	private static WritingObjects selectWritingTool(int userChoice) {
		switch (userChoice) {
		case 1:
			return new PenObject();
		case 2:
			return new PencilObject();
		case 3:
			return new FeltTipPen();
		default:
			System.out.print("Unexpected tool");
			return null;
		}
	}

	/**
	 * generate collection of the random Writing Objects
	 */
	private static ArrayList<WritingObjects> generateObjectsCollection() {
		int[] randomArray = new int[10];
		Random r = new Random(System.nanoTime());
		for (int i = 0; i < 10; i++) {
			randomArray[i] = r.nextInt(3) + 1;
		}
		ArrayList<WritingObjects> objects = new ArrayList();
		for (int i = 0; i < randomArray.length; i++)
			objects.add(selectWritingTool(randomArray[i]));

		return objects;
	}

	/**
	 * bubble sort by rest of writing means
	 * @param start collection before sorting*/
	private static ArrayList<WritingObjects> sort(ArrayList<WritingObjects> start) {
		ArrayList<WritingObjects> objArray = (ArrayList<WritingObjects>) start.clone();
		for (int i = 0; i < objArray.size(); i++) {
			for (int j = 0; j < objArray.size() - i - 1; j++) {
				if (objArray.get(j).restOfWritingMeans > objArray.get(j + 1).restOfWritingMeans) {
					// save max value to t variable
					WritingObjects t = objArray.get(j);
					// exchange of places
					objArray.set(j, objArray.get(j + 1));
					objArray.set(j + 1, t);
				}
			}
		}

		return objArray;
	}
}
