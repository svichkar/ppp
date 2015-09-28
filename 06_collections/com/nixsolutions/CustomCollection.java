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
