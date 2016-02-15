package com.nixsolutions.ReflectionsTask1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by sobolenko on 2/15/2016.
 */
@Target(value= ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Public {
}
