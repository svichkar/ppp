import org.junit.*;
import org.junit.rules.ExpectedException;

/**
 * Created by kozlovskij on 12/2/2015.
 */


public class StringBuilderTest {

    private static StringBuilder BUILDER;
    private static String INPUTSTRING = "TestString!";
    private String testString = "someText";
    private char[] chars = {'a', 'b', 'c', 'd', 'e', 'f'};

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void createNewInstance() {
        BUILDER = new StringBuilder();
    }

    @Before
    public void setUp() {
        BUILDER.append(INPUTSTRING);
    }

    @After
    public void tearDown() {
        BUILDER.delete(0, BUILDER.length());
    }

    @Test
    public void shouldAppendStringToTail() {
        String temp = INPUTSTRING + testString;
        BUILDER.append(testString);
        Assert.assertEquals(temp, BUILDER.toString());
    }

    @Test
    public void shouldAppendCharSequenceToTail() {
        String temp = INPUTSTRING;
        for (int i = 0; i < chars.length; i++) {
            temp += chars[i];
        }
        BUILDER.append(chars);
        Assert.assertEquals(temp, BUILDER.toString());
    }

    @Test
    public void shouldInsertSpecifiedStringAtSpecifiedPosition() {
        int position = (int) (Math.random() * BUILDER.length());
        String temp = "";
        for (int i = 0; i < INPUTSTRING.length(); i++) {
            if (i != position) {
                temp += INPUTSTRING.charAt(i);
            } else {
                for (int j = 0; j < testString.length(); j++) {
                    temp += testString.charAt(j);
                }
                temp += INPUTSTRING.charAt(i);
            }
        }
        BUILDER.insert(position, testString);
        Assert.assertEquals(temp, BUILDER.toString());
    }

    @Test
    public void shouldReturnExceptionWhenInsertInPositionGreaterThanLenth() throws IndexOutOfBoundsException {
        int position = (int) (Math.random() * BUILDER.length()) + BUILDER.length() + 1;
        exception.expect(IndexOutOfBoundsException.class);
        BUILDER.insert(position, testString);
    }

    @Test
    public void shouldReplaceStringInRange() {
        int position = (int) (Math.random() * BUILDER.length());
        int endPosition = position + (int) (Math.random() * BUILDER.length());
        String temp = "";
        for (int i = 0; i < position; i++) {
            temp += INPUTSTRING.charAt(i);
        }
        for (int i = 0; i < testString.length(); i++) {
            temp += testString.charAt(i);
        }
        if (endPosition < INPUTSTRING.length()) {
            for (int i = endPosition; i < INPUTSTRING.length(); i++) {
                temp += INPUTSTRING.charAt(i);
            }
        }
        BUILDER.replace(position, endPosition, testString);
        Assert.assertEquals(temp, BUILDER.toString());
    }

    @Test
    public void shouldDeleteSpecifiedRange() {
        int position = (int) (Math.random() * BUILDER.length());
        int endPosition = position + (int) (Math.random() * BUILDER.length());
        String temp = "";
        for (int i = 0; i < position; i++) {
            temp += INPUTSTRING.charAt(i);
        }
        if (endPosition < INPUTSTRING.length()) {
            for (int i = endPosition; i < INPUTSTRING.length(); i++) {
                temp += INPUTSTRING.charAt(i);
            }
        }
        BUILDER.delete(position, endPosition);
        Assert.assertEquals(temp, BUILDER.toString());
    }

    @Test
    public void shouldReverseString() {
        String temp = "";
        for (int i = INPUTSTRING.length() - 1; i >= 0; i--) {
            temp += INPUTSTRING.charAt(i);
        }
        BUILDER.reverse();
        Assert.assertEquals(temp, BUILDER.toString());
    }

    @Test
    public void shouldReturnSubstringFromPositionToEnd() {
        int position = (int) (Math.random() * BUILDER.length());
        String temp = "";
        for (int i = 0; i < INPUTSTRING.length(); i++) {
            if (i >= position) {
                temp += INPUTSTRING.charAt(i);
            }
        }
        Assert.assertEquals(temp, BUILDER.substring(position));
    }

    @Test
    public void shouldReturnExceptionWhenIndexForSubstringIsNegative() throws IndexOutOfBoundsException {
        int position = (int) (Math.random() * BUILDER.length()) * -1;
        exception.expect(IndexOutOfBoundsException.class);
        BUILDER.substring(position);
    }
}
