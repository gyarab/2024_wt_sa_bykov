public class Main {
    public static void main(String[] args) {
        for (int i = 2; i < 10; i++) {
            final int startNum = i;
            new Thread(() -> {
                for (int j = 0; j <= 100; j++)
                    System.out.println(String.format("%d:%d", startNum, j*startNum));
            }).start();
        }
        
    }
};