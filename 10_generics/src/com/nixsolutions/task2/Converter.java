package com.nixsolutions.task2;
/**
 * Created by svichkar on 12/3/2015.
 */
public interface  Converter <I,T> {

    /**
     *
     * @param <I> - type to convert from
     * @param <T> - type to convert to
     */
    public T get (I from);
}
