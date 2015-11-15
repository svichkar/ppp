import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by konstantin on 15.11.2015.
 */
public class WordToUniqueChars {

    public static void main(String args[]) {

        String testWord = "Antidisestablishmentarianism";

        System.out.println(getCharacters(testWord));
    }

    private static SortedSet<Character> getCharacters(String s) {

        SortedSet<Character> charsSet = new TreeSet<Character>();
        char[] allChars = s.toLowerCase().toCharArray();

        for (char element : allChars) {
            charsSet.add(Character.valueOf(element));
        }
        return charsSet;
    }
}
