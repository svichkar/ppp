package com.nixsolutions;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomCollection<E> implements Collection<E> {

	private E[] arrCollection;

	public CustomCollection(E[] arrObj) {
		this.arrCollection = arrObj;
	}

	@Override
	public boolean add(E inputValue) {
		E[] tmpArr = (E[]) Array.newInstance(arrCollection.getClass().getComponentType(), arrCollection.length + 1);
		System.arraycopy(arrCollection, 0, tmpArr, 0, arrCollection.length);
		tmpArr[arrCollection.length] = inputValue;
		arrCollection = tmpArr;
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> inputValue) {
		for (E item : inputValue) {
			this.add(item);
		}
		return true;
	}

	@Override
	public void clear() {
		arrCollection = (E[]) Array.newInstance(arrCollection.getClass().getComponentType(), 0);
	}

	@Override
	public boolean contains(Object inputValue) {
		for (Iterator<E> iterObj = iterator(); iterObj.hasNext();) {
			E customColValue = iterObj.next();
			if (inputValue == null ? customColValue == null : inputValue.equals(customColValue)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> inputValue) {
		Iterator<?> iterObj = inputValue.iterator();
		while (iterObj.hasNext()) {
			if (!contains(iterObj.next())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		return arrCollection.length == 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new CustomIterator<E>(arrCollection);
	}

	@Override
	public boolean remove(Object inputValue) {
		for (int i = 0; i < arrCollection.length; i++) {
			if (inputValue == null ? arrCollection[i] == null : inputValue.equals(arrCollection[i])) {
				E[] tmpArr = (E[]) Array.newInstance(arrCollection.getClass().getComponentType(),
						arrCollection.length - 1);
				System.arraycopy(arrCollection, 0, tmpArr, 0, i);
				System.arraycopy(arrCollection, i + 1, tmpArr, i, arrCollection.length - i - 1);
				arrCollection = tmpArr;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> inputValue) {
		boolean isChanged = false;
		for (Object item : inputValue) {
			if (contains(item)) {
				remove(item);
				isChanged = true;
			}
		}
		return isChanged;
	}

	@Override
	public boolean retainAll(Collection<?> inputValue) {
		boolean isChanged = false;
		int count = 0;
		for (Object item : inputValue) {
			if (contains(item)) {
				count++;
				isChanged = true;
			}
		}
		if (isChanged) {
			E[] tmpArr = (E[]) Array.newInstance(arrCollection.getClass().getComponentType(), count);
			count = 0;
			for (int i = 0; i < arrCollection.length; i++) {
				for (Object item : inputValue) {
					if (arrCollection[i] == item) {
						tmpArr[count++] = arrCollection[i];
						break;
					}
				}
			}
			arrCollection = tmpArr;
		}
		return isChanged;
	}

	@Override
	public int size() {
		return arrCollection.length;
	}

	@Override
	public Object[] toArray() {
		return arrCollection;
	}

	@Override
	public <T> T[] toArray(T[] inputValue) {
		if (inputValue.length < arrCollection.length) {
			return (T[]) Arrays.copyOf(arrCollection, arrCollection.length, inputValue.getClass());
		} else {
			System.arraycopy(arrCollection, 0, inputValue, 0, arrCollection.length);
			return inputValue;
		}
	}

	private class CustomIterator<T> implements Iterator<T> {

		private int index;
		private T[] arrObj;

		public CustomIterator(T[] arrObj) {
			this.index = 0;
			this.arrObj = arrObj;
		}

		@Override
		public boolean hasNext() {
			return index < arrObj.length;
		}

		@Override
		public T next() {
			if (hasNext()) {
				return arrObj[index++];
			} else {
				throw new NoSuchElementException();
			}
		}
	}

}
