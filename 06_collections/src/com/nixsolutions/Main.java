package com.nixsolutions;

public class Main {

    public static void main(String[] args) {
        try{
            MyCollection<String> strCollection1 = new MyCollection<String>();
            strCollection1.add("zero");
            strCollection1.add("one");
            strCollection1.add("two");
            strCollection1.add("five");
            strCollection1.add("three");
            System.out.printf("Print collection 1:\n");
            printCollectionElements(strCollection1);

            System.out.println();
            MyCollection<String> strCollection2 = new MyCollection<String>();
            strCollection2.add("four");
            strCollection2.add("five");
            strCollection2.add("six");
            System.out.printf("Print collection 2:\n");
            printCollectionElements(strCollection2);

            System.out.println();
            strCollection1.addAll(strCollection2);
            System.out.printf("Add second collection to the first:\n");
            printCollectionElements(strCollection1);

            System.out.println();
            strCollection1.remove("zero");
            System.out.printf("Remove 'zero' element:\n");
            printCollectionElements(strCollection1);


            System.out.println();
            MyCollection removeColl = new MyCollection();
            removeColl.add("five");
            removeColl.add("blabla");
            strCollection1.removeAll(strCollection2);
            System.out.printf("Remove collection removeColl from current:\n");
            printCollectionElements(strCollection1);

        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }

    }

    public static <T> void printCollectionElements(MyCollection<T> collection){
        for (int i = 0; i < collection.size(); i++){
            System.out.println(collection.toArray()[i]);
        }
    }
}
