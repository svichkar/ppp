package com.nixsolutions.collections;

import java.awt.List;
import java.util.Iterator;

public class MainCollections {

	public static void main(String[] args) {

		CustomCollection<String> strCollection = new CustomCollection<>();
		for (int i = 0; i < 5; i++) {
			strCollection.add("fhsldhgls suka plohovataja pogoda " + i);
		}

		CustomCollection<String> intCollection = new CustomCollection<>(4);
		for (int i = 0; i < 3; i++) {
			intCollection.add("Someth " + i);
		}
		strCollection.addAll(intCollection);
		strCollection.addAll(strCollection);
		/*
		 * Object[] array = intCollection.toArray(); String[] y =
		 * intCollection.toArray(new String[2]);
		 */

		if (strCollection.containsAll(intCollection)) {
			System.out.println("All of them are here");
		} else {
			System.out.println("Not all of them are here");
		}
		intCollection.add("Sna,lsfkh");
		intCollection.add("Someth 2");
		if (strCollection.retainAll(intCollection)) {
			System.out.println("All of them were removed");
		} else {
			System.out.println("Not all of them are here");
		}
		
		if (strCollection.containsAll(intCollection)) {
			System.out.println("All of them are here");
		} else {
			System.out.println("Not all of them are here");
		}
	  /*   Iterator iterator = strCollection.iterator();     
	      while (iterator.hasNext()){
	         System.out.print(iterator.next() + " ");  
	      }
	      System.out.println();*/
	}

}
