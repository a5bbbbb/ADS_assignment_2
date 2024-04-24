package datastructures;

import java.util.Iterator;

public final class MyQueue<T> {

    MyList<T> ls;

    private class MyIterator<T> implements Iterator<T> {

        int index = 0;

        Object[] a;

        public MyIterator(Object[] a) {
            this.a = a;
        }

        @Override
        public boolean hasNext() {
            return a.length != index;
        }

        @Override
        public T next() {
            var data = a[index];
            index++;
            return (T) data;
        }

    }

    public MyQueue() {

        this.ls = new MyLinkedList<>();

    }

    public void push(T el){

        ls.addLast(el);

    }

    public T getFront(){

        return ls.getFirst();

    }

    public T pop(){

        T el = ls.getFirst();

        ls.removeFirst();

        return el;

    }

    public Iterator<T> iterator() {
        return new MyIterator<T>(ls.toArray());
    }

}
