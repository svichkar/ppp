package com.nixsolutions;

/**
 * @author Sirotkin Mikhail
 * Class with different type of field for checking reflection methods
 */
public class MyTestClass {

    @Public
    private String field1;
    @Public
    private int field2;
    private boolean field3;

    public MyTestClass(String field1, int field2, boolean field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

}
