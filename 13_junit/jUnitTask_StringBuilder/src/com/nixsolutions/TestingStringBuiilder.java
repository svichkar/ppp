package com.nixsolutions;

        import org.junit.*;
        import org.junit.Assert;

/**
 * @suthor Sirotkin Mikhail
 * Class with unit-tests for StringBuilder class
 */
public class TestingStringBuiilder {

    private StringBuilder sb;
    private String one;
    private int number;

    @Before
    public void setup(){
        sb = null;
        one = "One1";
        number = 120;
    }

    @Test
    public void shouldCreateStringBuilderWithInputString(){
        sb = new StringBuilder(one);
        Assert.assertEquals(one, sb.toString());
    }

    @Test
    public void shouldCreateStringBuilderWithAccordingCapacity(){
        sb = new StringBuilder(number);
        Assert.assertEquals(number, sb.capacity());
    }

    @Test
    public void shouldDeleteCharsFromSpecifyedPlaceWithSpecifyedLength(){
        sb = new StringBuilder("0123 5678");
        Assert.assertEquals("0123 78", sb.delete(5, 7).toString());
    }

    @Test
    public void shouldDeleteCharWithSpecifyedIndex(){
        sb = new StringBuilder("Terst");
        Assert.assertEquals("Test", sb.deleteCharAt(2).toString());
    }

    @Test
    public void shouldReturnStartIndexOfSpecifyedString(){
        sb = new StringBuilder("Test base fun import");
        Assert.assertEquals(4, sb.indexOf(" base"));
    }

    @Test
    public void shouldInsertStringInsideStringBuilderValueOnSpecifyedPosition(){
        sb = new StringBuilder("But I Kant");
        Assert.assertEquals("But I am Kant", sb.insert(6, "am ").toString());
    }

    @Test
    public void shouldReturnIndexOfLastEntryOfSpecifyedStringInSubstring(){
        sb = new StringBuilder("Last 17 index is 17");
        Assert.assertEquals(17, sb.lastIndexOf("17"));
    }

    @Test
    public void shouldReturnLengthOfCurrentSubstring(){
        sb = new StringBuilder(one);
        Assert.assertEquals(4, sb.length());
    }

    @Test
    public void shouldReplaceSpecifyedPartOfSubstringToNewString(){
        sb = new StringBuilder("He can sleep");
        Assert.assertEquals("He can't sleep",sb.replace(3, 6, "can't").toString());
    }

    @Test
    public void shouldReverseCurrentSubstring(){
        sb = new StringBuilder("Reverse it");
        Assert.assertEquals("ti esreveR", sb.reverse().toString());
    }

    @Test
    public void shouldReturnSubstringFromSpecifyedIndexTillTheEnd(){
        sb = new StringBuilder("Test text");
        Assert.assertEquals("text", sb.substring(5).toString());

    }

    @Test
    public void shouldReturnSubstringBeetwenSpecifyedIndexes(){
        sb = new StringBuilder("From 7 .qwerty. to 14");
        Assert.assertEquals(".qwerty", sb.substring(7,14).toString());
    }
    
    @Test
    public void shouldAddToCurrentStringTextRepresentationOfBooleanValue(){
        sb = new StringBuilder("This is ");
        Assert.assertEquals("This is true", sb.append(true).toString());
    }

}

