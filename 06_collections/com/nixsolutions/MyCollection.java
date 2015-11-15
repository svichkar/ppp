package com.nixsolutions;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Sergey on 13.11.2015.
 */
public class MyCollection implements Collection {
    private Object[] array = null;
    private Object[] temporaryArray = null;
    private boolean flag = false;
    private MyIterator iterator = null;
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
                iterator = null;
                while (flag == false || this.iterator().hasNext()) {
                    if (o.equals(this.iterator().next())) {
                        flag = true;
                    }
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
        if (iterator == null) {
                iterator = new MyIterator();
        }
        return iterator;
    }

    @Override
    public Object[] toArray() {
        if (!this.isEmpty()) {
            Object[] temp = new Object[this.size()];
            int i = 0;
            iterator = null;
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
            System.arraycopy(array, 0, temporaryArray, 0, array.length);
            temporaryArray[array.length] = o;
            array = temporaryArray;
        } else {
            array = new Object[]{o};
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (!this.isEmpty()) {
            if (this.contains(o)) {
                int position = 0;
                for (int i = 0; i <array.length ; i++) {
                    if (o.equals(array[i])) {
                        position = i;
                    }
                }
                temporaryArray = new Object[array.length - 1];
                System.arraycopy(array, 0, temporaryArray, 0, position);
                System.arraycopy(array, position + 1, temporaryArray, position, array.length - 1);
                array = temporaryArray;
                return true;
            } else {
                return false;
            }
        } else {
            throw new NullPointerException("Collection is empty");
        }
    }

    @Override
    public boolean addAll(Collection c) {
        if (!c.isEmpty()) {
            for (int i = 0; i <c.size() ; i++) {
                this.add(c.iterator().next());
            }
        } else {
            throw new NullPointerException("Input collection is empty");
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
        if (!c.isEmpty()) {
            while (c.iterator().hasNext()) {
                if (!this.contains(c.iterator().next())) {
                    flag = false;
                }
            }
        } else {
            throw new NullPointerException("Input collection is empty");
        }
        return flag;
    }
//__________________**************What the fuck?
    @Override
    public Object[] toArray(Object[] a) {
        if (!this.isEmpty()) {
            if (a.length < this.size()) {
                a = new Object[this.size()];
                int i = 0;
                iterator = null;
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

    public Object getElementAt (int position) {
        if (position < this.size()) {
            return array[position];
        } else {
            throw new NoSuchElementException("No such element in collection");
        }
    }

    private class MyIterator implements  Iterator {
        private int counter;
        public MyIterator() {
            counter = 0;
        }

        @Override
        public boolean hasNext() {
            return (counter < MyCollection.this.size());
        }

        @Override
        public Object next() throws NoSuchElementException {
            if (this.hasNext()) {
                Object temp = MyCollection.this.getElementAt(counter);
                counter++;
                return temp;
            } else {
                throw new NoSuchElementException("No such element in collection");
            }
        }

        @Override
        public void remove() throws UnsupportedOperationException, IllegalStateException {
            MyCollection.this.remove(MyCollection.this.getElementAt(counter));
        }
    }
}
