package com.nixsolutions;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.swing.text.AbstractDocument.LeafElement;

public class CustomCollection implements Collection {

	private Object[] elements;

	public CustomCollection() {
		elements = new Object[0];
	}

	@Override
	public boolean add(Object e) {
		try {
			Object[] temp = elements.clone();
			elements = new Object[temp.length + 1];
			for (int i = 0; i < temp.length; i++) {
				elements[i] = temp[i];
			}
			elements[elements.length - 1] = e;
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	@Override
	public boolean addAll(Collection c) {
		for (Object obj : c) {
			add(obj);
		}
		return true;
	}

	@Override
	public void clear() {
		elements = new Object[0];
	}

	@Override
	public boolean contains(Object o) {
		for (Object element : elements) {
			if (element.equals(o)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection c) {
		int counter = 0;
		for (Object obj : c) {
			if (contains(obj)) {
				counter++;
			}
		}
		if (c.size() == counter) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		if (elements.length != 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Iterator iterator() {
		return new CustomIterator();
	}

	@Override
	public boolean remove(Object o) {
		Object[] temp = elements.clone();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].equals(o)) {
				try {
					elements = new Object[temp.length - 1];
					System.arraycopy(temp, 0, elements, 0, i);
					System.arraycopy(temp, i + 1, elements, i, elements.length - i);
					return true;
				} catch (Exception ex) {
					return false;
				}
			}
		}
		return false;
	}

	@Override
	public boolean removeAll(Collection c) {
		for (Object obj : c) {
			remove(obj);
		}
		return true;
	}

	@Override
	public boolean retainAll(Collection c) {
		boolean result = false;
		for (Object obj : elements) {
			if (!c.contains(obj)) {
				remove(obj);
				result = true;
			}
		} 
		return result;
	}

	@Override
	public int size() {
		return elements.length;
	}

	@Override
	public Object[] toArray() {
		return elements;
	}

	@Override
	public Object[] toArray(Object[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	class CustomIterator implements Iterator {
		private int index = 0;

		@Override
		public boolean hasNext() {
			if (index < elements.length) {
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			Object toReturn = null;
			if (index == elements.length) {
				throw new NoSuchElementException();
			}
			toReturn = elements[index];
			index++;
			return toReturn;
		}
	}

}
