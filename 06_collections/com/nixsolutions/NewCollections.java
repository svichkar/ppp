import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by sobolenko on 2/1/2016.
 */
public class NewCollections<E> implements Collection<E> {
    Object[] initArray = new Object[0];
    Object[] resultArray = {};
    public int size;

    public NewCollections() {
    }

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
        return null;
    }

    public Object[] toArray() {
        return new Object[0];
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
        return true;
    }

    public boolean remove(Object o) {
        size();
        Object[] tempArray = new Object[resultArray.length - 1];
        boolean result = false;
        int it = 0;
        int cap = 0;
        if(!contains(o))
        {
            return false;
        }
        for (Object obj : resultArray) {
            if (obj.equals(o)) {
                for (Object object : resultArray) {
                    if (cap != it) {
                        tempArray[cap] = object;
                        result = true;
                        cap++;
                    } else {
                        it = 0;
                    }
                }
            }
            it++;
        }
        resultArray = Arrays.copyOf(tempArray, tempArray.length);
        return result;
    }

    public boolean addAll(Collection c) {
        return false;
    }

    public void clear() {

    }

    public boolean retainAll(Collection c) {
        return false;
    }

    public boolean removeAll(Collection c) {
        return false;
    }

    public boolean containsAll(Collection c) {
        return false;
    }

    public <T> T[] toArray(T[] a) {
        T[] array = (T[]) new Object[1];
        return array;
    }
}
