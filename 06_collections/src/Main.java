import java.util.*;

/**
 * Created by konstantin on 14.11.2015.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        Collection<Double> myCollection = new MyCollection<Double>();

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            myCollection.add(random.nextDouble());
        }

        Iterator iterator = myCollection.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        Collection<Double> addCollection = new MyCollection<Double>();
        for (int i = 0; i < 5; i++) {
            addCollection.add(random.nextDouble());
        }
        myCollection.add(new Double(12));

        myCollection.addAll(addCollection);

        myCollection.contains(new Double(12));
        myCollection.containsAll(addCollection);
        myCollection.remove(new Double(12));
        myCollection.retainAll(addCollection);
        myCollection.toArray();
        myCollection.removeAll(addCollection);
        myCollection.isEmpty();
    }
}
