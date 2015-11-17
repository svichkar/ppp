package com.nixsolutions;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyCollection implements Collection {
	private Object[] myArray = new Object[] {};
	private Object[] tempArray = new Object[] {};
	private Iterator myIterator;

	@Override
	public boolean add(Object e) {
		try {
			tempArray = new Object[myArray.length + 1];
			System.arraycopy(myArray, 0, tempArray, 0, myArray.length);
			tempArray[myArray.length] = e;
			myArray = tempArray;
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean addAll(Collection c) {
		try {
			for (Object o : c)
				this.add(o);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public void clear() {
		myArray = new Object[] {};

	}

	@Override
	public boolean contains(Object o) {
		for (Object obj : myArray) {
			if (obj.equals(o))
				return true;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection c) {
		myIterator = c.iterator();
		while (myIterator.hasNext()) {
			if (!this.contains(myIterator.next())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		return (myArray.length == 0);
	}

	@Override
	public Iterator iterator() {
		return new MyIterator();
	}

	@Override
	public boolean remove(Object o) {
		try {
			int index = 0;
			for (int i = 0; i < myArray.length; i++)
				if (myArray[i].equals(o)) {
					index = i;
				}

			tempArray = new Object[myArray.length - 1];
			System.arraycopy(myArray, 0, tempArray, 0, index);
			System.arraycopy(myArray, index + 1, tempArray, index, myArray.length - index - 1);
			myArray = tempArray;
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean removeAll(Collection c) {
		try {
			for (Object o : c)
				if (this.contains(o))
					this.remove(o);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean retainAll(Collection c) {
		try {
			tempArray = new Object[myArray.length];
			int j = 0;
			for (int i = 0; i < myArray.length; i++)
				if (c.contains(myArray[i])) {
					tempArray[j] = myArray[i];
					j++;
				}
			myArray = new Object[j];
			System.arraycopy(tempArray, 0, myArray, 0, j);
			return true;
		}

		catch (Exception ex) {
			return false;
		}

	}

	@Override
	public int size() {
		return myArray.length;
	}

	@Override
	public Object[] toArray() {
		Object[] newArray = new Object[myArray.length];
		System.arraycopy(myArray, 0, newArray, 0, myArray.length);
		return newArray;
	}

	@Override
	public Object[] toArray(Object[] a) {
		if (a.length < myArray.length)
			a = new Object[myArray.length];
		else if (a.length > myArray.length)
			for (int i = myArray.length; i < a.length; i++)
				a[i] = null;
		System.arraycopy(myArray, 0, a, 0, myArray.length);
		return a;
	}

	private class MyIterator implements Iterator {

		private int iterator;

		public MyIterator() {
			iterator = 0;
		}

		@Override
		public boolean hasNext() {
			return iterator < MyCollection.this.size();
		}

		@Override
		public Object next() {
			try {
				return MyCollection.this.myArray[iterator++];
			} catch (Exception ex) {
				throw new NoSuchElementException();
			}
		}

	}

}
