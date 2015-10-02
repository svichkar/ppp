package com.nixsolutions;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustCollection implements Collection {

    private Object[] internalArray;

    public CustCollection() {
	internalArray = new Object[0];
    }

    @Override
    public boolean add(Object e) {
	increaseLength(internalArray.length + 1);
	internalArray[internalArray.length - 1] = e;
	return true;
    }

    @Override
    public boolean addAll(Collection c) {
	int currentLength = internalArray.length;
	increaseLength(internalArray.length + c.size());
	try {
	    System.arraycopy((Object[]) c.toArray(), 0, internalArray, currentLength, c.size());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	for (Object a : internalArray) {
	    System.out.println(a);
	}
	return true;
    }

    @Override
    public void clear() {
	internalArray = new Object[0];

    }

    @Override
    public boolean contains(Object o) {
	for (Object item : internalArray) {
	    if (item.hashCode() == o.hashCode()) {
		if (item.equals(o)) {
		    return true;
		}
	    }
	}
	return false;
    }

    @Override
    public boolean containsAll(Collection c) {
	boolean result = true;
	for (Object item : c) {
	    result = contains(item);
	    if (!result) {
		break;
	    }
	}
	return result;
    }

    @Override
    public boolean isEmpty() {
	return internalArray.length == 0;
    }

    @Override
    public Iterator iterator() {
	return new CustIterator();
    }

    @Override
    public boolean remove(Object o) {
	boolean result = false;
	for (int i = 0; i < internalArray.length; i++) {
	    if (internalArray[i].hashCode() == o.hashCode()) {
		if (internalArray[i].equals(o)) {
		    Object[] temp = new Object[internalArray.length - 1];
		    try {
			System.arraycopy(internalArray, 0, temp, 0, i);
			System.arraycopy(internalArray, i + 1, temp, i, internalArray.length - i - 1);
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		    internalArray = temp;
		    i--;
		    result = true;
		}
	    }
	}
	return result;
    }

    @Override
    public boolean removeAll(Collection c) {
	boolean result = false;
	for (Object item : c) {
	    if (remove(item)) {
		result = true;
	    }
	}
	return result;
    }

    @Override
    public boolean retainAll(Collection c) {
	boolean result = false;
	for (int i = 0; i < internalArray.length; i++) {
	    if (!c.contains(internalArray[i])) {
		remove(internalArray[i]);
		result = true;
	    }
	}
	return result;
    }

    @Override
    public int size() {
	return internalArray.length;
    }

    @Override
    public Object[] toArray() {
	return internalArray;
    }

    @Override
    public Object[] toArray(Object[] a) {
	if (a.length < internalArray.length) {
	    a = new Object[internalArray.length];
	} else if (a.length > internalArray.length) {
	    a[internalArray.length] = null;
	}
	try {
	    System.arraycopy(internalArray, 0, a, 0, internalArray.length);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return a;
    }

    private void increaseLength(int newLength) {
	Object[] tempArray = new Object[newLength];
	System.arraycopy(internalArray, 0, tempArray, 0, internalArray.length);
	internalArray = tempArray;
    }

    private class CustIterator implements Iterator {
	private int index;

	public CustIterator() {
	    index = 0;
	}

	@Override
	public boolean hasNext() {
	    return index < CustCollection.this.size();
	}

	@Override
	public Object next() {
	    if (CustIterator.this.hasNext()) {
		Object nextElement = CustCollection.this.internalArray[index];
		index++;
		return nextElement;
	    } else {
		throw new NoSuchElementException();
	    }
	}
    }
}
