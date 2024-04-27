package datastructures;

import java.util.Iterator;


/*
*
* Doubly linked list
* Supports iteration
* Implements datastructures.MyList interface
 */
public final class MyLinkedList<T> implements MyList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    /*
    * Every Node keeps only its value and links to the next and to the previous nodes.
     */
    private static class Node<T> {

        private T data;
        private Node<T> next;
        private Node<T> prev;

        public Node(T data) {
            this.data = data;
            next = null;
            prev = null;
        }

    }
    /*
    * This class allows iteration through the linked list
    * from the head to the tail.
     */
    private class MyIterator<T> implements Iterator<T>{
        Node<T> head;

        public MyIterator(Node<T> head) {
            this.head = head;
        }
        @Override
        public boolean hasNext() {
            return head != null;
        }
        @Override
        public T next() {
            var data = head.data;
            head = head.next;
            return data;
        }
    }

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /*
    * adds items to the end of the linked list.
    * Time complexity: O(1)
    * @param item the item to be added.
     */
    @Override
    public void add(T item) {
        Node<T> newNode = new Node<>(item);
        if(head == null) {
            head = newNode;
        }
        else{
            makeLink(tail, newNode);
        }
        tail = newNode;
        size++;
    }

    /*
    * Sets the value to the element in the linked list.
    * Time complexity: O(n) where n is the size of the linked list.
    * @param index the index at which value must be set to node.
    * @param the value.
     */
    @Override
    public void set(int index, T item) {
        checkIndex(index);
        if(index == 0)
            head.data = item;
        else{
            Node<T> currentNode = head;
            for(int i = 0; i < index; i++)
                currentNode = currentNode.next;
            currentNode.data = item;
        }
    }

    /*
    * Inserts a node with value in the index.
     * Time complexity: O(n) where n is the size of the linked list.
     * @param index the index at which a new node must be inserted.
     * @param value the value of the new node.
     */
    @Override
    public void add(int index, T item) {
        if(index == size){
            add(item);
            return;
        }
        checkIndex(index);
        Node<T> newNode = new Node<>(item);
        if(index == 0) {
            makeLink(newNode, head);
            head = newNode;
        }
        else{
            Node<T> currentNode = head;
            for(int i = 0; i < index-1; i++)
                currentNode = currentNode.next;
            newNode.prev = currentNode;
            newNode.next = currentNode.next;
            currentNode.next = newNode;
            newNode.next.prev = newNode;
        }
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

    /*
    * helped method. Helps to determine whether the index is withing the linked list.
     */
    private void checkIndex(int index){
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index is not correct, or the linked list is empty.");
    }

    /*
     * returns item at an index.
     * Time complexity: O(n) where n is the size of the linked list.
     * @param index the index.
     * @return the item.
     */
    @Override
    public T get(int index) {
        checkIndex(index);
        if(index == 0)
            return head.data;
        else{
            Node<T> currentNode = head;
            for(int i = 0; i < index; i++)
                currentNode = currentNode.next;
            return currentNode.data;
        }
    }

    @Override
    public T getFirst() {
        return get(0);
    }

    @Override
    public T getLast() {
        return get(size - 1);
    }

    /*
     * removes a node at the index.
     * Time complexity: O(n) where n is the size of the linked list.
     * @param index the index.
     */
    @Override
    public void remove(int index) {
        checkIndex(index);
        if (index == 0){
            removeFirst();
        }
        else if (index == size - 1) {
            removeLast();
        }
        else{
            Node<T> currentNode = head;
            for(int i = 0; i < index - 1; i++)
                currentNode = currentNode.next;
            currentNode.next = currentNode.next.next;
            if(currentNode.next != null)
                currentNode.next.prev = currentNode;
        }
        size--;
    }

    /*
     * removes the first node at the index.
     * Time complexity: O(1) where n is the size of the linked list.
     */
    @Override
    public void removeFirst() {
        checkIndex(0);
        head = head.next;
        if(head != null)
            head.prev = null;
        size--;
    }

    /*
     * removes the last node at the index.
     * Time complexity: O(1) where n is the size of the linked list.
     */
    @Override
    public void removeLast() {
        checkIndex(size-1);
        tail = tail.prev;
        tail.next = null;
        size--;
    }

    private void makeLink(Node<T> a, Node<T> b){
        if(a != null)
            a.next = b;
        if(b != null)
           b.prev = a;
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

    /*
     * sorts the linked list by converting it to an ordinary list sorting it with bubble sort,
     * and appending the resulting list back to the linked list.
     * Time complexity: O(n*n) where n is the size of the linked list.
     */
    private void bubbleSort(){
        T[] a = (T[]) toArray();
        bubbleSort(a);
        clear();
        for(var i : a)
            add(i);
    }

    @Override
    public void sort() {
        if(size == 0)
            return;
        bubbleSort();
    }

    /*
    * liner search through the linked list. Returns first index of the node with the provided value.
    * Time complexity: O(n) where n is the size of the linked list.
    * @param value the value index of which needs to be found.
    * @return the index.
     */
    @Override
    public int indexOf(Object object) {
        Node<T> currentNode = head;
        for(int i = 0; i < size; i++) {
            if(object == currentNode.data)
                return i;
            currentNode = currentNode.next;
        }
        return -1;
    }

    /*
     * liner search through the linked list. Returns last index of the node with the provided value.
     * Time complexity: O(n) where n is the size of the linked list.
     * @param value the value index of which needs to be found.
     * @return the index.
     */
    @Override
    public int lastIndexOf(Object object) {
        Node<T> currentNode = tail;
        for(int i = size - 1; i >= 0; i--) {
            if(object == currentNode.data)
                return i;
            currentNode = currentNode.prev;
        }
        return -1;
    }

    /*
    * Checks whether an item is present in the linked list.
    * Time complexity: O(n)
    * @param object the object for which an index needs to be found.
    * @return the index.
     */
    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    /*
     * Makes a copy of values of the linked list in an array.
     * Time complexity: O(n)
     * @return the array.
     */
    @Override
    public Object[] toArray() {
        Object[]  toArr = new Object[size];
        Node<T> currentNode = head;
        for(int i = 0; i < size; i++) {
            toArr[i] = currentNode.data;
            currentNode = currentNode.next;
        }
        return toArr;
    }

    /*
     * Makes a copy of values of the linked list in an array.
     * Time complexity: O(1)
     * @return the array.
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>(head);
    }
}
