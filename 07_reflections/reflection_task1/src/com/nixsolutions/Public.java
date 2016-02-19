package com.nixsolutions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

    /**
     * @author Sirotkin Mikhail
    */
    @Retention (value = RetentionPolicy.RUNTIME)
    @Target(value = ElementType.FIELD)
    public @interface Public {

    }