package com.nixsolutions.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class CustomCollection<E> implements Collection<E> {
	private int realSize = 0;
	private static final int DEFAULT_SIZE = 10;
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

	// DONE
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

	// DONE
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

	// DONE
	@Override
	public void clear() {
		if (realSize > 0) {
			java.util.Arrays.fill(arrInternal, 0, realSize, null);
		}
		realSize = 0;
	}

	// DONE
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

	// DONE
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

	// DONE
	@Override
	public boolean isEmpty() {

		return realSize == 0;
	}

	// DONE
	@Override
	public Iterator<E> iterator() {

		return (Iterator<E>) new CustomIterator();
	}

	// DONE
	@Override
	public boolean remove(Object o) {
		/*
		 * int sizeBefore = realSize; arrInternal = (E[])
		 * Arrays.stream(this.arrInternal).filter(p -> !p.equals(o)).toArray();
		 * realSize--; return !(realSize == sizeBefore);
		 */

		boolean isRemoved = false;
		for (int i = 0; i < realSize; i++) {
			if (arrInternal[i].equals(o)) {
				System.arraycopy(arrInternal, i + 1, arrInternal, i, realSize - 1 - i);
				arrInternal = Arrays.copyOfRange(arrInternal, 0, realSize - 1);
				realSize--; //
				this.arrInternal = arrInternal;

				isRemoved = true;
			}
		}

		return isRemoved;
	}

	// DONE
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

	@SuppressWarnings("unchecked")
	@Override
	public boolean retainAll(Collection<?> c) {
		boolean result = false;
		int retainedNumber = 0;
		for (Object obj : arrInternal) {
			if (!c.contains(obj)) {
				this.remove(obj);
				retainedNumber++;
			}
		}

		return retainedNumber==c.size();

	}

	// DONE
	@Override
	public int size() {
		return realSize;
	}

	// DONE
	@Override
	public Object[] toArray() {
		return arrInternal;
	}

	// DONE
	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		return (T[]) arrInternal;

	}

	// DONE
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
				System.out.println(currentIndex);
				return (E) arrInternal[currentIndex++];
			} else {
				return null;
			}
		}

	}
}
