package com.nixsolutions.generics2;

/** Converter interface for the task 2 of 10_generics lab. */
public interface Converter<T, I> {
    
    /**
     * Converts the input type to the output type.
     * @param input Input type to convert.
     * @return Returns input type converted to the specified type.
     */
    public T get(I input);
}
