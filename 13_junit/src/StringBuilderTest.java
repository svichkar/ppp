import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by pantiukhin on 2/23/2016.
 */
public class StringBuilderTest {
    private StringBuilder sb;
    private String functionName;

    @BeforeClass
    public static void executeBeforeAllTests(){
        System.out.println("Testing the StringBuilder functions");
    }
    @Before
    public void executeBeforeEachTest() {
        //Given
        sb = new StringBuilder("hello");
    }

    @Test
    public void testAppend() {
        functionName = "append()";
        //When
        sb.append("a");
        //Then
        Assert.assertEquals("helloa", sb.toString());
    }

    @Test
    public void testDeleteCharAt() {
        functionName = "deleteCharAt()";
        //When
        sb.deleteCharAt(0);
        //Then
        Assert.assertEquals("ello", sb.toString());
    }

    @Test
    public void testInsert() {
        functionName = "insert()";
        //When
        sb.insert(1, 'W');
        //Then
        Assert.assertEquals('W', sb.charAt(1));
    }

    @Test
    public void testSubstring() {
        functionName = "substring()";
        //When
        String substr = sb.substring(0, 3);
        //Then
        Assert.assertEquals("hel", substr);
    }

    @Test
    public void testReplace() {
        functionName = "replace()";
        //When
        sb.replace(0, 2, "ye");
        //Then
        Assert.assertEquals("yello", sb.toString());
    }

    @Test
    public void testReverse() {
        functionName = "reverse()";
        //When
        sb.reverse();
        //Then
        Assert.assertEquals("olleh", sb.toString());
    }

    @After
    public void executeAfterEachTest() {
        System.out.println("Finished testing the " + functionName + " function");
    }
    @AfterClass
    public static void executeAfterAllTests(){
        System.out.println("Finished testing the StringBuilder functions");
    }
}
