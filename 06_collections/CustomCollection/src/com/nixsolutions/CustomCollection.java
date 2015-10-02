/**
 * 
 */
package com.nixsolutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import org.omg.CORBA.MARSHAL;

/**
 * @author mixeyes
 *
 */
public class CustomCollection<E> implements Collection<E> {

	private E[] customCollection;
	private static final int MAX_CAP = 15;
	private int size;

	/**
	 * 
	 */
	public CustomCollection() {
		customCollection = (E[]) new Object[MAX_CAP];
		size = 0;
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#add(java.lang.Object)
	 */
	@Override
	public boolean add(E element) {
		if (size + 1 <= MAX_CAP) {
			customCollection[size] = element;
			size++;
			return true;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends E> newCollection) {
		if (size + newCollection.size() < MAX_CAP) {
			int newSize = size + newCollection.size();
			E[] timeCollection = (E[]) new Object[MAX_CAP];
			System.arraycopy(customCollection, 0, timeCollection, 0, size);
			System.arraycopy(newCollection.toArray(), 0, timeCollection, size, newCollection.size());
			customCollection = timeCollection;
			size += newCollection.size();
			return true;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#clear()
	 */
	@Override
	public void clear() {
		customCollection = (E[]) new Object[MAX_CAP];
		size = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object element) {
		for (E elem : customCollection) {
			if (elem == null)
				return false;
			if (elem.equals((E) element)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<?> list) {
		boolean isContains = true;
		for (Object element : list) {
			if (element == null)
				break;
			if (!contains((E) element))
				isContains = false;
		}
		return isContains;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#isEmpty()
	 */
	@Override
	public boolean isEmpty() {

		return size == 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#iterator()
	 */
	@Override
	public Iterator<E> iterator() {
		return new CustomIterator<E>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object object) {
		if (contains(object)) {
			int index = 0;
			for (index = 0; index < size; index++) {
				if (customCollection[index].equals(object)) {
					break;
				}
			}
			while (index < size) {
				customCollection[index] = customCollection[index + 1];
				index++;
			}
			size--;
			return true;
		} else {
			throw new NoSuchElementException("Can't find this element");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> list) {
		if (containsAll(list)) {
			for (Object element : list) {
				if (element == null)
					break;
				remove(element);
			}
			return true;
		} else {
			throw new NoSuchElementException("Can't find some elements");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> list) {
		while (containsAll(list)) {
			for (E element : this) {
				if (element == null)
					break;
				if (!list.contains(element))
					remove(element);
			}
			if (list.containsAll(this))
				return true;

		}
			return false;
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
		return customCollection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#toArray(java.lang.Object[])
	 */
	@Override
	public <T> T[] toArray(T[] arg0) {
		return (T[]) customCollection;
	}

	private class CustomIterator<E> implements Iterator<E> {
		private int actualIndex;

		public CustomIterator() {
			this.actualIndex = 0;
		}

		@Override
		public boolean hasNext() {
			return this.actualIndex < customCollection.length;
		}

		@Override
		public E next() {
			if (this.hasNext()) {
				E current = (E) customCollection[actualIndex];
				actualIndex++;
				return current;
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
