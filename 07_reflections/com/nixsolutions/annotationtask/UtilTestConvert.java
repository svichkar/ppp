package com.nixsolutions.annotationtask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by rybkinrolla on 27.11.2015.
 */
public class UtilTestConvert {
    private static final Logger LOGGER = LogManager.getLogger("UtilTestConvert");

    public static void main(String[] args) {
        BedForConvert bed = new BedForConvert("noname", 1, 2, 0.5f);
        try {
            LOGGER.info(UtilObjectToStringConvert.ReflectByAnnotation(bed));
            LOGGER.info(UtilObjectToStringConvert.GoogleReflectByAnnotation(bed));
        } catch (NoSuchFieldException e) {
            LOGGER.catching(e);
        } catch (IllegalAccessException e) {
            LOGGER.catching(e);
        }
    }
}
