package datastructures;

public class MyMinHeap<T extends Comparable<T>>{

    private final MyArrayList<T> tree;

    public MyMinHeap() {
        tree = new MyArrayList<>();
        tree.add(null);
    }

    public int size() {
        return tree.size() - 1;
    }

    public boolean empty(){
        return size() == 0;
    }

    private void swap(int a, int b){
        T t = tree.get(a);
        tree.set(a, tree.get(b));
        tree.set(b, t);
    }

    private boolean cmp(T a, T b){
        return ((Comparable<T>)a).compareTo(b) > 0;
    }

    private int parentOf(int index){
        return index>>1;
    }

    private void traverseUp(int index){
        if(index <= 1)return;
        int to = parentOf(index);
        if(cmp(tree.get(to),tree.get(index))) {
            swap(to, index);
            traverseUp(to);
        }
    }

    private boolean checkIndex(int index){
        return 1 <= index && index <= size();
    }

    private int leftChildOf(int index){
        return index<<1;
    }

    private int rightChildOf(int index){
        return (index<<1)|1;
    }

    private void heapify(int index){
        if( ! checkIndex(index))
            return;
        if( ! (checkIndex(leftChildOf(index)) && cmp(tree.get(index), tree.get(leftChildOf(index))) ||
                checkIndex(rightChildOf(index)) && cmp(tree.get(index), tree.get(rightChildOf(index)))))
            return;
        if(checkIndex(rightChildOf(index))  && cmp(tree.get(leftChildOf(index)), tree.get(rightChildOf(index)))){
            swap(index, rightChildOf(index));
            heapify(rightChildOf(index));
        }
        else {
            swap(index, leftChildOf(index));
            heapify(leftChildOf(index));
        }
    }

    public void insert(T value){
        tree.add(value);
        traverseUp(size());
    }

    public T getMin() {
        checkIndexWithThrow(1);
        return tree.get(1);
    }

    private void checkIndexWithThrow(int index){
        if(!checkIndex(index))
            throw new RuntimeException("Heap is empty, or operation is performed on non-existent index.");
    }

    public T extractMin(){
        checkIndexWithThrow(size());
        var temp = getMin();
        swap(1, size());
        tree.removeLast();
        heapify(1);
        return temp;
    }
}
