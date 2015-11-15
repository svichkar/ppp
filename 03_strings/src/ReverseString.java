/**
 * Created by konstantin on 11.11.2015.
 */
public class ReverseString {

    public static void main(String args[]) {

        String string = "My test string.";
        int N = string.length();
        String reverseString = "";

        for (int i = N - 1; i >= 0; i--) {
            reverseString += string.charAt(i);
        }

        System.out.println(String.format("Initial string: \"%s\". %nString after reversing: \"%s\". ", string, reverseString));
    }
}
