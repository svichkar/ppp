package nixsolutions;

import java.util.Arrays;

/**
 * Created by w700 on 30.01.2016.
 */
public class Anagramma {
    /**
     *Check that input strings are anagram
     * @param firstString first input string
     * @param secondString second input string
     * @return return false or true (true if strings are anagram, false if not)
     */
    public static boolean anagramma(String firstString, String secondString){
        char[] firstStringArray = firstString.replaceAll(" ", "").toCharArray();
        char[] secondStringArray = secondString.replaceAll(" ","").toCharArray();
        Arrays.sort(firstStringArray);
        Arrays.sort(secondStringArray);
        return Arrays.equals(firstStringArray,secondStringArray);
    }
}
