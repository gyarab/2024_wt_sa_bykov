public class Hydra {
    //static class so fits in single file
    static class Runner implements Runnable {
        private int head = 0;

        //when reaches 8 make no more threads
        static final int HYDRA_HEAD_CHOP_AMOUNT = 8;

        Runner(int head) {
            this.head = head;
        }

        @Override
        public void run() {
            System.out.println(this.head);

            if(this.head == HYDRA_HEAD_CHOP_AMOUNT) return;

            Thread t1 = new Thread(new Runner(this.head+1));
            Thread t2 = new Thread(new Runner(this.head+1));
            t1.start();
            t2.start();
        }
    };

    public static void main(String[] args) {
        Thread t = new Thread(new Runner(1));
        t.start();
    }
};