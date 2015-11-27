package com.nixsolutions;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The Class CustomCollection.
 *
 * @param <E>
 *            the element type
 */
public class CustomCollection<E> implements Collection<E> {

	/** The Constant DEFAULT */
	private static final int DEFAULT = 1;
	private int size = 0;

	/** The data Collect. */
	private Object[] dataCollect;

	/** The b. */
	private boolean b = false;

	/**
	 * Instantiates a new custom collection.
	 *
	 * @throws Exception
	 *             the exception
	 */
	public CustomCollection() throws Exception {
		dataCollect = (E[]) new Object[DEFAULT];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#add(java.lang.Object)
	 */
	@Override
	public boolean add(Object e) {
		try {
			Object[] temp = dataCollect.clone();
			dataCollect = new Object[temp.length + 1];
			for (int i = 0; i < temp.length; i++) {
				dataCollect[i] = temp[i];
			}
			dataCollect[dataCollect.length - 1] = e;
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection c) {
		for (Object obj : c) {
			add(obj);
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#clear()
	 */
	@Override
	public void clear() {
		for (Object i : dataCollect) {
			i = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object o) {
		for (Object ob : dataCollect) {
			if (ob.equals(o)) {
				b = true;
			} else {
				b = false;
			}
		}
		return b;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		boolean status = true;
		for (Object object : c) {
			if (!this.contains(object)) {
				status = false;

				break;
			}
		}
		return status;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		if (size != 0) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#iterator()
	 */
	@Override
	public Iterator<E> iterator() {
		return new OwnIterator<E>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object o) {
		Object[] temp = dataCollect.clone();
		for (Object ob : temp) {
			if (ob.equals(o)) {
				remove(o);
				b = true;
			} else {
				b = false;
			}
		}
		return b;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		int initialSize = dataCollect.length;
		for (Object o : c) {
			while (this.remove(o)) {
			}
		}
		return initialSize != dataCollect.length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		boolean result = false;
		for (Object obj : dataCollect) {
			if (!c.contains(obj)) {
				remove(obj);
				result = true;
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#size()
	 */
	@Override
	public int size() {
		return size;
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#toArray()
	 */
	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		System.arraycopy(dataCollect, 0, array, 0, size);
		return array;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#toArray(java.lang.Object[])
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		Object[] temp = dataCollect.clone();
		return (T[]) temp.clone();
	}

	/**
	 * The Class OwnIterator.
	 *
	 * @param <E>
	 *            the element type
	 */
	private class OwnIterator<E> implements Iterator<E> {

		/** The actual index. */
		private int actualIndex = 0;

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			if (actualIndex < dataCollect.length) {
				return true;
			}
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#next()
		 */
		@Override
		public E next() {
			if (this.hasNext()) {
				E current = (E) dataCollect[actualIndex];
				actualIndex++;
				return current;
			} else {
				throw new NoSuchElementException();
			}

		}

	}
}
