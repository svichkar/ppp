package com.nixsolutions.collections;

import java.util.Iterator;
import java.util.Scanner;

public class MainCollections {

	public static void main(String[] args) {

		CustomCollection<String> strCollection1 = new CustomCollection<>();
		CustomCollection<Integer> intCollection1 = new CustomCollection<Integer>();

		for (int i = 0; i < 5; i++) {
			strCollection1.add("Collection's one stuff #" + i);
			intCollection1.add(i);
		}

		CustomCollection<String> strCollection2 = new CustomCollection<>(4);
		CustomCollection<Integer> intCollection2 = new CustomCollection<Integer>(4);
		for (int i = 0; i < 3; i++) {
			strCollection2.add("Second Collection's stuff " + i * 100);
			intCollection2.add(i + 43);
		}
int i=0;
		for (String str:strCollection1){
			strCollection1.remove(str);
			System.out.println("Ieration " + i+1);
			i++;
		}
		printCollection(strCollection1);
		
		
		
		strCollection1.add("Just added string");
		intCollection1.add(1002);

		
		
		printCollection(strCollection1);
		printCollection(intCollection1);

		strCollection1.addAll(strCollection2);
		intCollection1.addAll(intCollection2);

		printCollection(strCollection1);
		printCollection(intCollection1);

		strCollection1.removeAll(strCollection2);
		intCollection1.removeAll(intCollection2);

		strCollection1.remove("Just added string");
		intCollection1.remove(1);

		printCollection(strCollection1);
		printCollection(intCollection1);

		strCollection1.addAll(strCollection2);
		intCollection1.addAll(intCollection2);

		printCollection(strCollection1);
		printCollection(intCollection1);

		strCollection1.retainAll(strCollection2);
		intCollection1.retainAll(intCollection2);

		printCollection(strCollection1);
		printCollection(intCollection1);

		strCollection1.removeAll(strCollection2);
		intCollection1.removeAll(intCollection2);
		System.out.println("Before removing 2nd collections");

		printCollection(strCollection1);
		printCollection(intCollection1);
				
	}

	private static void printCollection(CustomCollection col) {
		Iterator iterator = col.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " \n");
		}
		System.out.println();
	}
	
}
