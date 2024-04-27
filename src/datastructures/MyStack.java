package datastructures;

import java.util.Iterator;

public final class MyStack<T> implements Iterable<T> {

    MyLinkedList<T> ls;

    public MyStack() {
        this.ls = new MyLinkedList<>();
    }

    private boolean checkIndex(int index){
        return 0 <= index && index < size();
    }

    private void checkIndexWithThrow(int index){
        if(!checkIndex(index))
            throw new RuntimeException("Stack is empty, or operation is performed on non-existent index.");
    }

    public void push(T el){
        ls.addLast(el);
    }

    public T peek(){
        checkIndexWithThrow(size()-1);
        return ls.getLast();
    }

    public T pop(){
        checkIndexWithThrow(size()-1);
        T el = ls.getLast();
        ls.removeLast();
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

