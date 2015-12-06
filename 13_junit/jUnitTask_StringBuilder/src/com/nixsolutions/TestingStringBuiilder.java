package com.nixsolutions;

        import org.junit.*;
        import org.junit.Assert;

/**
 * @suthor Sirotkin Mikhail
 * Class with unit-tests for StringBuilder class
 */
public class TestingStringBuiilder {

    private StringBuilder sb2;
    private String one;
    private String second;
    private int number;

    @Before
    public void setup(){
        //sb = new StringBuilder("Test text");
        one = "One";
        second = "Second";
        number = 120;
    }

    @Test
    public void shouldCreateStringBuilderWithInputString(){
        sb2 = new StringBuilder(one);
        Assert.assertEquals(one, sb2.toString());
    }

    @Test
    public void shouldCreateStringBuilderWithAccordingCapacity(){
        sb2 = new StringBuilder(number);
        Assert.assertEquals(number, sb2.capacity());
    }

    /*@Test
    public void shouldConcatinateStrings(){

    }*/

    /*
    https://docs.oracle.com/javase/8/docs/api/java/lang/StringBuilder.html#

    delete(int start, int end)

    deleteCharAt(int index)

 	indexOf(String str)

     insert(int offset, boolean b)

      	insert(int offset, char c)

      	 	insert(int offset, long l)

      	 	 	lastIndexOf(String str)

      	 	 	 	length()

      	 	 	 	replace(int start, int end, String str)

      	 	 	 	reverse()

      	 	 	 	setLength(int newLength)
Sets the length of the character sequence.

 	substring(int start)

 	substring(int start, int end)

 	 	trimToSize()

 	 	


     */
}

