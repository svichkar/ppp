import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by kozlovskij on 12/2/2015.
 */


public class StringBuilderTest {
    private static String INPUTSTRING = "testString!";
    private static char ACHAR = '!';
    private StringBuilder stringBuilder;
    private String outString;
    private int length;
    private int position;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void createNewInstanceOfStringBuilder() {
        stringBuilder = new StringBuilder();
        stringBuilder.append(INPUTSTRING);
    }

    @After
    public void tearDown () {
        stringBuilder = null;
        outString = "";
        length = 0;
        position = 0;
    }

    @Test
    public void shouldReturnStringEqualInputString () {
        //when
        outString = stringBuilder.toString();
        //then
        Assert.assertEquals(INPUTSTRING, outString);
    }

    @Test
    public void shouldReturnLengthEqualInputStringLength () {
        //when
        length = stringBuilder.length();
        //then
        Assert.assertEquals(INPUTSTRING.length(), length);
    }

    @Test
    public void shouldReturnConcatenationOfStrings() {
        //given
        stringBuilder.append(INPUTSTRING);
        //when
        outString= stringBuilder.toString();
        //then
        Assert.assertEquals(INPUTSTRING + INPUTSTRING, outString);

    }

    @Test
    public void shouldInsertStringAtSelectedPositionWithinLength () {
        //given
        position = (int) (Math.random() * stringBuilder.length());
        //when
        stringBuilder.insert(position, ACHAR);
        //then
        Assert.assertEquals(ACHAR, stringBuilder.charAt(position));
    }

    @Test
    public void shouldReturnExceptionByInsertStringAtSelectedPositionBeyondLength () throws ArrayIndexOutOfBoundsException {
        //given
        position = (int) (Math.random() * stringBuilder.length()) + stringBuilder.length() + 1;
        //when
        exception.expect(ArrayIndexOutOfBoundsException.class);
        stringBuilder.insert(position, ACHAR);
    }

    @Test
    public  void shouldReplaceCharactersSequenceWithCharactersInSpecifiedString () {
        //given
        position = (int) (Math.random() * stringBuilder.length());
        //when
        stringBuilder.replace(position, position + 1, String.valueOf(ACHAR));
        //then
        Assert.assertEquals(ACHAR, stringBuilder.charAt(position));
    }

    @Test
    public void shouldReverseInputString () {
        //given
        for (int i = INPUTSTRING.length() - 1; i >= 0 ; i--) {
            outString += INPUTSTRING.charAt(i);
        }
        //when
        stringBuilder.reverse();
        //then
        Assert.assertEquals(outString, stringBuilder.toString());
    }

    @Test
    public void shouldDeleteCharInSpecifiedPosition () {
        //given
        position = (int) (Math.random() * stringBuilder.length());
        for (int i = 0; i <INPUTSTRING.length() ; i++) {
            if (i != position) {
                outString += INPUTSTRING.charAt(i);
            }
        }
        //when
        stringBuilder.deleteCharAt(position);
        //then
        Assert.assertEquals(outString, stringBuilder.toString());
    }
}
