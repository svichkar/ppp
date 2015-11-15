package com.nixsolutions;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Sergey on 13.11.2015.
 */
public class MyCollection implements Collection {
    private Object[] array = null;
    private Object[] temporaryArray = null;
    private boolean flag = false;
    @Override
    public int size() throws NullPointerException {
        if (!this.isEmpty()) {
            return array.length;
        } else {
            throw new NullPointerException("Collection is empty");
        }
    }

    @Override
    public boolean isEmpty() {
        if (array == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean contains(Object o) throws ClassCastException, NullPointerException {
        flag = false;
        if (!this.isEmpty()) {
            try {
                int i = 0;
                while (flag == false || i < array.length) {
                    if (o.equals(array[i])) {
                        flag = true;
                    }
                    i++;
                }
            } catch (ClassCastException e) {
                throw e;
            }
        } else {
            throw new NullPointerException("Collection is empty");
        }
        return flag;
    }

    @Override
    public Iterator iterator() {
        return new MyIterator();
    }

    @Override
    public Object[] toArray() {
        if (!this.isEmpty()) {
            Object[] temp = new Object[this.size()];
            int i = 0;
            while (this.iterator().hasNext()) {
                temp[i] = this.iterator().next();
                i++;
            }
            return temp;
        } else {
            throw new NullPointerException("Collection is empty");
        }
    }

    @Override
    public boolean add(Object o) {
        if (!this.isEmpty()) {
            temporaryArray = new Object[array.length + 1];
            for (int i = 0; i <array.length ; i++) {
                temporaryArray[i] = array[i];
            }
            temporaryArray[array.length] = o;
            array = temporaryArray;
        } else {
            array = new Object[]{o};
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (this.contains(o)) {
            if (!this.isEmpty()) {
                temporaryArray = new Object[array.length - 1];
                int i = 0;
                while (!o.equals(array[i])) {
                    temporaryArray[i] = array[i];
                    i++;
                }
                for (int j = i + 1; j < array.length; j++, i++) {
                    temporaryArray[i] = array[j];
                }
                array = temporaryArray;
            } else {
                throw new NullPointerException("Collection is empty");
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addAll(Collection c) {
        if (!c.isEmpty()) {
            for (int i = 0; i <c.size() ; i++) {
                this.add(c.iterator().next());
            }
        } else {
            throw new NullPointerException("Added collection is empty");
        }
        return true;
    }

    @Override
    public void clear() {
        if (!this.isEmpty()) {
            array = null;
        } else {
            throw new NullPointerException("Collection is empty");
        }
    }

    @Override
    public boolean retainAll(Collection c) {
        flag = false;
        while (c.iterator().hasNext() && !flag) {
            if (this.contains(c.iterator().next())) {
                flag = true;
            }
        }
        if (flag) {
            for (int i = 0; i <this.size() ; i++) {
                while (c.iterator().hasNext()) {
                    if (!array[i].equals(c.iterator().next())) {
                        this.remove(array[i]);
                    }
                }
            }
        } else {
            this.clear();
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection c) {
        flag = false;
        while (c.iterator().hasNext() && !flag) {
            if (this.contains(c.iterator().next())) {
                flag = true;
            }
        }
        if (flag) {
            for (int i = 0; i <this.size() ; i++) {
                while (c.iterator().hasNext()) {
                    if (array[i].equals(c.iterator().next())) {
                        this.remove(array[i]);
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean containsAll(Collection c) {
        flag = true;
        while (c.iterator().hasNext()) {
            if (!this.contains(c.iterator().next())) {
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public Object[] toArray(Object[] a) {
        if (!this.isEmpty()) {
            if (a.length < this.size()) {
                a = new Object[this.size()];
                int i = 0;
                while (this.iterator().hasNext()) {
                    a[i] = this.iterator().next();
                    i++;
                }
            } else {
                for (int i = 0; i <a.length ; i++) {
                    if (this.iterator().hasNext()) {
                        a[i] = this.iterator().next();
                    } else {
                        a[i] = null;
                    }
                }
            }
            return a;
        } else {
            throw new NullPointerException("Collection is empty");
        }
    }
}
