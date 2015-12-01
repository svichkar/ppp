package collect.com;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyCollections<Integer> cfirst = new MyCollections<>();
		cfirst.add(1);

		// ArrayList<Integer> csecond = new ArrayList<>();

		MyCollections<Integer> csecond = new MyCollections<>();

		csecond.add(10);
		csecond.add(20);
		cfirst.addAll(csecond);
		System.out.println(cfirst.size());

		cfirst.remove(10);
		System.out.println(cfirst.size());

		cfirst.retainAll(csecond);
		System.out.println(cfirst.size());

		MyCollections<Integer> c = new MyCollections<>();

		// Check toArray
		Object arr[] = c.toArray();
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		c.add(20);
		c.add(50);
		c.add(80);
		c.remove(10);
		c.add(20);
		c.add(560);
		c.remove(560);
		c.add(70);

		arr = c.toArray();
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}

	}

}
