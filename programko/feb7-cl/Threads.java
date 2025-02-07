import java.util.*;

public class Threads {
    static List<Thread> threadList;

    static final int sleepTime = 500;

    static class MyRun implements Runnable {
        String name;
        String value;

        public MyRun(String name, String value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public void run() {
            try {
                for(int i = 0; i < value.length(); i++) {
                    System.out.println(String.format("Th%s: %c", name, value.charAt(i)));
                    Thread.sleep(sleepTime);
                }
            }
            catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
    };

    public static void main(String[] args) throws Exception {
        String s = new String("123456789");
        String rs = new StringBuilder(s).reverse().toString();

        String[] words = {s, rs, "Konstantynopolitańczykowianeczka", "Pięćdziesięciogroszówka", "Dźwiękonaśladownictwo"};
        threadList = new ArrayList<Thread>();

        for(int i = 0; i < words.length; i++) {
            threadList.add(new Thread(new MyRun(Integer.toString(i), words[i])));
            threadList.getLast().start();
        }
    }
}
