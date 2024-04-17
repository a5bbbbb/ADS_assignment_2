import java.util.Scanner;

public class Main {

    private final static Scanner scan = new Scanner(System.in);

    private final static MyLinkedList<Integer> ls = new MyLinkedList<>();

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
    private static void testRemove(){

        System.out.println("To remove element enter index, or -1 to skip.");

        int index = scan.nextInt();

        if(index == -1)return;

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

    private static void test(){

        ls.add(8);
        ls.add(4);
        ls.add(5);
        ls.add(8);
        ls.add(4);


        while(true){

            //testAdd();

            //testRemove();

            //testSort();

            //testFind();

            testLastFind();
        }
    }

    public static void main(String[] args){
        test();
    }
}
