import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by konstantin on 14.11.2015.
 */
public class MyCollection<T> implements Collection<T> {

    private static final int INITIAL_SIZE = 10;
    private int size;
    private T[] item;

    public MyCollection(int currentSize) throws Exception {
        if (currentSize < 0) {
            throw new Exception(String.format("Illegal size: %d", currentSize));
        }
        this.item = (T[]) new Object[currentSize];
    }

    public MyCollection() throws Exception {
        this(INITIAL_SIZE);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        boolean result = false;
        for (int i = 0; i < size; i++) {
            if (item[i].equals(o))
                result = true;
        }
        return result;
    }

    @Override
    public Iterator iterator() {
        return new MyIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(item, size);
    }

    @Override
    public <T> T[] toArray(T[] t) {
        if (t.length < size)
            return (T[]) Arrays.copyOf(item, size, t.getClass());
        System.arraycopy(item, 0, t, 0, size);
        if (t.length > size)
            t[size] = null;
        return t;
    }

    @Override
    public boolean add(T t) {
        if (size == item.length)
            checkVolume(size + 1);
        item[size++] = t;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> t) {
        Object[] a = t.toArray();
        int numNew = a.length;
        checkVolume(size + numNew);
        System.arraycopy(a, 0, item, size, numNew);
        size += numNew;
        return numNew != 0;
    }

    private void checkVolume(int volume) {
        int current = item.length;
        if (volume > current) {
            T[] newData = (T[]) new Object[Math.max(current * 2, volume)];
            System.arraycopy(item, 0, newData, 0, current);
            item = newData;
        }
    }

    @Override
    public boolean remove(Object o) {
        boolean result = false;
        for (int i = 0; i < size; i++) {
            if (item[i].equals(o)) {
                result = true;
                System.arraycopy(item, i + 1, item, i, size - 1 - i);
                item[size - 1] = null;
                size--;
                break;
            }
        }
        return result;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            item[i] = null;
        }
        this.size = 0;
    }

    @Override
    public boolean retainAll(Collection c) {
        boolean result = true;
        for (Object element : c) {
            for (int i = 0; i < item.length; i++) {
                if (item[i] != null) {
                    if (!item[i].equals(element)) {
                        this.remove(element);
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public boolean removeAll(Collection c) {
        boolean result = true;
        for (Object obj : c) {
            if (!this.remove(obj)) {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean containsAll(Collection c) {
        boolean result = true;
        for (Object o : c) {
            for (int i = 0; i < size; i++) {
                if (!this.contains(o)) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    private class MyIterator<T> implements Iterator<T> {

        private int cursor;

        public MyIterator() {
            this.cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return this.cursor < MyCollection.this.size;
        }

        @Override
        public T next() {
            if (this.hasNext()) {
                T current = (T) MyCollection.this.item[cursor++];
                return current;
            } else {
                throw new NoSuchElementException();
            }
        }
    }
}
