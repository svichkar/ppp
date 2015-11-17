package com.nixsolutions;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		CollectionImplementation<String> collection = new CollectionImplementation<String>();
		collection.add("text");
		collection.add("remove");
		collection.add("new");
		collection.add("ololo");
		collection.add("new");
		collection.add("qwerty");
		collection.add("qwerty1234");
		collection.remove("remove");
		int size = collection.size();
		boolean res = collection.contains("new");
		Object[] newCollectionArrObj = collection.toArray();
		String[] newCollectionArrStr = collection.toArray(new String[collection.size()]);
		collection.clear();
		collection.add("text");
		collection.add("remove");
		collection.add("new");
		collection.add("ololo");
		collection.add("new");
		collection.add("qwerty");
		collection.add("qwerty1234");

		List<String> list = new ArrayList<>();
		list.add("text");
		list.add("new");

		res = collection.containsAll(list);
		res = collection.removeAll(list);

		list.add("qwerty1234");
		list.add("z");
		list.add("12qwerty1234");
		list.add("zoo");

		collection.add("z");
		collection.add("z");
		collection.add("z");
		collection.add("y");
		collection.add("y123");
		collection.add("zoooo123");

		collection.retainAll(list);
	}

}
