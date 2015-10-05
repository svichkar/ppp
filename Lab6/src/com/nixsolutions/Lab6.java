package com.nixsolutions;

import java.util.*;

public class Lab6 {

	public static void main(String[] args) {
		CustomCollection customCollection = new CustomCollection();
		customCollection.add("One");
		customCollection.add("Two");
		customCollection.add("Three");
		System.out.print("Elements in collection: ");
		for (Object el : customCollection){
			System.out.print(el + "; ");
		}
		System.out.println();
		CustomCollection newCustomCollection = new CustomCollection();
		newCustomCollection.addAll(customCollection);
		newCustomCollection.addAll(customCollection);
		System.out.print("Elements in new collection: ");
		for (Object el : newCustomCollection){
			System.out.print(el + "; ");
		}
		System.out.println();
		System.out.println("Is new collection empty: " + newCustomCollection.isEmpty());
		System.out.println("Size of new collection: " + newCustomCollection.size());
		customCollection.remove("One");
		System.out.print("Elements in new collection after remove: ");
		for (Object el : customCollection){
			System.out.print(el + "; ");
		}
		System.out.println();
		customCollection.add("One");
		List<String> listToRemove = new ArrayList<>(Arrays.asList("One", "Three"));
		customCollection.removeAll(listToRemove);
		System.out.print("Elements in collection after removeAll: ");
		for (Object el : customCollection){
			System.out.print(el + "; ");
		}
		System.out.println();
		newCustomCollection.retainAll(listToRemove);
		Iterator it = newCustomCollection.iterator();
		System.out.print("Elements in new collection after retainAll: ");
		while(it.hasNext()){
			System.out.print(it.next() + "; ");
		}
		System.out.println();
		customCollection.clear();
		System.out.println("Size of collection after clear: " + customCollection.size());
		System.out.println("Size of new collection: " + newCustomCollection.size());
	}

}
