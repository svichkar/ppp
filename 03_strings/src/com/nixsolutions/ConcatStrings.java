import java.util.List;

/**
 * Created by w700 on 30.01.2016.
 */
public class ConcatStrings {
    /**
     *
     * @param inputString
     */
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
}
