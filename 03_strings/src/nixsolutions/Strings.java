package nixsolutions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sobolenko on 1/27/2016.
 */
public class Strings {

    public static void main(String[] args){
        System.out.println("Is anagramma: "+Anagramma.anagramma("полковник клоп", "полк клоповник"));
        System.out.println("Get initials: "+ Initials.initials("Полковник Клоп"));
        List<String> listOfStrings = new ArrayList<String>();
        System.out.println("Concatenate strings (3 different methods):");
        listOfStrings.add("Java");
        listOfStrings.add("forever");
        ConcatStrings.concatStrings(listOfStrings);
    }
}
