package com.nixsolutions;

import java.util.Iterator;

public class CollectionTask {
    public static void main(String[] args) {
	CustCollection testCollection = new CustCollection();
	System.out.println("Size of new collection: " + testCollection.size());
	System.out.println("Is collection empty: " + testCollection.isEmpty());
	testCollection.add("Hello");
	System.out.println("New size (one element is added): " + testCollection.size());
	System.out.println("Is collection empty: " + testCollection.isEmpty());
	CustCollection temp = new CustCollection();
	temp.add("World!");
	temp.add("London");
	temp.add("is the");
	temp.add("capital");
	temp.add("of");
	temp.add("Great Britain");
	testCollection.addAll(temp);
	Iterator iter = testCollection.iterator();
	while (iter.hasNext()) {
	    System.out.println(iter.next());
	}
	System.out.println("New size (several elements are added): " + testCollection.size());
	System.out.println("Element \"World!\" is contained in collection: " + testCollection.contains("World!"));
	System.out.println("Element \"abc!\" is contained in collection: " + testCollection.contains("abc!"));
	temp = new CustCollection();
	temp.add("World!");
	temp.add("of");
	temp.add("is the");
	System.out.println("Elements \"World!\", \"of\", \"is the\" are contained in collection: "
		+ testCollection.containsAll(temp));
	temp.add("abc");
	System.out.println("Elements \"World!\", \"of\", \"is the\", \"abc\" are contained in collection: "
		+ testCollection.containsAll(temp));
	System.out.println("Remove element \"World!\". Is removed: " + testCollection.remove("World!"));
	System.out.println("Element \"World!\" is contained in collection: " + testCollection.contains("World!"));
	temp = new CustCollection();
	temp.add("of");
	temp.add("is the");
	System.out.println("Delete elements \"of\", \"is the\" from collection: " + testCollection.removeAll(temp));
	System.out.println(
		"Elements \"of\", \"is the\" are contained in collection: " + testCollection.containsAll(temp));
	Object[] resArray = testCollection.toArray();
	resArray = testCollection.toArray(new Object[] { "a", "b" });
	resArray = testCollection.toArray(new Object[] { "a", "b", "a", "b", "a", "b", "a", "b", "a", "b", "a", "b",
		"a", "b", "a", "b", "a", "b" });
	temp.add("London");
	temp.add("capital");
	temp.add("Great Britain");
	System.out.println("Retain elements in collection: " + testCollection.retainAll(temp));
	iter = testCollection.iterator();
	while (iter.hasNext()) {
	    System.out.println(iter.next());
	}
	System.out.println("Retain elements in collection: " + testCollection.retainAll(temp));
	System.out.println("Clear collection");
	testCollection.clear();
	System.out.println("Is collection empty: " + testCollection.isEmpty());
    }
}
