package com.nixsolutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class CollectionMain {

	public static void main(String[] args) {
		boolean check;
		MyCollection collection = new MyCollection();
		Object o = "test";
		Object p = "test2";
		Object q = "abc";
		Object s = "f";
		Object t = "h";
		Object k=null;
		collection.add(o);
		collection.add(p);
		collection.add(q);
		collection.add(s);
		collection.add(p);
		check = collection.contains(q);
		check = collection.remove(p);
		ArrayList<String> list = new ArrayList<>(Arrays.asList("new", "list"));
		check = collection.addAll(list);
		check = collection.removeAll(list);		
		collection.add(t);
		check = collection.isEmpty();
		collection.clear();
		check = collection.isEmpty();
		collection.addAll(list);

		Iterator it = collection.iterator();
		while (it.hasNext())
			check = collection.contains(it.next());

		Object[] test = collection.toArray();
		String[] str = new String[collection.size()];
		Object[] testArray = collection.toArray(str);

	}

}