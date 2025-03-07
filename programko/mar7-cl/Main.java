import java.util.concurrent.Semaphore;

class VelkePole {
    public static final int VP_SIZE = 1_000_000;

    private int data[];
    private Semaphore sems[];
    private int amountZero = 0;
    private int amountOne = 0;
    private int amountTwo = 0;

    VelkePole() {
        this.data = new int[VP_SIZE];
        this.sems = new Semaphore[VP_SIZE];
        for(int i = 0; i < this.sems.length; i++) {
            this.sems[i] = new Semaphore(1, true); //fair = FIFO
        }
    }

    int delkaPole() {
        return VP_SIZE;
    }

    /*
    void prictiJedna(int index) {
        this.data[index]++;
    }
    */

    /*
    synchronized void prictiJedna(int index) {
        //every thread waits until it can write here
        //slows down stuff A LOT
        //! critical section !

        //this code block allows using own object as lock i.e. synchronized(object)
        //synchronized(this) {
            this.data[index]++;
        //}
    }
    */

    void prictiJedna(int index) {
        try {
            this.sems[index].acquire();
            this.data[index]++;
            this.sems[index].release();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    //TODO AtomicInteger

    int secti() {
        int result = 0;
        for(int i : data) {
            result += i;
        }
        return result;
    }

    void calcOccurences() {
        this.amountZero = 0;
        this.amountOne = 0;
        this.amountTwo = 0;
        for(int i : data) {
            switch(i) {
                case(0): this.amountZero++; break;
                case(1): this.amountOne++; break;
                case(2): this.amountTwo++; break;
            }
        }
    }

    void printOccurences() {
        System.out.println(
            String.format("0: %dx (%f%%), 1: %dx (%f%%), 2 %dx (%f%%)",
            this.amountZero, (double)this.amountZero/VP_SIZE*100.0,
            this.amountOne, (double)this.amountOne/VP_SIZE*100.0, 
            this.amountTwo, (double)this.amountTwo/VP_SIZE*100.0
            )
        );
    }

    int getAmountZero() {
        return this.amountZero;
    }
    int getAmountOne() {
        return this.amountOne;
    } 
    int getAmountTwo() {
        return this.amountTwo;
    }
};

public class Main {
    static final int RUN_AMOUNT = 100;
    public static void main(String[] args) throws Exception {
        Tester t = new Tester();
        double averageZeroAmount = 0;
        double averageOneAmount = 0;
        double averageTwoAmount = 0;

        long start = System.nanoTime();

        for(int j = 0; j < RUN_AMOUNT; j++) {
            VelkePole p = new VelkePole();

            //TODO best solution - parcel this up so threads dont meet
            //t1 - every second number, odd
            //t2 - every second number, even
            //then we dont need synchro

            Thread t1 = new Thread(() -> {
                for(int i = 0; i < VelkePole.VP_SIZE; i++)
                    p.prictiJedna(i);
            });

            Thread t2 = new Thread(() -> {
                for(int i = 0; i < VelkePole.VP_SIZE; i++)
                    p.prictiJedna(i);
            });

            t1.start();
            t2.start();
            t1.join();
            t2.join();

            p.calcOccurences();
            averageZeroAmount += p.getAmountZero();
            averageOneAmount += p.getAmountOne();
            averageTwoAmount += p.getAmountTwo();

            t.beginAssertOutput();
            System.out.print(p.secti());
            if(!t.endAssertOutput(Integer.toString(VelkePole.VP_SIZE*2))) {
                p.printOccurences();
            };
            
        }

        long end = System.nanoTime();

        t.printResult();

        //percentage
        averageZeroAmount /= VelkePole.VP_SIZE*RUN_AMOUNT/100.0;
        averageOneAmount /= VelkePole.VP_SIZE*RUN_AMOUNT/100.0;
        averageTwoAmount /= VelkePole.VP_SIZE*RUN_AMOUNT/100.0;

        double seconds = (end - start)/1000000000.0;

        System.out.println(String.format("Avg 0s: %f%%, avg 1s: %f%%, avg 2s: %f%%\nExecuted in %fs", averageZeroAmount, averageOneAmount, averageTwoAmount, seconds));

        /*
        1 million
        nothing - 3.55s
        synchro - 5.79s
        semaphore - 5.04s
        */
    }
};