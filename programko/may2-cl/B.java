import java.util.LinkedList;
import java.util.ListIterator;
import java.util.concurrent.ThreadLocalRandom;

//linked list traversal
//check if next even -> skip him
//and so on and so on

/*
 * psuedocode
 *
 * Node preStart
 * preStart.next = start
 * while(true):
 *  	if(node.next == null) break //fictive element guarantees that at least 1 node exists!
 * 
 *      if(node.next.data is even):
 *           node.next = node.next.next
 *      else:
 *          node = node.next 
 * 
 * end = next
 */

 //TODO write actual custom linked list later

public class B {
    final static int ARRAY_AMOUNT = 200000;
    public static void main(String[] args) {
        LinkedList<Integer> ll = new LinkedList<Integer>();
        int[] al = new int[ARRAY_AMOUNT];
        int alSize = ARRAY_AMOUNT;
        int[] al2 = new int[ARRAY_AMOUNT];
        int alSize2 = ARRAY_AMOUNT;

        long startTime = System.currentTimeMillis();
        for(int i = 0; i < ARRAY_AMOUNT; i++) {
            int value = ThreadLocalRandom.current().nextInt();
            ll.add(value);
            al[i] = value;
            al2[i] = value;
        }
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("generation - %d ms", endTime - startTime));

        startTime = System.currentTimeMillis();
        for(int i = ARRAY_AMOUNT-1; i >= 0; i--) {
            if(al[i] % 2 == 0) {
                al[i] = al[alSize-1];
                al[alSize-1] = -1;
                alSize--;
            }
        }
        endTime = System.currentTimeMillis();
        System.out.println(String.format("list switching - %d ms", endTime - startTime));

        startTime = System.currentTimeMillis();
        for(int i = ARRAY_AMOUNT-1; i >= 0; i--) {
            if(al2[i] % 2 == 0) {
                for(int j = i; j < alSize2-2; j++) {
                    al2[j] = al2[j+1];
                }
                al2[alSize2-1] = -1;
                alSize2--;
            }
        }
        endTime = System.currentTimeMillis();
        System.out.println(String.format("list movement - %d ms", endTime - startTime));

        startTime = System.currentTimeMillis();
        //this does add the fictive "pre-start" element
        ListIterator<Integer> li = ll.listIterator();
        while(li.hasNext()) {
            int i = li.next();
            if(i % 2 == 0) {
                li.remove();
            }
        }
        endTime = System.currentTimeMillis();
        System.out.println(String.format("linked list - %d ms", endTime - startTime));

        //linked list slower than list switching but keeps order of elements!

        /*
        for(int i : ll) System.out.print(String.format("%d ", i));
        System.out.println("\n");
        for(int i : al) System.out.print(String.format("%d ", i));
        System.out.println("\n");
        for(int i : al2) System.out.print(String.format("%d ", i));
        System.out.println("\n");
        */
    }
}
