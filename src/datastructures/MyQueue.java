package datastructures;

import java.util.Iterator;

public final class MyQueue<T> implements Iterable<T>{
    MyLinkedList<T> ls;
    
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

    private boolean checkIndex(int index){
        return 0 <= index && index < size();
    }

    private void checkIndexWithThrow(int index){
        if(!checkIndex(index))
            throw new RuntimeException("Queue is empty, or operation is performed on non-existent index.");
    }

    public T getFront(){
        checkIndexWithThrow(0);
        return ls.getFirst();
    }

    public T pop(){
        checkIndexWithThrow(0);
        T el = ls.getFirst();
        ls.removeFirst();
        return el;
    }

    public int size(){
        return ls.size();
    }

    public Iterator<T> iterator() {
        return new MyIterator<T>(ls.toArray());
    }

}
