import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class FixedImpl {
    public static class Node {
        String value;
        Node next;
        Node prev;

        Node(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public static class DoubleLinkedList {
        Node beforeFirst;
        Node afterLast;

        DoubleLinkedList() {
            this.beforeFirst = new Node("");
            this.afterLast = new Node("");
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
        public DoubleLinkedList addNew(String value) {
            return this.add(new Node(value));
        }
        public DoubleLinkedList prepend(Node n) {
            n.next = beforeFirst.next;
            n.prev = beforeFirst;
            beforeFirst.next.prev = n;
            beforeFirst.next = n;
            return this;
        }
        public DoubleLinkedList prependNew(String value) {
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
        public DoubleLinkedList insertNew(String value, int id) throws Exception {
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

        //bubble sort by swapping value
        public DoubleLinkedList bubbleSortValue() {
            boolean swaps = true;
            while(swaps) {
                swaps = false;
                for (Node current = beforeFirst.next;;) {
                    if (current.next == afterLast) break;
                    if(current.next.value.compareTo(current.value) <= -1) {
                        swaps = true;
                        String temp = current.value;
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

                    if(current.next.value.compareTo(current.value) <= -1) {
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
                if (current.value.compareTo(current.next.value) >= 1) return false;
                current = current.next;
            }
            return true;
        }

        public static String randomString() {
            int len = ThreadLocalRandom.current().nextInt(0, 15);
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < len; i++) {
                sb.append(Character.toChars(ThreadLocalRandom.current().nextInt('0', 'z'+1)));
            }
            return sb.toString();
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
                            dll.addNew(randomString());
                            continue;
                        case(1):
                            dll.prependNew(randomString());
                            continue;
                        case(2):
                            dll.insertNew(
                                randomString(),
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
                dll.addNew(input);
            } catch(Exception e) {
                System.out.println("Invalid input, try again.");
                continue;
            }
        }
        sc.close();

        //dll.bubbleSortList().print().printBack();
        dll.bubbleSortList().print();
    }
}
