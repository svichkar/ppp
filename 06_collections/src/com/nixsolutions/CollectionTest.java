package com.nixsolutions;

/**
 * Created by sobolenko on 2/1/2016.
 */
public class CollectionTest {
    public static void main(String args[]) {
        NewCollections<String> containsAllTest = new NewCollections<String>();
        containsAllTest.add("9");
        containsAllTest.add("rt");
        containsAllTest.add("l");
        NewCollections<String> retainTest = new NewCollections<String>();
        retainTest.add(1);
        retainTest.add("f");
        retainTest.add("l");
        retainTest.add("q");
        NewCollections<String> addTest = new NewCollections<String>();
        addTest.add("f");
        addTest.add(9);
        addTest.add("rt");
        addTest.add("q");
        addTest.add("k");
        addTest.add("l");
        NewCollections<String> remTest = new NewCollections<String>();
        remTest.add("sg");
        remTest.add("k");
        NewCollections<String> collections = new NewCollections<String>();
        System.out.println(collections.add(1));
        System.out.println(collections.add(5));
        System.out.println(collections.add(4));
        System.out.println(collections.add("sg"));
        System.out.println(collections.size());
        System.out.println(collections.isEmpty());
        System.out.println(collections.contains("sg"));
        System.out.println(collections.contains("sq"));
        System.out.println(collections.remove(5));
        System.out.println("-------------------------");
        System.out.println(collections.addAll(addTest));
        System.out.println("-------------------------");
        for (Object x : collections.toArray()) {
            System.out.println(x.toString());
        }
        System.out.println("-------------------------");
        System.out.println(collections.size);
        System.out.println(collections.removeAll(remTest));
        System.out.println(collections.retainAll(retainTest));
        System.out.println(collections.containsAll(containsAllTest));
        System.out.println(collections.iterator().hasNext());
        System.out.println(collections.iterator().next());
        System.out.println(collections.iterator().next());
        collections.iterator().remove();
        System.out.println(collections.iterator().next());
        System.out.println(collections.iterator().next());
        System.out.println(collections.iterator().hasNext());
        collections.clear();
        System.out.println(collections.isEmpty());
    }
}
