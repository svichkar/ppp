import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by pantiukhin on 2/23/2016.
 */
public class StringBuilderTest {
    private StringBuilder sb;

    @Before
    public void executeBeforeEachTest() {
        //Given
        sb = new StringBuilder("hello");
    }

    @Test
    public void shouldAddStringToString() {
        //When
        sb.append(", world");
        //Then
        Assert.assertEquals("hello, world", sb.toString());
    }

    @Test
    public void shouldCancatenateVariousDataTypesInOneString() {
        //When
        sb.append('a' + " ").append(new Integer(12) + " ").append(new Double(12.0) + " ");
        sb.append(new Long(1L) + " ").append(3 + " ").append(23f);
        //Then
        Assert.assertEquals("helloa 12 12.0 1 3 23.0", sb.toString());
    }

    @Test
    public void shouldDeleteCharacterAtGivenIndex() {
        //When
        sb.deleteCharAt(0);
        //Then
        Assert.assertEquals("ello", sb.toString());
    }

    @Test
    public void shouldInsertCharacterAtGivenIndex() {
        //When
        sb.insert(1, 'W');
        //Then
        Assert.assertEquals('W', sb.charAt(1));
    }

    @Test
    public void shouldReturnSubstringFromString() {
        //When
        String substr = sb.substring(0, 3);
        //Then
        Assert.assertEquals("hel", substr);
    }

    @Test
    public void shouldReplaceCharactersInString() {
        //When
        sb.replace(0, 2, "ye");
        //Then
        Assert.assertEquals("yello", sb.toString());
    }

    @Test
    public void shouldReverseCharactersInString() {
        //When
        sb.reverse();
        //Then
        Assert.assertEquals("olleh", sb.toString());
    }
}
