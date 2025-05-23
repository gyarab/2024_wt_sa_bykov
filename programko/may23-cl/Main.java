public class Main {
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
                if(n == afterLast) throw new Exception("ID out of range");
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
    }

    public static void main(String[] args) throws Exception {
        DoubleLinkedList dll = new DoubleLinkedList();
        dll
                .addNew(1).addNew(3).addNew(5)
                .prependNew(2).prependNew(4).prependNew(6)
                .insertNew(19, 3).addNew(5).print().bubbleSortList().print();

        //long start = System.nanoTime();
        //for(int i = 0; i < 15; i++) {
        //    dll.addNew(i);
        //}
        //long end = System.nanoTime();
        //System.out.println(String.format("%d", (end - start)/1000000));

        //dll.print().printBack();
    }
}