import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class FixedImpl {
    public static class Node {
        int value;
        Node next;
        Node prev;

        Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return Integer.toString(value);
        }
    }

    public static class DoubleLinkedList {
        Node beforeFirst;
        Node afterLast;

        DoubleLinkedList() {
            this.beforeFirst = new Node(0);
            this.afterLast = new Node(0);
            this.beforeFirst.next = this.afterLast;
            this.afterLast.prev = this.beforeFirst;
        }

        public DoubleLinkedList add(Node n) {
            n.prev = afterLast.prev;
            n.next = afterLast;
            afterLast.prev.next = n;
            afterLast.prev = n;
            return this;
        }
        public DoubleLinkedList addNew(int value) {
            return this.add(new Node(value));
        }
        public DoubleLinkedList prepend(Node n) {
            n.next = beforeFirst.next;
            n.prev = beforeFirst;
            beforeFirst.next.prev = n;
            beforeFirst.next = n;
            return this;
        }
        public DoubleLinkedList prependNew(int value) {
            return this.prepend(new Node(value));
        }
        public DoubleLinkedList insert(Node n, int id) throws Exception {
            int i = 0;
            Node t = beforeFirst;
            for(i = 0; i < id; i++) {
                if(t == afterLast) throw new Exception("ID out of range");
                t = t.next;
            }

            n.prev = t;
            n.next = t.next;
            t.next.prev = n;
            t.next = n;

            return this;
        }
        public DoubleLinkedList insertNew(int value, int id) throws Exception {
            return this.insert(new Node(value), id);
        }
        public DoubleLinkedList print() {
            for (Node current = beforeFirst;;) {
                if (current.next == afterLast) {
                    System.out.println();
                    return this;
                }
                System.out.print(current.next+" ");
                current = current.next;
            }
        }
        public DoubleLinkedList printBack() {
            for (Node current = afterLast;;) {
                if (current.prev == beforeFirst) {
                    System.out.println();
                    return this;
                }
                System.out.print(current.prev+" ");
                current = current.prev;
            }
        }

        public DoubleLinkedList removeEven() {
            for(Node current = beforeFirst.next;;) {
                if (current == afterLast) return this;

                if(current.value % 2 == 0) {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                current = current.next;
            }
        }
        //bubble sort by swapping value
        public DoubleLinkedList bubbleSortValue() {
            boolean swaps = true;
            while(swaps) {
                swaps = false;
                for (Node current = beforeFirst.next;;) {
                    if (current.next == afterLast) break;
                    if(current.next.value < current.value) {
                        swaps = true;
                        int temp = current.value;
                        current.value = current.next.value;
                        current.next.value = temp;
                    }
                    current = current.next;
                }
            }
            return this;
        }
        //bubble sort by swapping list
        public DoubleLinkedList bubbleSortList() {
            boolean swaps = true;
            while(swaps) {
                swaps = false;
                for (Node current = beforeFirst.next;;) {
                    if (current.next == afterLast || current == afterLast) break;

                    if(current.next.value < current.value) {
                        swaps = true;

                        Node s = current;
                        Node u = current.next;

                        s.prev.next = u;
                        u.prev = s.prev;
                        
                        s.next = u.next;
                        u.next.prev = s;

                        u.next = s;
                        s.prev = u;
                    }
                    current = current.next;
                }
            }
            return this;
        }
        public boolean isSorted() {
            for (Node current = beforeFirst.next;;) {
                if (current.next == afterLast) break;
                if (current.value > current.next.value) return false;
                current = current.next;
            }
            return true;
        }

        public static void selfTest(int testAmount, int listSize, boolean print) throws Exception {
            int success = 0;

            //random check
            int randomOutput = ThreadLocalRandom.current().nextInt(0, TEST_AMOUNT);
    
            long start = System.nanoTime();
            for(int i = 0; i < testAmount; i++) {
                DoubleLinkedList dll = new DoubleLinkedList();
        
                //automatic testing
                for(int j = 0; j < listSize; j++) {
                    switch(ThreadLocalRandom.current().nextInt(0, 3)) {
                        case(0):
                            dll.addNew(ThreadLocalRandom.current().nextInt(0, 99));
                            continue;
                        case(1):
                            dll.prependNew(ThreadLocalRandom.current().nextInt(0, 99));
                            continue;
                        case(2):
                            dll.insertNew(
                                ThreadLocalRandom.current().nextInt(0, 99),
                                0
                            );
                            continue;
                        default: continue; //not going to happen, checkstyle go away
                    }
                }
        
                dll.bubbleSortList();
                if(dll.isSorted()) success++;
                else {
                    System.out.print(String.format("List no. %d failed: ", i));
                    dll.print().printBack();
                    throw new Exception(String.format("List no. %d failed: ", i));
                }
    
                if(i == randomOutput && print) {
                    System.out.print(String.format("Random check for list no. %d: ", i));
                    dll.print().printBack();
                }
            }
    
            long end = System.nanoTime();
            if(print) {
                System.out.println(String.format("Tests took %d ms, successful: %d out of %d, percentage: %d%%", (end - start)/1000000, success, TEST_AMOUNT, success/TEST_AMOUNT * 100));
            }
        }
    }

    public static final int TEST_AMOUNT = 1000;
    public static final int LIST_SIZE = 500;

    public static void main(String[] args) throws Exception {
        DoubleLinkedList.selfTest(TEST_AMOUNT, LIST_SIZE, false);

        DoubleLinkedList dll = new DoubleLinkedList();
        Scanner sc = new Scanner(System.in);
        for(String input = sc.next(); !input.equals("END"); input = sc.next()) {
            try {
                dll.addNew(Integer.parseInt(input));
            } catch(Exception e) {
                System.out.println("Invalid input, try again.");
                continue;
            }
        }
        sc.close();

        dll.bubbleSortList().print().printBack();
    }
}
