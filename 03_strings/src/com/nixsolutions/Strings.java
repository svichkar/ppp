import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sobolenko on 1/27/2016.
 */
public class Strings {
    public static void concatStrings(List<String> inputString)
    {
        String result = "";
        for (String x: inputString){
            result += x+" ";
        }
        System.out.println(result);
        result = "";

        for (String x: inputString){
            result = result.concat(x).concat(" ");
        }
        System.out.println(result);
        result = "";

        for (String x: inputString){
            StringBuilder sb = new StringBuilder();
            result = result.concat(sb.append(x).append(" ").toString());
        }
        System.out.println(result);
    }
    public static boolean anagramma(String firstString, String secondString){
        char[] firstStringArray = firstString.replaceAll(" ", "").toCharArray();
        char[] secondStringArray = secondString.replaceAll(" ","").toCharArray();
        Arrays.sort(firstStringArray);
        Arrays.sort(secondStringArray);
        return Arrays.equals(firstStringArray,secondStringArray);
    }
    public static String initials(String inputString){
        char[] stringArray = inputString.toCharArray();
        String result ="";
        for (Character ch: stringArray){
            if (ch.toString().equals(ch.toString().toUpperCase())&&!ch.equals(' ')){
                result += ch;
            }
        }
        return result;
    }
    public static void main(String[] args){
        System.out.println(anagramma("полковник клоп", "полк клоповник"));
        System.out.println(initials("Полковник Клоп"));
        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("Java");
        listOfStrings.add("forever");
        concatStrings(listOfStrings);
    }
}
