/**
 * Created by w700 on 30.01.2016.
 */
public class AB {
    /**
     *
     * @param inputString
     * @return
     */
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
}
