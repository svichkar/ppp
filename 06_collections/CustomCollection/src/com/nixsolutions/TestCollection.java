/**
 * 
 */
package com.nixsolutions;

/**
 * @author mixeyes
 *
 */
public class TestCollection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Enter some Symbols");
		CustomCollection<String> exampleColl = new CustomCollection<>();
		exampleColl.add("dfhdhg");
		exampleColl.add("dfhdhg");
		exampleColl.add("dfhdhg");
		CustomCollection<String> exampleColl2 = new CustomCollection<>();
		exampleColl2.add("gfjfjhnc");
		exampleColl2.add("gfjfjhnc");
		exampleColl2.add("gfjfjhnc");
		exampleColl.addAll(exampleColl2);
		exampleColl.contains("gfjfjhnc");
		exampleColl.containsAll(exampleColl2);
		exampleColl.remove("gfjfjhnc");
		exampleColl.add("gfjfjhnc");
		exampleColl.removeAll(exampleColl2);
		exampleColl.addAll(exampleColl2);
		exampleColl.retainAll(exampleColl2);
		
	}

}
