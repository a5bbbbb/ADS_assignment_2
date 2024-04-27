import datastructures.*;

import java.util.Scanner;

public class Main {

    private final static Scanner scan = new Scanner(System.in);
    private final static MyList<Integer> ls = new MyLinkedList<>();
    private final static MyMinHeap<Integer> hp = new MyMinHeap<>();
    private final static MyQueue<Integer> q = new MyQueue<>();
    private final static MyStack<Integer> st = new MyStack<>();


    private static void printArray(){
        System.out.println("size: " + ls.size());
        System.out.print("[");
        for(var i : ls)
            System.out.print(i + ", ");
        System.out.print("]");
        System.out.println();
    }

    private static void testAdd(){
        System.out.println("To add element to a position enter index and number. " +
                "\nTo skip -1.");
        int index = scan.nextInt();
        if(index == -1)return;
        Integer item = scan.nextInt();
        ls.add(index, item);
        printArray();
    }

    private static void testRemove() {
        System.out.println("To remove element enter index, or -1 to skip.");
        int index = scan.nextInt();
        if (index == -1) return;
        ls.remove(index);
        printArray();
    }

    private static void testSort(){
        System.out.println("To sort enter 1, otherwise 0.");
        if(scan.nextInt() == 0)return;
        ls.sort();
        printArray();
    }

    private static void testFind(){
        System.out.println("To find index of the element enter value, or 0 to skip.");
        int val = scan.nextInt();
        if(val == 0)return;
        System.out.println(ls.indexOf(val));
        printArray();
    }

    private static void testLastFind(){
        System.out.println("To find last index of the element enter value, or 0 to skip.");
        int val = scan.nextInt();
        if(val == 0)return;
        System.out.println(ls.lastIndexOf(val));
        printArray();
    }

    private static void testAddHeap(){
        System.out.println("To add element enter number. " +
                "\nTo skip -1.");
        int item = scan.nextInt();
        if(item == -1)return;
        hp.push(item);
    }

    private static void testGetHeap(){
        if(hp.size() != 0)
            System.out.println("Min in the MyMinHeap: " + hp.getTop());
        else
            System.out.println("MyMinHeap is empty.");
    }

    private static void testPopHeap(){
        System.out.println("To pop top of the heap enter 1, or 0 to skip.");
        int val = scan.nextInt();
        if(val == 0)return;
        hp.pop();
    }

    private static void testHeap(){
        hp.push(8);
        while(true){
            testGetHeap();
            testAddHeap();
            testPopHeap();
        }
    }

    private void testList(){
        ls.add(8);
        ls.add(4);
        ls.add(5);
        ls.add(8);
        ls.add(4);
        ls.add(2);
        ls.add(3);
        ls.add(6);
        ls.add(1023);
        ls.add(1);
        while(true){
            testAdd();
            testRemove();
            testSort();
            testFind();
            testLastFind();
        }
    }

    private static void testPushQ(){
        System.out.println("To add element enter number. " +
                "\nTo skip -1.");
        int item = scan.nextInt();
        if(item == -1)return;
        q.push(item);
    }

    private static void testPopQ(){
        System.out.println("To pop top of the heap enter 1, or 0 to skip.");
        int val = scan.nextInt();
        if(val == 0)return;
        q.pop();
    }
    private static void testGetQ(){
        if(q.size() > 0)
            System.out.println("Front elements of the queue is: " + q.getFront());
        else
            System.out.println("Queue is empty.");
    }

    private static void testQ(){
        q.push(0);
        q.push(1);
        q.push(2);
        q.push(3);
        q.push(4);
        q.push(5);
        while(true){
            testGetQ();
            testPushQ();
            testPopQ();
        }
    }

    private static void test(){
        //testHeap();
        testQ();
    }

    public static void main(String[] args){
        test();
    }
}
