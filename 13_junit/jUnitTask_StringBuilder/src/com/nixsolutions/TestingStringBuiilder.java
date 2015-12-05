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

}

