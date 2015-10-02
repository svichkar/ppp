package com.nixsolutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Start {

    public static void main(String[] args) {
	// TODO Auto-generated method stub
	CollectionImpl coll = new CollectionImpl() {
	};
	Object a = "asdasd";
	Object b = "asdsasd";
	coll.add(a);
	coll.add(a);
	coll.add(a);
	coll.add(a);
	coll.add(a);
	coll.add(a);
	coll.add(a);

	boolean cont = coll.contains(b);

	List<String> list = new ArrayList<>(Arrays.asList("xyz", "abc", "1", "2"));
	cont = coll.containsAll(list);
	coll.addAll(list);
	cont = coll.containsAll(list);
	
	cont = coll.add(b);
	cont = coll.remove(b);
	cont = coll.removeAll(list);
	cont = coll.isEmpty();
	
	int size = coll.size();
	
	Iterator iter = coll.iterator();
	while(iter.hasNext()){
	    System.out.println("bla bla");
	    
	 Object temp = iter.next();    
	}
	cont = coll.retainAll(list);
	
	coll.clear();
	
	
	}	
	
    }


