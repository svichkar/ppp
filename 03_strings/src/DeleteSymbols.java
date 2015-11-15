/**
 * Created by konstantin on 15.11.2015.
 */
public class DeleteSymbols {

    public static void main(String args[]) {

        String testString = "Do you have a pen, John O'Conar Junior?";

        //delete 3 symbols from the start of string (as default)
        System.out.println(deleteSymbols(testString, "o", 3));

        //delete 3 symbols from the start of string - isLeftSide = true
        System.out.println(deleteSymbols(testString, "o", 3, true));

        //delete 2 symbols from the end of string - isLeftSide = false
        System.out.println(deleteSymbols(testString, "o", 2, false));
    }

    private static String deleteSymbols(String s, String symbol, int count) {

        return deleteSymbols(s, symbol, count, true);
    }

    private static String deleteSymbols(String s, String symbol, int count, boolean isLeftSide) {

        StringBuilder result = new StringBuilder(s);

        if (isLeftSide == true) {
            for (int i = 0; i < count; i++) {
                int index = result.indexOf(symbol);
                if (index != -1)
                    result.deleteCharAt(index);
                else
                    break;
            }
        } else {
            for (int i = 0; i < count; i++) {
                int lastIndex = result.lastIndexOf(symbol);
                if (lastIndex != -1)
                    result.deleteCharAt(lastIndex);
                else
                    break;
            }
        }

        return result.toString();
    }
}
