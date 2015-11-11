/**
 * Created by konstantin on 11.11.2015.
 */
public class ReverseString {

    public static void main(String args[]) {

        String string = "Argentinamanitnegra";
        char[] signs = string.toCharArray();
        int N = signs.length;

        char temp;
        for (int i = 0; i < N/2; i++) {
                temp = signs[i];
                signs[i] = signs[N - 1 - i];
                signs[N - 1 - i] = temp;
        }

        String reverseString = String.copyValueOf(signs);
        System.out.println(String.format("Initial string: \"%s\". %nString after reversing: \"%s\". ", string, reverseString));
    }
}
