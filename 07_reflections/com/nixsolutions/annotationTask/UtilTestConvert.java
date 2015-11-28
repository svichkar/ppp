package com.nixsolutions.annotationTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by rybkinrolla on 27.11.2015.
 */
public class UtilTestConvert {
    private static final Logger logger = LogManager.getLogger("UtilTestConvertLogger");

    public static void main(String[] args) {
        BedForConvert bed = new BedForConvert("noname", 1, 2, 0.5f);
        try {
            logger.info(UtilObjectToStringConvert.ReflectByAnnotation(bed));
            logger.info(UtilObjectToStringConvert.GoogleReflectByAnnotation(bed));
        } catch (NoSuchFieldException e) {
            logger.catching(e);
        } catch (IllegalAccessException e) {
            logger.catching(e);
        }
    }
}
