package collect.com;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyCollections<Integer> cfirst = new MyCollections<>();
		cfirst.add(1);
		
		//ArrayList<Integer> csecond = new ArrayList<>();
		
		MyCollections<Integer> csecond = new MyCollections<>();
		
		csecond.add(10);
		csecond.add(20);
		cfirst.addAll(csecond);
		System.out.println(cfirst.size());
		
		cfirst.remove(10);
		System.out.println(cfirst.size());
		
		cfirst.retainAll(csecond);
		System.out.println(cfirst.size());
		
		
		
		
		
		
		

	}

}
