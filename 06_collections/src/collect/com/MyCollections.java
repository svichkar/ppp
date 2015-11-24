package collect.com;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;

public class MyCollections<E> implements java.util.Collection<E> {

	private E[] arrCollect;
	int size;
	int part = 6;

	public MyCollections() {
		clear();
	}

	@Override
	public boolean add(E e) {

		if (size < arrCollect.length) {
			arrCollect[size] = e;
			size++;
			return true;
		} else {
			arrCollect = Arrays.copyOf(arrCollect, part + part);
			arrCollect[size] = e;
			size++;
			return true;
		}
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {

		for (E element : c) {
			add(element);
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {

		arrCollect = (E[]) new Object[part];
		size = 0;
	}

	@Override
	public boolean contains(Object o) {

		for (E element : arrCollect) {
			if (Objects.equals(element, o)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		int i = 0;
		for (Object element : c) {
			if (this.contains(element)) {
				i++;
			}
		}
		if (i == c.size()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		return size() > 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new MyIterator<E>();
	}

	@Override
	public boolean remove(Object o) {

		int index = findIndex(o);
		if (index != size + 1) {
			System.arraycopy(arrCollect, index + 1, arrCollect, index, size - index);
			arrCollect[size] = null;
			size--;
			return true;
		} else {

			return false;
		}
	}

	public int findIndex(Object o) {
		int i = 0;
		int index = size + 1;
		for (E element : arrCollect) {
			if (Objects.equals(element, o)) {
				index = i;
			}
			i++;
		}
		return index;
	}

	@Override
	public boolean removeAll(Collection<?> c) {

		boolean res = false;
		for (Object element : c) {
			res = remove(element);
		}
		return res;
	}

	@Override
	public boolean retainAll(Collection<?> c) {

		boolean res = false;
		for (E element : arrCollect) {
			if (!c.contains(element)) {
				res = remove(element);
			}
		}
		return res;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		E[] toArrayCollect = (E[]) new Object[part];
		System.arraycopy(arrCollect, 0, toArrayCollect, 0, size);
		return toArrayCollect;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		if (a.length < size) {
			System.arraycopy(arrCollect, 0, a, 0, size);
			return a;
		} else {
			a = (T[]) new Object[size];
			System.arraycopy(arrCollect, 0, a, 0, size);
			return a;
		}
	}

	private class MyIterator<E> implements Iterator<E> {

		private int currentIndex = 0;

		public Iterator<E> iterator() {
			return new MyIterator<E>();
		}

		@Override
		public boolean hasNext() {

			return currentIndex < MyCollections.this.size() && MyCollections.this.arrCollect[currentIndex] != null;

		}

		@Override
		public E next() {
			return (E) MyCollections.this.arrCollect[currentIndex++];

		}

	}

}