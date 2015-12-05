package com.nixsolutions.converter;

/**
 * Created by pixtart on 12/5/2015.
 */
public interface Converter<T, I> {
    public T get(I i);
}




    /*Реализовать интерфейс Converter с методом get, на вход принимающим параметризованный тип I
    и возвращающий параметризированный тип T. Реализовать 2 класса наследника - один преобразовывает Float в Double,
    другой - массив Integer в String (конкатенация всех элементов через пробел). Показать использование на примере*/