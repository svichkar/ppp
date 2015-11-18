package com.nixsolutions;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CollectionImplementation<E> implements Collection<E> {

	private int size = 0;
	private Object[] arr;

	private IteratorImplementation iterator = new IteratorImplementation();

	public CollectionImplementation() throws Exception {
		this.arr = new Object[size];
	}

	@Override
	public boolean add(E e) {
		size = size + 1;
		arr = Arrays.copyOf(arr, size);
		arr[size - 1] = e;
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean res = false;
		for (E o : c) {
			res = add(o);
		}
		return res;
	}

	@Override
	public void clear() {
		size = 0;
		this.arr = new Object[size];
	}

	@Override
	public boolean contains(Object o) {
		for (int i = 0; i < size; i++) {
			if (arr[i].equals(o))
				return true;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		boolean result = true;
		for (Object o : c)
			if (!this.contains(o)) {
				result = false;
				break;
			}
		return result;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public boolean remove(Object o) {
		Object[] newArr = new Object[0];
		int c = 0;
		boolean res = false;
		for (int i = 0; i < arr.length; i++) {
			if (!arr[i].equals(o)) {
				c++;
				newArr = Arrays.copyOf(newArr, c);
				newArr[c - 1] = arr[i];

			} else {
				size = size - 1;
				res = true;
			}
		}
		if (res) {
			arr = newArr;
		}
		return res;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean res = false;
		Iterator<?> iterator = c.iterator();
		while (iterator.hasNext())
			res = remove(iterator.next());
		return res;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		boolean res = false;
		for (int i = 0; i < size; i++) {
			if (!c.contains(arr[i])) {
				Object objToRemove = arr[i];
				res = this.remove(objToRemove);
				i--;
			}
		}

		return res;
	}

	@Override
	public Iterator<E> iterator() {
		return this.iterator;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object[] toArray() {
		return arr;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		T[] arrT = a;
		for (int i = 0; i < a.length; i++) {
			arrT[i] = (T) arr[i];
		}
		return arrT;
	}

	public class IteratorImplementation implements Iterator<E> {

		int pointerIndex = 0;

		@Override
		public boolean hasNext() {
			return pointerIndex != size;
		}

		@SuppressWarnings("unchecked")
		@Override
		public E next() {
			E elForReturn = null;
			if (pointerIndex >= size)
				throw new NoSuchElementException();
			if (hasNext()) {
				elForReturn = (E) arr[pointerIndex];
				pointerIndex = pointerIndex + 1;
			}
			return elForReturn;
		}

	}

}
