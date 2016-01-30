import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sobolenko on 1/27/2016.
 */
public class Strings {

    public static void main(String[] args){
        System.out.println(Anagramma.anagramma("полковник клоп", "полк клоповник"));
        System.out.println(AB.initials("Полковник Клоп"));
        List<String> listOfStrings = new ArrayList<String>();
        listOfStrings.add("Java");
        listOfStrings.add("forever");
        ConcatStrings.concatStrings(listOfStrings);
    }
}
