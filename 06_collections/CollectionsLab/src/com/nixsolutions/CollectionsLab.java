package com.nixsolutions;

import java.util.ArrayList;
import java.util.Arrays;

public class CollectionsLab {

	public static void main(String[] args) {
		CustomCollection<String> c = new CustomCollection<String>(new String[] { "1", "2", "2", "3", "4" });
		System.out.println("Does collection contain \'9\'? " + c.contains("9"));
		System.out.println("Lets add \'9\' to the collection! " + c.add("9"));
		System.out.println("Does it contain \'9\' now? " + c.contains("9"));
		System.out.println("We\'d better remove it. " + c.remove("9"));
		System.out.println("And here we are: " + Arrays.toString(c.toArray()));		
		System.out.println("Is collection empty? Do not think so. " + c.isEmpty());
		c.clear();
		System.out.println("And now? " + c.isEmpty());
		ArrayList<String> l = new ArrayList<String>();
		l.add("0");
		l.add("1");
		l.add("4");
		System.out.printf("Does collection contain this list \'%s\'? %b%n", l.toString(), c.containsAll(l));
		System.out.println("Of course not, it\'s empty. Lets add the whole list to the collection. " + c.addAll(l));
		System.out.println("Yep, it's better now. " + c.containsAll(l));	
	}
}
