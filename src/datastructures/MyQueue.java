package datastructures;

import java.util.Iterator;

public final class MyQueue<T> implements Iterable<T>{

    MyLinkedList<T> ls;

    public MyQueue() {
        this.ls = new MyLinkedList<>();
    }

    public void enqueue(T el){
        ls.addLast(el);
    }

    private boolean checkIndex(int index){
        return 0 <= index && index < size();
    }

    private void checkIndexWithThrow(int index){
        if(!checkIndex(index))
            throw new RuntimeException("Queue is empty, or operation is performed on non-existent index.");
    }

    public T peek(){
        checkIndexWithThrow(0);
        return ls.getFirst();
    }

    public T dequeue(){
        checkIndexWithThrow(0);
        T el = ls.getFirst();
        ls.removeFirst();
        return el;
    }

    public int size(){
        return ls.size();
    }

    public boolean empty(){
        return size() == 0;
    }

    public Iterator<T> iterator() {
        return ls.iterator();
    }

}
