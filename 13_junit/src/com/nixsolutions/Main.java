package com.nixsolutions;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by sobolenko on 2/22/2016.
 */
public class Main {
    @Test
    public void appendStringTest() {
        StringBuilderAppend stringBA = new StringBuilderAppend();
        String result = stringBA.appendString("test","first");
        assertTrue(result.contains("first"));
    }
}
