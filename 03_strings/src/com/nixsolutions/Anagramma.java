import java.util.Arrays;

/**
 * Created by w700 on 30.01.2016.
 */
public class Anagramma {
    /**
     *
     * @param firstString
     * @param secondString
     * @return
     */
    public static boolean anagramma(String firstString, String secondString){
        char[] firstStringArray = firstString.replaceAll(" ", "").toCharArray();
        char[] secondStringArray = secondString.replaceAll(" ","").toCharArray();
        Arrays.sort(firstStringArray);
        Arrays.sort(secondStringArray);
        return Arrays.equals(firstStringArray,secondStringArray);
    }
}
