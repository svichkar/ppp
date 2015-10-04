package com.nixsolutions;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

/**
 * Class implemented for validating my collection
 * 
 * @author maxb
 * 
 */
public class TestMyCollection {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] testArray = new String[] { "a", "b", "c", "d", "e", "f", "g" };
		Integer[] testArray1 = new Integer[] { 1, 5 };
		Car[] testArray2 = new Car[2];
		testArray2[0] = new Car("BMW");
		testArray2[0].setPower(200);
		testArray2[1] = new Car("VW");
		testArray2[1].setPower(180);
		// /array for subtracting
		Car vw = new Car("VW");
		vw.setPower(180);
		Object[] subArray = new Object[] { "c", "d", 1, vw };
		Object[] subArray1 = new Object[] { "c", "d", 1, "bla" };

		// init collection
		MyCollection<Object> testC = new MyCollection<Object>();
		testC.addAll(Arrays.asList(testArray));
		testC.addAll(Arrays.asList(testArray1));
		testC.addAll(Arrays.asList(testArray2));

		// //check contains
		Random rnd = new Random();
		System.out.printf("My collection contains \"%1s\" - %2s%n",
				testArray[rnd.nextInt(testArray.length)],
				testC.contains(testArray[rnd.nextInt(testArray.length)]));
		System.out.printf("My collection contains \"%1s\" - %2s%n", "bla",
				testC.contains("bla"));
		System.out.println();
		// //check remove
		System.out.printf("Collection before removing %s%n",
				testC.toString(";"));
		boolean wasItemdRemoved = testC.remove(testArray[rnd
				.nextInt(testArray.length)]);
		System.out.printf("Was any items removed? - %s%n", wasItemdRemoved);
		System.out
				.printf("Collection after removing %s%n", testC.toString(";"));
		System.out.println();
		// /check removeall
		testC = new MyCollection<Object>();
		testC.addAll(Arrays.asList(testArray));
		testC.addAll(Arrays.asList(testArray1));
		testC.addAll(Arrays.asList(testArray2));
		System.out.printf("Collection before removing all %s%n",
				testC.toString(";"));
		testC.removeAll(Arrays.asList(subArray));
		System.out.printf("Collection after removing all %s%n",
				testC.toString(";"));
		System.out.println();
		// /check retainall
		testC = new MyCollection<Object>();
		testC.addAll(Arrays.asList(testArray));
		testC.addAll(Arrays.asList(testArray1));
		testC.addAll(Arrays.asList(testArray2));
		System.out.printf("Collection before remain all %s%n",
				testC.toString(";"));
		testC.retainAll(Arrays.asList(subArray));
		System.out.printf("Collection after remain all %s%n",
				testC.toString(";"));
		System.out.println();
		// check clear
		System.out.printf("Collection before clear %s%n", testC.toString(";"));
		testC.clear();
		System.out.printf("Collection after clear %s%n", testC.toString(";"));
		System.out.println();
		// check is Empty
		if (testC.isEmpty()) {
			System.out.println("Collection is empty. We will refeed it");
			testC = new MyCollection<Object>();
			testC.addAll(Arrays.asList(testArray));
			testC.addAll(Arrays.asList(testArray1));
			testC.addAll(Arrays.asList(testArray2));
			System.out.printf("The newest content of collection - %s%n",
					testC.toString(";"));
		} else {
			System.out
					.printf("Collection is still not empty.%nThe following items are still remaining %s%n",
							testC.toString(";"));
		}
		System.out.println();
		// check that collection contains all items from other collection
		System.out.printf("Items from other collection - %1s;%2s;%3s;%4s %n",
				subArray1[0], subArray1[1], subArray1[2], subArray1[3]);
		System.out.printf("All items are present - %s%n",
				testC.containsAll(Arrays.asList(subArray1)));
		System.out.println();
		// /check iterator
		System.out
				.printf("Cheking iterator by passing via all items of collection - %s%n",
						testC.toString(" "));
		Iterator<Object> myIterator = testC.iterator();
		int counter = 0;
		while (myIterator.hasNext()) {
			Object one = myIterator.next();
			System.out.printf("Item %1s has value %2s%n", counter,
					one.toString());
			counter++;
		}
		System.out.println();

	}

}
