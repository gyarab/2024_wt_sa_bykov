package deadlock;
class Main {
    static class TridaA extends Thread {
        TridaA kamarad;
    
        public void run() {
            prvni();
        }
    
        synchronized void prvni() {
            System.out.println("prvni");
            kamarad.druha();
        }
    
        synchronized void druha() {
            System.out.println("druha");
        }
    }

    public static void main(String[] args) {
        //ReentrantLock rrl = new ReentrantLock();
        //ReadWriteLock rwl = new ReentrantReadWriteLock();

        TridaA a = new TridaA();
        TridaA b = new TridaA();

        a.kamarad = b;
        b.kamarad = a;

        a.start();
        System.out.println("ahoj"); //fixes issue
        b.start();
    }
}