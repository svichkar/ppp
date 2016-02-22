package com.nixsolutions;

/**
 * Created by sobolenko on 2/22/2016.
 */
public class StringBuilderAppend {

    public String appendString(String basicString, String appendString) {
        StringBuilder stringAppend = new StringBuilder(basicString);
        String basic = basicString;
        return stringAppend.append(appendString).toString();
    }
}
