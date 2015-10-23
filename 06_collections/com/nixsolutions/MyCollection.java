package com.nixsolutions;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class contains implementation of my own collection
 * 
 * @author maxb
 * 
 * @param <E>
 *            Define the type of data within collection
 */
public class MyCollection<E> implements Collection<E> {

	Object[] myStorage = new Object[] {};

	/**
	 * Add all items of defined collection into our collection
	 * 
	 */
	public boolean addAll(Collection<? extends E> c) {
		// add items one by one
		for (E oneC : c) {
			add((E) oneC);
		}
		return myStorage[myStorage.length - 1] != null ? true : false;
	}

	/**
	 * Add one item into collection
	 * 
	 */
	public boolean add(E e) {
		extendArray(1);
		myStorage[myStorage.length - 1] = e;
		return myStorage[myStorage.length - 1] != null ? true : false;
	};

	/**
	 * Change size of internal array at defined size. Original items will copied
	 * 
	 * @param amount
	 *            Variable type int.
	 */
	private void extendArray(int amount) {
		// create new array with bigger size
		myStorage = Arrays.copyOf(myStorage, myStorage.length + amount);
	}

	/**
	 * Change size of array at defined size.Original items will copied
	 * 
	 * @param original
	 *            Original array
	 * @param extendAt
	 *            Define at how many items you want to extend an array 
	 * @return
	 */
	private Object[] extendArray(Object[] original, int extendAt) {
		return Arrays.copyOf(original, original.length + extendAt);
	}

	/**Provide amount of items
	 * 
	 */
	public int size() {
		return myStorage.length;
	}

	/**Check if collection is empty
	 * 
	 */
	public boolean isEmpty() {
		return myStorage.length == 0 ? true : false;
	}

	/**Allows us to check that our collection contains the provided object
	 * 
	 */
	@SuppressWarnings("unchecked")
	public boolean contains(Object o) {
		boolean isPresent = false;
		if (o == null) {
			throw new IllegalArgumentException();
		}

		for (Object oneO : myStorage) {
			try {
				if (o.getClass().cast(o).equals((E) oneO)) {
					isPresent = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
				isPresent = false;
			}

		}
		return isPresent;
	}

	public Iterator<E> iterator() {
		return new MyIterator<E>();
	}

	public Object[] toArray() {
		return myStorage;
	}

	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		int size = a.length + myStorage.length;
		int startIndex = a.length > 0 ? a.length - 1 : 0;
		T[] newA = (T[]) Array.newInstance(a.getClass().getComponentType(),
				size);

		for (Object oneO : myStorage) {
			newA[startIndex] = (T) oneO;
			startIndex++;
		}
		return newA;
	}

	/**Allows us to remove an item from our collection
	 * 
	 */
	@SuppressWarnings("unchecked")
	public boolean remove(Object o) {
		boolean modified = false;
		Object[] tempArray = new Object[] {};

		int j = 0;
		for (int i = 0; i < myStorage.length; i++) {
			if (o.getClass().cast(o).equals((E) myStorage[i])) {
				modified = true;
			} else {
				tempArray = extendArray(tempArray, 1);
				tempArray[j] = myStorage[i];
				j++;
			}
		}
		// assign modified array back
		myStorage = tempArray;
		return modified;
	}

	/**Allows us to validate that all items from provided collection are within our collection
	 * 
	 */
	public boolean containsAll(Collection<?> c) {
		Iterator<?> e = c.iterator();
		while (e.hasNext()) {
			if (!contains(e.next())) {
				return false;
			}
		}
		return true;
	}

	/**Method that allows us to remove in our collection only items from provided collection
	 * 
	 */
	public boolean removeAll(Collection<?> c) {
		boolean modified = false;
		Iterator<?> e = c.iterator();

		int i = 0;
		while (e.hasNext()) {
			Object obj = e.next();
			if (contains(obj)) {
				remove(obj);
				modified = true;
			}
		}

		return modified;
	}

	/**Method that allows us to save in our collection only items from provided collection
	 * 
	 */
	public boolean retainAll(Collection<?> c) {
		boolean modified = false;
		Object[] tempArray = new Object[] {};
		Iterator<?> e = c.iterator();
		int i = 0;
		while (e.hasNext()) {
			Object obj = e.next();
			if (contains(obj)) {
				tempArray = extendArray(tempArray, 1);
				tempArray[i] = obj;
				i++;
			} else {
				modified = true;
			}
		}
		myStorage = tempArray;
		return modified;
	}

	/**Remove all items of collection
	 * 
	 */
	public void clear() {
		myStorage = new Object[] {};
	}

	/**Provide all items of collection as a string
	 * 
	 * @param delimiter Define delimiter that have to be present between items in string
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String toString(String delimiter) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < myStorage.length; i++) {
			if (i != myStorage.length - 1) {
				builder.append((E) myStorage[i]);
				builder.append(delimiter);
			} else {
				builder.append((E) myStorage[i]);
			}
		}
		return builder.toString();
	}

	private class MyIterator<E> implements Iterator<E> {
		private int cursorindex;
		private Object cursor;

		public MyIterator() {
			this.cursorindex = 0;
			this.cursor = myStorage[this.cursorindex];
		}

		public boolean hasNext() {
			return this.cursorindex < myStorage.length;
		}

		public E next() {
			if (!this.hasNext()) {
				throw new NoSuchElementException();
			}
			int current = this.cursorindex;
			this.cursorindex++;
			this.cursor = myStorage[current];
			return (E) this.cursor;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
