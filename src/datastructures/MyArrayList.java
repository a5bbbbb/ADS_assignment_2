package datastructures;

import java.util.Iterator;

public final class MyArrayList<T> implements MyList<T>{
    private Object[] arr = new Object[5];

    private int size = 0, actual_size = 5;

    /*
     * This class allows iteration through the list.
     */
    private class MyIterator<T> implements Iterator<T>{

        int index = 0;

        MyList<T> a;

        public MyIterator(MyList<T> a) {
            this.a = a;
        }

        @Override
        public boolean hasNext() {
            return a.size() != index;
        }

        @Override
        public T next() {
            var data = a.get(index);
            index++;
            return data;
        }
    }

    private void increaseBuffer(){
        actual_size *= 2;

        Object[] newArr = new Object[actual_size];

        for(int i = 0 ; i < size; i++)

            newArr[i] = arr[i];

        arr = newArr;
    }

    private void checkBuffer(int target_size) {

        if (target_size > actual_size) {

            increaseBuffer();

            checkBuffer(target_size);

        }
    }

    private void checkIndex(int index){
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException("MyArray object is empty, or index is out of bounds.");
    }

    @Override
    public void add(T item) {
        checkBuffer(size + 1);
        arr[size] = item;
        size++;
    }

    @Override
    public void set(int index, T item) {
        checkIndex(index);
        arr[index] = item;
    }

    @Override
    public void add(int index, T item) {

        if(index == size){

            add(item);

            return;

        }

        checkIndex(index);

        checkBuffer(size+1);

        for(int i = size; i > index; i--)

            arr[i] = arr[i-1];

        arr[index] = item;

        size++;

    }

    @Override
    public void addFirst(T item) {
        add(0, item);
    }

    @Override
    public void addLast(T item) {
        add(item);
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) arr[index];
    }

    @Override
    public T getFirst() {
        return get(0);
    }

    @Override
    public T getLast() {
        return get(size-1);
    }

    @Override
    public void remove(int index) {

        checkIndex(index);

        for(int i = index; i < size - 1; i ++)
            arr[i] = arr[i + 1];

        size--;
    }

    @Override
    public void removeFirst() {
        remove(0);
    }

    @Override
    public void removeLast() {
        remove(size-1);
    }

    private void bubbleSort(T[]  arr){

        T temp;

        boolean check_again = true;

        while(check_again){

            check_again = false;

            for(int j = 0; j < size - 1; j++) {

                Comparable<T> t1 = (Comparable<T>) arr[j];

                temp = arr[j+1];

                if (t1.compareTo((T) temp) > 0) {

                    check_again = true;

                    temp = arr[j];

                    arr[j] = arr[j + 1];

                    arr[j + 1] = temp;

                }
            }
        }
    }

    @Override
    public void sort() {
        bubbleSort((T[])arr);
    }

    @Override
    public int indexOf(Object object) {

        for(int i = 0; i < size; i++)

            if(object == get(i))

                return i;

        return -1;

    }

    @Override
    public int lastIndexOf(Object object) {

        for(int i = size - 1; i >= 0 ; i--)

            if(object == get(i))

                return i;

        return -1;
    }

    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public Object[] toArray() {

        Object[] nArr = new Object[size];

        for(int i = 0; i < size; i++)

            nArr[i] = arr[i];

        return nArr;

    }

    @Override
    public void clear() {
        arr = new Object[5];
        actual_size = 5;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator<T>(this);
    }
}
