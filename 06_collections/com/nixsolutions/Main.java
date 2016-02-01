/**
 * Created by sobolenko on 2/1/2016.
 */
public class Main {
    public static  void main(String args[])
    {
        NewCollections <String> collections = new NewCollections<String>();
        System.out.println(collections.add(1));
        System.out.println(collections.add(5));
        System.out.println(collections.add(4));
        System.out.println(collections.add("sg"));
        System.out.println(collections.size());
        System.out.println(collections.isEmpty());
        System.out.println(collections.contains("sg"));
        System.out.println(collections.contains("sq"));
        System.out.println(collections.remove(5));
        //System.out.println(collections);
    }
}
