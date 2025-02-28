public class Fast {
    static final int SPEEDUP = 100;
    static final int STARTM = 10;

    static class SonicThread extends Thread {
        int minutesForKM;

        SonicThread(int minutesForKM, String name) {
            this.minutesForKM = minutesForKM;
            this.setName(name);
        }

        @Override
        public void run() {
            for(int i = STARTM; i > 0; i--) {
                System.out.println(Integer.toString( (STARTM-i)*minutesForKM)+" "+getName()+" at km"+Integer.toString(i));
                try {
                    Thread.sleep(1000*60*minutesForKM/SPEEDUP);
                }
                catch(Exception e) {}
            }
        }
    }
    public static void main(String[] args) {
        Thread t1 = new SonicThread(3, "Augustow");
        Thread t2 = new SonicThread(6, "Bydgoszcz");
        Thread t3 = new SonicThread(8, "Czestochowa");
        Thread t4 = new SonicThread(10, "Dabrowa Gornicza");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        boolean t1done = false;
        boolean t2done = false;
        boolean t3done = false;
        boolean t4done = false;
        do {
            if(!t1.isAlive() && !t1done) {
                t1done = true;
                System.out.println("Finished "+t1.getName());
            }
            if(!t2.isAlive() && !t2done) {
                t2done = true;
                System.out.println("Finished "+t2.getName());
            }
            if(!t3.isAlive() && !t3done) {
                t3done = true;
                System.out.println("Finished "+t3.getName());
            }
            if(!t4.isAlive() && !t4done) {
                t4done = true;
                System.out.println("Finished "+t4.getName());
            }
        }
        while(!(t1done && t2done && t3done && t4done));
    }
}
