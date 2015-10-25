package com.nixsolutions;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomCollection<E> implements Collection<E> {
	private E[] dataArr;
	private int currentSize;
	private static final int DEFAULT_CAPACITY = 10;
	
	public CustomCollection() {
		dataArr = (E[]) new Object[DEFAULT_CAPACITY];
	}
	
	public CustomCollection(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException();
		}
		dataArr = (E[]) new Object[capacity];
	}
	
	public CustomCollection(E[] inputArr) {
		dataArr = (E[]) new Object[inputArr.length];
		System.arraycopy(inputArr, 0, dataArr, 0, inputArr.length);
		currentSize = inputArr.length;
	}
	
	@Override
	public boolean add(E e) {
		try {
			ensureCapacity(currentSize + 1);
			dataArr[currentSize] = e;
			currentSize++;
			return true;
		} catch (Exception ex) {
			throw new IllegalArgumentException(ex);
		}
	}
	
	@Override
	public boolean addAll(Collection<? extends E> c) {
		try {
			E[] tempArr = (E[]) new Object[currentSize + c.size()];
			System.arraycopy(dataArr, 0, tempArr, 0, currentSize);
			System.arraycopy(c.toArray(), 0, tempArr, currentSize, c.size());
			dataArr = tempArr;
			currentSize = dataArr.length;
			return true;
		} catch (Exception ex) {
			throw new IllegalArgumentException(ex);
		}
	}
	
	@Override
	public boolean remove(Object o) {
		boolean result = false;
		int modCount = 0;
		for (int i = 0; i < currentSize; i++) {
			if (o.equals(dataArr[i])) {
				E[] tempArr = (E[]) new Object[currentSize];
				System.arraycopy(dataArr, 0, tempArr, 0, i);
				System.arraycopy(dataArr, i+1, tempArr, i, currentSize - i - 1);
				dataArr = tempArr;
				modCount++;
				result = true;
			}
		}
		currentSize -= modCount;
		return result;
	}
	
	@Override
	public boolean removeAll(Collection<?> c) {
		boolean result = false;
		for (Object item : c) {
			result = result | remove(item);
		}
		return result;
	}
	
	@Override
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
	
	@Override
	public boolean containsAll(Collection<?> c) {
		boolean result = true;
		for (Object item : c) {
			result = result && contains(item);
		}
		return result;
	}
	
	@Override
	public int size() {
		return currentSize;
	}
	
	@Override
	public boolean isEmpty() {
		return currentSize == 0;
	}
	
	@Override
	public E[] toArray() {
		E[] array = (E[]) new Object[currentSize];
		System.arraycopy(dataArr, 0, array, 0, currentSize);
		return array;
	}
	
	@Override
	public <T> T[] toArray(T[] a) {
		if (a.length < currentSize) {
			a = (T[]) Array.newInstance(a.getClass().getComponentType(), currentSize);
		} else if (a.length > currentSize) {
			a[currentSize] = null;
		}
		System.arraycopy(dataArr, 0, a, 0, currentSize);
		return a;
	}
	
	@Override
	public void clear() {
		if (currentSize > 0) {
			java.util.Arrays.fill(dataArr, 0, currentSize, null);
			currentSize = 0;
		}
	}
	
	@Override
	public boolean retainAll(Collection<?> c) {
		boolean result = false;
		E[] tempArr = (E[]) new Object[currentSize];
		int i = 0;
		for (Object item : c) {
			if (contains(item)) {
				tempArr[i] = (E) item;
				i++;
				result = true;
			}
		}
		if (result) {
			dataArr = tempArr;
			currentSize = i;
		}
		return result;
	}
	
	@Override
	public Iterator<E> iterator() {
		return new CustomIterator<E>();
	}
	
	public E get(int index) {
		checkBoundExclusive(index);
		return dataArr[index];
	}
	
	public void set(int index, E e) {
		checkBoundExclusive(index);
		dataArr[index] = e;
	}
	
	private void checkBoundExclusive(int index) {
		if (index >= currentSize) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + currentSize);
		}
	}
	
	private void ensureCapacity(int minCapacity) {
		int current = dataArr.length;
		if (minCapacity > current) {
			E[] newData = (E[]) new Object[Math.max(current, minCapacity)];
			System.arraycopy(dataArr, 0, newData, 0, current);
			dataArr = newData;
		}
	}
	
	private class CustomIterator<E> implements Iterator<E> {
		private int cursor;
		
		public CustomIterator() {
			this.cursor = 0;
		}
		
		@Override
		public boolean hasNext() {
			return this.cursor < CustomCollection.this.dataArr.length;
		}
		
		@Override
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
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
