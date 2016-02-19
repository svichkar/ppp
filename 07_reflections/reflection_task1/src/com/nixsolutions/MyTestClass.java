package com.nixsolutions;

/**
 * @author Sirotkin Mikhail
 * Class with different type of field for checking reflection methods
 */
public class MyTestClass {

    @Public
    private String field1;
    @Public
    public int field2;
    private boolean field3;
    @Public
    private boolean field4;

    public MyTestClass(String field1, int field2, boolean field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        this.field4 = field3;
    }

    public String getfield1() {
        return field1;
    }
    public void setfield1(String field1) {
        this.field1 = field1;
    }

    public int getfield2() {
        return field2;
    }
    public void setfield2(int field2) {
        this.field2 = field2;
    }

    public boolean getfield3() {
        return field3;
    }
    public void setfield3(boolean field3) {
        this.field3 = field3;
    }
    public boolean getfield4() {
        return field4;
    }
    public void setfield4(boolean field4) {
        this.field4 = field4;
    }

}