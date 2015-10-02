package com.nixsolutions;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class CollectionImpl implements Collection {

    Object[] obj;

    public CollectionImpl() {
	obj = new Object[0];

    }

    private void increaseArraySizeOnOneElement() {
	Object[] temp = new Object[obj.length];
	temp = obj;
	obj = new Object[obj.length + 1];
	for (int i = 0; i < temp.length; i++) {
	    obj[i] = temp[i];

	}

    }

    @Override
    public boolean add(Object e) {
	try {
	    increaseArraySizeOnOneElement();
	    obj[obj.length - 1] = e;
	    return true;
	} catch (Exception ex) {
	    return false;
	}
    }

    @Override
    public boolean addAll(Collection c) {
	try {
	    for (Object object : c) {
		this.add(object);
	    }
	    return true;
	} catch (Exception ex) {
	    return false;
	}
    }

    @Override
    public void clear() {
	obj = new Object[0];

    }

    @Override
    public boolean contains(Object o) {

	for (Object object : obj) {
	    if (object != null && object.equals(o)) {
		return true;
	    }
	}

	return false;
    }

    @Override
    public boolean containsAll(Collection c) {
	for (Object object : c) {
	    if (!this.contains(object)) {
		return false;
	    }
	}
	return true;
    }

    @Override
    public boolean isEmpty() {
	for (int i = 0; i < obj.length; i++) {
	    if (obj[i] != null) {
		return false;
	    }
	}
	return true;
    }

    @Override
    public Iterator iterator() {

	return new MyIterator();
    }

    @Override
    public boolean remove(Object o) {
	try {
	    for (int i = 0; i < obj.length; i++) {
		if (obj[i].equals(o)) {
		    Object[] temp = new Object[obj.length - 1];
		    System.arraycopy(obj, 0, temp, 0, i);

		    System.arraycopy(obj, i + 1, temp, i, obj.length - i - 1);
		    obj = temp;
		    i = i - 1;
		}

	    }
	} catch (Exception e) {
	    return false;
	}
	return true;

    }

    @Override
    public boolean removeAll(Collection c) {
	try {
	    for (Object object : c) {
		if (this.contains(object)) {
		    this.remove(object);
		}
	    }
	} catch (Exception e) {
	    return false;
	}
	return true;
    }

    @Override
    public boolean retainAll(Collection c) {
	boolean result = false;
	for (int i = 0; i < obj.length; i++) {
	    if (!c.contains(obj[i])) {
		remove(obj[i]);
		result = true;
	    }
	}
	return result;
    }

    @Override
    public int size() {

	return obj.length;
    }

    @Override
    public Object[] toArray() {

	return obj;
    }

    @Override
    public Object[] toArray(Object[] a) {
	if (a.length < obj.length) {
	    a = new Object[obj.length];
	} else if (a.length > obj.length) {
	    a[obj.length] = null;
	}
	try {
	    System.arraycopy(obj, 0, a, 0, obj.length);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return a;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + Arrays.hashCode(obj);
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	CollectionImpl other = (CollectionImpl) obj;
	if (!Arrays.equals(this.obj, other.obj))
	    return false;
	return true;
    }

    private class MyIterator implements Iterator {

	private int iteratorIndex;

	public MyIterator() {
	    iteratorIndex = 0;
	}

	@Override
	public boolean hasNext() {

	    return iteratorIndex < CollectionImpl.this.size();
	}

	@Override
	public Object next() {
	    try {

		Object temp = CollectionImpl.this.obj[iteratorIndex];
		iteratorIndex++;
		return temp;
	    } catch (Exception e) {
		throw new NoSuchElementException();
	    }
	}

    }

}
