package com.nixsolutions;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * Created by Sergey on 13.11.2015.
 */
public class MyIterator implements Iterator {
    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Object next() throws NoSuchElementException {
        return null;
    }

    @Override
    public void remove() throws UnsupportedOperationException, IllegalStateException {

    }
}
