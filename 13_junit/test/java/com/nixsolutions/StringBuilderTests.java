package java.com.nixsolutions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by sobolenko on 2/22/2016.
 */
public class StringBuilderTests {

    StringBuilder stringBuilderForTesting;
    String inputString = "";

    @Before
    public void initializeEnv() {
        stringBuilderForTesting = new StringBuilder();
    }

    @Test
    public void stringBuilderAppendTest() {
        //given
        stringBuilderForTesting.append("string of the last occurrence of the specified substring");
        //when
        stringBuilderForTesting.append(" first");
        //then
        assertTrue(stringBuilderForTesting.toString().contains("first"));
    }

    @Test
    public void stringBuilderIndexOfTest() {
        //given
        stringBuilderForTesting.append("string of the last occurrence of the specified substring");
        //when
        int index = stringBuilderForTesting.indexOf("i");
        //then
        assertTrue(index == 3);
    }

    @Test
    public void stringBuilderDeleteChatAtTest() {
        //given
        stringBuilderForTesting.append("string of the last occurrence of the specified substring");
        //when
        stringBuilderForTesting.deleteCharAt(14);
        //then
        assertFalse(stringBuilderForTesting.toString().contains("l"));
    }

    @Test
    public void stringBuilderReplaceTest() {
        //given
        stringBuilderForTesting.append("string of the last occurrence of the specified substring");
        //when
        stringBuilderForTesting.replace(4, 10, "insert");
        //then
        assertTrue(stringBuilderForTesting.toString().contains("insert") && stringBuilderForTesting.charAt(6) == 's');
    }

    @Test
    public void stringBuilderSubstringTest() {
        //given
        stringBuilderForTesting.append("string of the last occurrence of the specified substring");
        //when
        String subltring = stringBuilderForTesting.substring(4, 6);
        //then
        assertTrue(subltring.equals("ng"));
    }

    @Test
    public void stringBuilderReverseTest() {
        //given
        stringBuilderForTesting.append("string of the last occurrence of the specified substring");
        //when
        stringBuilderForTesting.reverse();
        //then
        assertTrue(stringBuilderForTesting.toString().equals("gnirtsbus deificeps eht fo ecnerrucco tsal eht fo gnirts"));
    }

    @Test
    public void stringBuilderLastIndexOfTest() {
        //given
        stringBuilderForTesting.append("string of the last occurrence of the specified substring");
        //when
        int result = stringBuilderForTesting.lastIndexOf("string");
        //then
        assertTrue(result == 50);
    }

    @Test
    public void stringBuilderCodePointAtTest() {
        //given
        stringBuilderForTesting.append("string of the last occurrence of the specified substring");
        //when
        int symbolIndex = (char) stringBuilderForTesting.codePointAt(14);
        //then
        assertTrue(symbolIndex == Character.valueOf('l'));
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void stringBuilderDeleteTest() throws StringIndexOutOfBoundsException {
        //given
        stringBuilderForTesting.append("string of the last occurrence of the specified substring");
        //when
        stringBuilderForTesting.delete(60, 70);
        //then
        fail("Index out of bounds");
    }

    @After
    public void clearAll() {
        stringBuilderForTesting = new StringBuilder(inputString);
    }
}
