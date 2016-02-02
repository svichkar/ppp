package nixsolutions;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Created by sobolenko on 2/1/2016.
 */
public class NewCollections<E> implements Collection<E> {
    Object[] initArray = new Object[0];
    Object[] resultArray = {};
    private int currentPosition = -1;
    public int size;

    public NewCollections() {
        clear();
    }

    /**
     *
     * @return
     */
    public int size() {
        double capasity = 0;
        for (Object obj : resultArray) {
            capasity++;
        }
        if (capasity > Integer.MAX_VALUE) {
            capasity = Integer.MAX_VALUE;
        } else {
            size = (int) capasity;
        }
        return (int) capasity;
    }

    public boolean isEmpty() {
        double capasity = 0;
        if (resultArray.length == 0) {
            return true;
        }
        return false;
    }

    public boolean contains(Object o) {
        for (Object obj : resultArray) {
            if (obj.equals(o)) {
                return true;
            }
        }
        return false;
    }

    public Iterator iterator() {
        return new NewIterator();
    }

    public Object[] toArray() {
        Object[] newArray = new Object[resultArray.length];
        System.arraycopy(resultArray, 0, newArray, 0, resultArray.length);
        return newArray;
    }

    public boolean add(Object o) {
        size();
        for (Object obj : initArray) {
            if (obj.equals(o)) {
                return false;
            }
        }
        resultArray = Arrays.copyOf(resultArray, resultArray.length + 1);
        resultArray[size] = o;
        size();
        return true;
    }

    public boolean remove(Object o) {
        size();
        Object[] tempArray = new Object[resultArray.length - 1];
        boolean result = false;
        boolean flag = false;
        int it = 0;
        int cap = 0;
        if (!contains(o)) {
            return false;
        }
        for (Object obj : resultArray) {
            if (obj.equals(o)) {
                for (Object object : resultArray) {
                    if (cap != it || flag) {
                        tempArray[cap] = object;
                        result = true;
                        cap++;
                    } else {
                        flag = true;
                    }
                }
            }
            it++;
        }
        resultArray = Arrays.copyOf(tempArray, tempArray.length);
        size();
        return result;
    }

    public boolean addAll(Collection c) {
        Object[] newArray = c.toArray();
        int oldHash = Arrays.hashCode(resultArray);
        int oldLength = resultArray.length;
        resultArray = Arrays.copyOf(resultArray, resultArray.length + newArray.length);
        System.arraycopy(newArray, 0, resultArray, oldLength, newArray.length);
        int newHash = Arrays.hashCode(resultArray);
        size();
        if (oldHash != newHash) {
            return true;
        }
        return false;
    }

    public void clear() {
        resultArray = Arrays.copyOf(resultArray, 0);
        size();
    }

    public boolean retainAll(Collection c) {
        Object[] array = toArray(resultArray);
        int oldHash = Arrays.hashCode(resultArray);
        for (int i = 0; i < size; i++) {
            if (c.contains(array[i])) {
                remove(array[i]);
            }
        }
        int newHash = Arrays.hashCode(resultArray);
        size();
        if (oldHash != newHash) {
            return true;
        }
        return false;
    }

    public boolean removeAll(Collection c) {
        Object[] newArray = c.toArray();
        int oldHash = Arrays.hashCode(resultArray);
        for (Object x : newArray) {
            remove(x);
        }
        size();
        int newHash = Arrays.hashCode(resultArray);
        if (oldHash != newHash) {
            return true;
        }
        return false;
    }

    public boolean containsAll(Collection c) {
        Object[] newArray = c.toArray();
        String result = "";
        for (Object obj : newArray) {
            for (Object ra : resultArray) {
                if (obj.equals(ra)) {
                    result += true + ",";
                    break;
                }
            }
        }
        String[] tempResult = result.split(",");
        if (tempResult.length < 3) {
            return false;
        }
        return true;
    }

    public <T> T[] toArray(T[] a) {
        T[] array = (T[]) new Object[resultArray.length];
        for (int i = 0; i < resultArray.length; i++) {
            array[i] = (T) resultArray[i];
        }
        return array;
    }

    //--------------------------------------------------------------
    public class NewIterator implements Iterator {

        public boolean hasNext() {
            if (currentPosition != size-1) {
                return true;
            }
            return false;
        }

        public Object next() {
            if (currentPosition >= size)
                throw new NoSuchElementException();
            currentPosition++;
            return resultArray[currentPosition];
        }

        public void remove() {
            if (currentPosition >= size)
                throw new NoSuchElementException();
            Object[] tempArray = new Object[resultArray.length - 1];
            boolean flag = false;
            int it = 0;
            int cap = 0;
            for (Object obj : resultArray) {
                if (obj.equals(resultArray[currentPosition])) {
                    for (Object object : resultArray) {
                        if (cap != it || flag) {
                            tempArray[cap] = object;
                            cap++;
                        } else {
                            flag = true;
                        }
                    }
                }
                it++;
            }
            resultArray = Arrays.copyOf(tempArray, tempArray.length);
            size();
        }
    }
}
