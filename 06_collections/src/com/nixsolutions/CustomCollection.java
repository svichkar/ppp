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

	/** The Constant DEFAULT_CAPACITY. */
	private static final int DEFAULT_CAPACITY = 5;

	/** The mass data. */
	private Object[] massData;

	/** The b. */
	private boolean b = false;

	/**
	 * Instantiates a new custom collection.
	 *
	 * @throws Exception
	 *             the exception
	 */
	public CustomCollection() throws Exception {
		this.massData = new Object[DEFAULT_CAPACITY];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#add(java.lang.Object)
	 */
	@Override
	public boolean add(Object e) {
		try {
			Object[] temp = massData.clone();
			massData = new Object[temp.length + 1];
			for (int i = 0; i < temp.length; i++) {
				massData[i] = temp[i];
			}
			massData[massData.length - 1] = e;
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
		for (Object i : massData) {
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
		for (Object ob : massData) {
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
		int quantity = 0;
		for (Object obj : c) {
			if (contains(obj)) {
				quantity++;
			}

		}
		if (c.size() == quantity) {
			return true;
		}
		return false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		if (massData.length != 0) {
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
		Object[] temp = massData.clone();
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
		for (Object o : c) {
			remove(o);
			b = true;
		}
		return b;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		boolean result = false;
		for (Object obj : massData) {
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
		return massData.length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#toArray()
	 */
	@Override
	public Object[] toArray() {
		return Arrays.copyOf(massData, massData.length);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#toArray(java.lang.Object[])
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		Object[] temp = massData.clone();
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
			if (actualIndex < massData.length) {
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
				E current = (E) massData[actualIndex];
				actualIndex++;
				return current;
			} else {
				throw new NoSuchElementException();
			}

		}

	}
}
