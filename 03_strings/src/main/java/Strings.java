import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sobolenko on 1/27/2016.
 */
public class Strings {
    public static boolean anagramma(String firstString, String secondString){
        char[] firstStringArray = firstString.replaceAll(" ", "").toCharArray();
        char[] secondStringArray = secondString.replaceAll(" ","").toCharArray();
        Arrays.sort(firstStringArray);
        Arrays.sort(secondStringArray);
        return Arrays.equals(firstStringArray,secondStringArray);
    }
    public static String deleteLetter(String inputString,char letter,int amount){
        char[] stringArray = inputString.toCharArray();
        String result="";
        for (Character ch: stringArray){
            if (!ch.equals(letter)&& amount>0){
                result += ch;
            }
            if (amount==0){
                result += ch;
            }
            if (ch.equals(letter)&& amount>0){amount--;}
        }
        return result;
    }
    public static String reverseOrder(String inputString) throws UnsupportedEncodingException {
        byte[] stringArray = inputString.getBytes();
        byte[] result = new byte[stringArray.length];
        for (int i= stringArray.length;i>0;i--){
            result[stringArray.length-i] = stringArray[i-1];
        }
        return new String(result);
    }
    public static String initials(String inputString){
        char[] stringArray = inputString.toCharArray();
        String result ="";
        for(Character ch: stringArray){
            if(ch.toString().equals(ch.toString().toUpperCase())&&!ch.equals(' ')){
                result += ch;
            }
        }
        return result;
    }
    public static void main(String[] args){
        System.out.println(anagramma("полковник клоп", "полк клоповник"));
        System.out.println(deleteLetter("полковник клоп", 'о', 3));
        try {
            System.out.println(reverseOrder("полковник клоп"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(initials("Полковник Клоп"));

    }
}
