package com.nixsolutions.collections;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class CustomCollection<E> implements Collection<E> {
	private int realSize = 0;
	private static final int DEFAULT_SIZE = 1;
	private E[] arrInternal;

	@SuppressWarnings("unchecked")
	public CustomCollection() {
		arrInternal = (E[]) new Object[DEFAULT_SIZE];
		realSize = 0;
	}

	@SuppressWarnings("unchecked")
	public CustomCollection(int collectionSize) {
		if (collectionSize <= 0) {
			throw new IllegalArgumentException();
		}
		arrInternal = (E[]) new Object[collectionSize];
	}

	/**
	 * Adding a new element to the current collection
	 * 
	 * @param e
	 *            - object supposed to be added
	 * @return true if the element was successfully added
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean add(E e) {
		try {
			if (realSize <= arrInternal.length - 1) {
				arrInternal[realSize] = e;
			} else {
				E[] temp = arrInternal.clone();
				arrInternal = (E[]) new Object[arrInternal.length + 1];
				System.arraycopy(temp, 0, arrInternal, 0, temp.length);
				arrInternal[arrInternal.length - 1] = e;
			}
			realSize++;
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * Adding a collection of objects to current one
	 * 
	 * @param collection
	 *            of objects
	 * @return true if all of the elements were added to current collection
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean addAll(Collection<? extends E> c) {
		try {
			E[] arrTemp = (E[]) new Object[realSize + c.size()];
			System.arraycopy(arrInternal, 0, arrTemp, 0, realSize);
			System.arraycopy(c.toArray(), 0, arrTemp, realSize, c.size());
			arrInternal = arrTemp;
			realSize = arrInternal.length;
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * Clears the current collection
	 * 
	 */
	@Override
	public void clear() {
		if (realSize > 0) {
			java.util.Arrays.fill(arrInternal, 0, realSize, null);
		}
		realSize = 0;
	}

	/**
	 * Determines if the current collection contains object
	 * 
	 * @param o
	 *            - object to verify if it is in current collection
	 * @return true if the current collection contains the object at least once
	 */
	@Override
	public boolean contains(Object o) {
		if (o != null) {
			for (int i = 0; i < realSize; i++) {
				if (arrInternal[i].equals(o))
					return true;
			}
		}
		return false;
	}

	/**
	 * Determines if the current collection contains each of object from
	 * incoming collection
	 * 
	 * @param collection
	 *            of objects to verify on containing
	 * @return true if current collection contains ALL of the elements from
	 *         incoming collection
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		boolean contains = true;
		for (Object e : c) {
			if (!this.contains(e)) {
				contains = false;
				break;
			}
		}
		return contains;
	}

	/**
	 * Verifies if the current collection is empty
	 * 
	 * @return true if the current collection is empty
	 */
	@Override
	public boolean isEmpty() {
		return realSize == 0;
	}

	/**
	 * Returns iterator of current collection
	 * 
	 * @return Iterator
	 */
	@Override
	public Iterator<E> iterator() {
		return (Iterator<E>) new CustomIterator();
	}

	/**
	 * Removes all of the objects equals to incoming on from current collection
	 * 
	 * @param object
	 *            that must be removed
	 * @return true if at least one object has been removed from current
	 *         collection
	 */
	@Override
	public boolean remove(Object o) {
		boolean isRemoved = false;
		for (int i = 0; i < realSize; i++) {
			if (arrInternal[i].equals(o)) {
				System.arraycopy(arrInternal, i + 1, arrInternal, i, realSize - 1 - i);
				arrInternal = Arrays.copyOfRange(arrInternal, 0, realSize - 1);
				realSize--;
				isRemoved = true;
			}
		}
		return isRemoved;
	}

	/**
	 * Removes all of the objects of incoming collection from the current one
	 * 
	 * @param collections
	 *            that supposed to be removed
	 * @return true if all of the objects have been removed from current
	 *         collection
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		int numberRemovedItems = 0;
		for (Object obj : c) {
			if (this.remove(obj)) {
				numberRemovedItems++;
			}
		}
		return numberRemovedItems == c.size();
	}

	/**
	 * Retains in the current collection the elements from incoming collection
	 * the rest removes
	 * 
	 * @param collection
	 *            to be retained in current collection
	 * @return true if all of the objects from incoming collection have been
	 *         retained in current collection
	 * @author Evgeniy
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		int retainedNumber = 0;
		Object[] tempArr = new Object[realSize];
		System.arraycopy(arrInternal, 0, tempArr, 0, realSize);
		for (Object obj : tempArr) {
			if (!c.contains(obj)) {
				this.remove(obj);
				retainedNumber++;
			}
		}
		return retainedNumber == c.size();
	}

	/**
	 * Returns the int size of current collection
	 * 
	 * @return size of current collection
	 */
	@Override
	public int size() {
		return realSize;
	}

	/**
	 * Returns an array containing the elements of the current collection
	 * 
	 * @return array of the elements of the current collection
	 */
	@Override
	public Object[] toArray() {
		return Arrays.copyOf(arrInternal, realSize);
	}

	/**
	 * Returns an array containing the objects of the current collection
	 * 
	 * @param collection
	 *            to be transformed to array
	 * @return array of the objects of the current collection
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {

		if (a.length < realSize) {
			a = (T[]) Array.newInstance(a.getClass().getComponentType(), realSize);
		} else if (a.length > realSize) {
			a[realSize] = null;
		}
		System.arraycopy(arrInternal, 0, a, 0, realSize);
		return a;

	}

	/**
	 * Implementation of the Iterator interface
	 * 
	 * @author Evgeniy
	 * @param Iterator
	 */
	@SuppressWarnings("hiding")
	private class CustomIterator<E> implements Iterator<E> {
		private int currentIndex;

		public CustomIterator() {
			this.currentIndex = 0;
		}

		@Override
		public boolean hasNext() {
			return (currentIndex < realSize);
		}

		@SuppressWarnings("unchecked")
		@Override
		public E next() {
			if (this.hasNext()) {
				return (E) arrInternal[currentIndex++];
			} else {
				return null;
			}
		}

	}
}
