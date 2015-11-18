package com.nixsolutions;


/**
 * Created by Rybkinrolla on 15.11.2015.
 */
public class CustomCollectionTest {
    public static void main(String[] args) {
        CustomCollection<String> cc = new CustomCollection<>();
        CustomCollection<String> cc2 = new CustomCollection<>();
        cc.add("Tree");
        cc.add("Tree1");
        cc.add("Tree2");
        cc.add("Tree3");
        cc.add("Tree4");
        cc.add("NewTree1");
        cc.add("NewTree2");
        cc.add("NewTree3");
        cc.add("NewTree4");
        cc2.add("NewTree3");
        cc2.add("Tree2");
        cc2.add("Tree4");
        cc2.add("Tree5");
        cc.remove("Tree");
        cc.retainAll(cc2);
        CustomCollection<String> cc3 = new CustomCollection();
        cc3.add("Tree2");
        cc.removeAll(cc3);
        String[] sss = new String[cc.size()];
        cc.toArray(sss);
        for (int i = 0; i < sss.length; i++) {
            System.out.println(sss[i]);
        }
    }
}
