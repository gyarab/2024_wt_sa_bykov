package pisemka;

import java.util.Scanner;

public class LinkedListExample {
    public static class ListNode {
        int value;
        ListNode next;

        public ListNode(int i) {
            this.value = i;
            this.next = null;
        }
    }
    public static class SingleLinkedList {
        ListNode beforeFirst;

        public SingleLinkedList() {
            this.beforeFirst = new ListNode(-1);
        }

        public void addFirstNode(ListNode n) {
            n.next = this.beforeFirst.next;
            this.beforeFirst.next = n;
        }
        public void addLastNode(ListNode n) {
            ListNode current = this.beforeFirst;
            while(current.next != null) {
                current = current.next;
            }
            current.next = n;
        }

        public void cycle() {
            ListNode current = this.beforeFirst;
            while(current.next != null) {
                current = current.next;
            }

            current.next = this.beforeFirst.next; //last to first
            this.beforeFirst = current; //before first is last
        }

        public void printList(int max) {
            ListNode current = this.beforeFirst;
            int i = 0;
            while(current.next != null) {
                current = current.next;
                System.out.print(current.value+" ");
                i++;
                if(max == i) return;
            }
            System.out.println();
        }

        public void decimate(int n) {
            ListNode current = this.beforeFirst.next;
            ListNode prev = this.beforeFirst;
            int i = 1;
            //if end of list (should not happen if cycle)
            while(current.next != null) {
                if(current.next == current) {
                    //last element (the last man standing)
                    System.out.print(current.value+" ");
                    break;
                }

               // System.out.println("id"+i+" is "+current.value);

                if(i % n == 0) {
                    System.out.println(current.value+" ");
                    prev.next = current.next;
                    current = current.next;
                   // System.out.println("prev "+prev.value+" next "+current.value);
                } else {
                    prev = current;
                    current = current.next;
                }

                i++;
            }
            System.out.println();
        }

        public boolean isEmpty() {
            return this.beforeFirst.next == null;
        }
    }

    static final int SLL_SIZE = 13;

    public static void main(String[] args) {

        SingleLinkedList sll = new SingleLinkedList();
        for(int i = 1; i < SLL_SIZE+1; i++) {
            sll.addLastNode(new ListNode(i));
        }
        sll.cycle();
        //sll.printList(SLL_SIZE*2);

        Scanner s = new Scanner(System.in);
        sll.decimate(s.nextInt()); //release the lions :)
        s.close();
    }
}