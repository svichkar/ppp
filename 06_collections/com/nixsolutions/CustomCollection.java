package com.nixsolutions;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * the class implements Collection interface with addition of custom method
 * get()
 *
 * @param <E>
 * @author evgeniykryzhanovskiy
 */
public class CustomCollection<E> implements Collection<E> {
	/**
	 * size of the array (how many elements it contains)
	 */
	private int size = 0;

	/**
	 * initial start size of the collection
	 */
	private static final int DEFAULT = 1;

	/**
	 * array of the collection, storage place
	 */
	private E[] storage;

	public CustomCollection() {
		storage = (E[]) new Object[DEFAULT];
	}

	/**
	 * returns an instance of element by its index in the collection
	 *
	 * @param index
	 *            an index of the element
	 * @return the element
	 */
	public E get(int index) {
		return storage[index];
	}

	@Override
	public boolean add(E e) {
		boolean status = false;
		if (size < storage.length) {
			storage[size++] = e;
			status = true;
		} else {
			storage = Arrays.copyOf(storage, storage.length + 1);
			storage[size++] = e;
			status = true;
		}
		return status;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		int preState = storage.length;
		for (E e : c) {
			this.add(e);
		}
		return preState != storage.length;
	}

	@Override
	public void clear() {
		storage = (E[]) new Object[DEFAULT];
		size = 0;
	}

	@Override
	public boolean contains(Object o) {
		boolean status = false;
		if (o == null) {
			for (int i = 0; i < storage.length; i++) {
				if (storage[i] == o) {
					status = true;
					break;
				}
			}
		} else {
			for (int i = 0; i < storage.length; i++) {
				if (o.equals(storage[i])) {
					status = true;
					break;
				}
			}
		}
		return status;
	}

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

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterations();
	}

	@Override
	public boolean remove(Object o) {
		boolean status = false;
		if (o == null) {
			for (int i = 0; i < storage.length; i++) {
				if (storage[i] == null) {
					System.arraycopy(storage, i + 1, storage, i,
							storage.length - i - 1);
					storage = Arrays.copyOfRange(storage, 0,
							storage.length - 1);
					size--;
					status = true;
					break;
				}
			}
		} else {
			for (int i = 0; i < storage.length; i++) {
				if (o.equals(storage[i])) {
					System.arraycopy(storage, i + 1, storage, i,
							storage.length - i - 1);
					storage = Arrays.copyOfRange(storage, 0,
							storage.length - 1);
					size--;
					status = true;
					break;
				}
			}

		}
		return status;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		int initialSize = storage.length;
		for (Object object : c) {
			while (this.remove(object)) {
				// do not need a code here
			}
		}
		return initialSize != storage.length;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		int initialSize = storage.length;
		for (Object item : storage) {
			if (!c.contains(item)) {
				while (this.remove(item)) {
					// do not need a code here
				}
			}
		}
		return initialSize != storage.length;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		System.arraycopy(storage, 0, array, 0, size);
		return array;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		if (a.length < storage.length) {
			T[] fitSize = (T[]) Array
					.newInstance(a.getClass().getComponentType(), size);
			System.arraycopy(storage, 0, fitSize, 0, storage.length);
			return fitSize;
		} else if (a.length > storage.length) {
			System.arraycopy(storage, 0, a, 0, storage.length);
			a[storage.length + 1] = null;
			return a;
		} else {
			System.arraycopy(storage, 0, a, 0, storage.length);
			return a;
		}

	}

	private class Iterations implements Iterator<E> {
		int index = 0;

		@Override
		public boolean hasNext() {
			return index < storage.length;
		}

		@Override
		public E next() {
			if (this.hasNext()) {
				return (E) storage[index++];
			} else {
				return null;
			}
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
