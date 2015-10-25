package com.nixsolutions;

public class CollectionsProcessing {

	public static void main(String[] args) {
		CustomCollection<String> cCol = new CustomCollection<>();
		boolean temp = cCol.add("ss");
		temp = cCol.add("testvalue2");
		temp = cCol.add("test3");
		temp = cCol.remove("ss");
		temp = cCol.addAll(java.util.Arrays.asList(new String[]{"ss", "testvalue2"}));
		temp = cCol.removeAll(java.util.Arrays.asList(new String[]{"ss", "testvalue2"}));
		temp = cCol.add("ss");
		temp = cCol.contains("testvalue2");
		temp = cCol.containsAll(java.util.Arrays.asList(new String[]{"ss", "testvalue2"}));
		temp = cCol.retainAll(java.util.Arrays.asList(new String[]{"ss", "testvalue2"}));
		String tempStr = cCol.get(0);
		cCol.set(0, "SetterValue");
	}
}
