//https://stackoverflow.com/questions/4183408/redirect-stdout-to-a-string-in-java

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Tester {
    private ByteArrayOutputStream baos;

    private int success;
    private int all;

    public Tester() {
        this.success = 0;
        this.all = 0;
    }

    public void beginAssertOutput() {
        this.baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
    }
    public boolean endAssertOutput(String expected) throws Exception {
        boolean retval = true;
        String recieved = this.baos.toString("utf-8");
        recieved = recieved.trim(); //https://stackoverflow.com/questions/7454330/how-to-remove-newlines-from-beginning-and-end-of-a-string
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

        if(expected.compareTo(recieved) != 0) {
            System.out.println(String.format("Test failed: Expected output: %s; got %s", expected, recieved));
            retval = false;
        }
        else {
            System.out.println(String.format("Test suceeded. Value is %s", recieved));
            this.success++;
        }

        this.baos.close();
        this.all++;

        return retval;
    }

    public void printResult() {
        System.out.println(String.format("%d/%d (%f%%) tests succeeded", this.success, this.all, (double)this.success/(double)this.all*100.0));
    }
}
