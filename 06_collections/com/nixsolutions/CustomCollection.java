package com.nixsolutions;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomCollection<E> implements Collection<E> {
	private E[] dataArr;
	private int currentSize;
	
	public CustomCollection(E[] inputArr) {
		this.dataArr = inputArr;
		this.currentSize = inputArr.length;
	}
	
	public boolean add(E e) {
		try {
			dataArr = (E[]) new Object[currentSize+1];
			currentSize = dataArr.length;
			dataArr[currentSize-1] = e;
			return true;
		} catch (Exception ex) {
			throw new IllegalArgumentException(ex);
		}
	}
	
	public boolean addAll(Collection<? extends E> c) {
		
	}
	
	public boolean remove(Object o) {
		boolean result = false;
		for (Iterator<E> iter = iterator(); iter.hasNext();) {
			E curItem = iter.next();
			if (o != null && o.equals(curItem)) {
				curItem = null;
				result = true;
			}
		}
		return result;
	}
	
	public boolean removeAll(Collection<?> c) {
		boolean result = false;
		for (Object item : c) {
			result = result || remove(item);
		}
		return result;
	}
	
	public boolean contains(Object o) {
		boolean result = false;
		for (Iterator<E> iter = iterator(); iter.hasNext();) {
			E curItem = iter.next();
			if (o != null && o.equals(curItem)) {
				result = true;
				break;
			}
		}
		return result;
	}
	
	public boolean containsAll(Collection<?> c) {
		boolean result = false;
		for (Object item : c) {
			result = result || contains(item);
		}
		return result;
	}
	
	public int size() {
		return currentSize;
	}
	
	public boolean isEmpty() {
		boolean result = true;
		for (Iterator<E> iter = iterator(); iter.hasNext();) {
			E curItem = iter.next();
			result = result & curItem == null;
		}
		return result;
	}
	
	public E[] toArray() {
		return dataArr;
	}
	
	public <T> T[] toArray(T[] a) {
		
	}
	
	public void clear() {
		for (Iterator<E> iter = iterator(); iter.hasNext();) {
			E curItem = iter.next();
			curItem = null;
		}
	}
	
	public boolean retainAll(Collection<?> c) {
		
	}
	
	public Iterator<E> iterator() {
		return new CustomIterator();
	}
	
	private class CustomIterator<E> implements Iterator<E> {
		private int cursor;
		
		public CustomIterator() {
			this.cursor = 0;
		}
		
		public boolean hasNext() {
			return this.cursor < CustomCollection.this.dataArr.length;
		}
		
		public E next() {
			if (this.hasNext()) {
				E current = (E) CustomCollection.this.dataArr[cursor];
				cursor++;
				return current;
			}
			else {
				throw new NoSuchElementException();
			}
		}
	}
}
