package com.nixsolutions;

import java.io.Serializable;

/**
 * the class was created to check ho serialization works
 * @author kryzhanovskiy
 *
 */
public class ToBeSerialized implements Serializable {

    private static final long serialVersionUID = -6599696678816748199L;

    private String param1 = "param1";
    private String param2 = "param2";
    private String param3 = "param3";
    private transient String param4 = "param4";

    public String getParam1() {
        return param1;
    }
    public void setParam1(String param1) {
        this.param1 = param1;
    }
    public String getParam2() {
        return param2;
    }
    public void setParam2(String param2) {
        this.param2 = param2;
    }
    public String getParam3() {
        return param3;
    }
    public void setParam3(String param3) {
        this.param3 = param3;
    }
    public String getParam4() {
        return param4;
    }
    public void setParam4(String param4) {
        this.param4 = param4;
    }



}

