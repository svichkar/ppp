package com.nixsolutions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Serko on 27.11.2015.
 */
public class ForTestObjects {
    @Public
    private String field0;
    @Public
    private int field1;
    private boolean field2;
    private double field3;
    private static final Logger LOGGER = LogManager.getLogger(ForTestObjects.class);

    public ForTestObjects(String field0, int field1, boolean field2, double field3) {
        this.field0 = field0;
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        LOGGER.info(field0 + "/" + field1 + "/" + field2 + "/" + field3);
    }
}
