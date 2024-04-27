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

    private void swap(int a, int b){
        T t = tree.get(a);
        tree.set(a, tree.get(b));
        tree.set(b, t);
    }

    private boolean cmp(T a, T b){
        return ((Comparable<T>)a).compareTo(b) > 0;
    }

    private void updateUpwards(int index){
        if(index <= 1)return;
        int to = index>>1;
        if(cmp(tree.get(to),tree.get(index))) {
            swap(to, index);
            updateUpwards(to);
        }
    }

    private boolean checkIndex(int index){
        return 1 <= index && index <= size();
    }

    private void updateDownwards(int index){
        if( ! checkIndex(index))
            return;
        if( ! (checkIndex(index<<1) && cmp(tree.get(index), tree.get(index<<1)) ||
                checkIndex((index<<1)|1) && cmp(tree.get(index), tree.get((index<<1)|1))))
            return;
        if(checkIndex((index<<1)|1)  && cmp(tree.get(index<<1), tree.get((index<<1)|1))){
            swap(index, (index<<1)|1);
            updateDownwards((index<<1)|1);
        }
        else {
            swap(index, index << 1);
            updateDownwards(index << 1);
        }
    }

    public void push(T value){
        tree.add(value);
        updateUpwards(size());
    }

    public T getTop() {
        checkIndexWithThrow(1);
        return tree.get(1);
    }

    private void checkIndexWithThrow(int index){
        if(!checkIndex(index))
            throw new RuntimeException("Heap is empty, or operation is performed on non-existent index.");
    }

    public void pop(){
        checkIndexWithThrow(size());
        swap(1, size());
        tree.removeLast();
        updateDownwards(1);
    }
}
