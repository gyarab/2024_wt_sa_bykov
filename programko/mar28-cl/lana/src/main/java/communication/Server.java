package communication;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.*;
import java.awt.image.*;

public class Server {
    static final int port = 10000;
    static final String html = """
<html>
    <head>
        <title>Cau lidi</title>
        <style>
            body {
                background-color: #aaaaaa;
                font-family: sans-serif;
                color: #ffffff;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <h1>Cau lidi!</h1>
        <p>
            Ony sou volby blyzko! Jdy hlasovat!
            <br>
            Ja vam jen chci rict ze nykdy neodstoupim, nykdy! Necht si to vsichni zapamtuji!
            <br>
            Muj cas je tedka:
            <b>%s</b>
        </p>
    </body>
</html>
            """;

    private static Color bgc = new Color(0, 0, 50);
	private static Color fgc = new Color(255, 255, 255);
	
	private static int sizemul = 80;

    public static BufferedImage drawImage() {
        BufferedImage image = new BufferedImage(1280, 720, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.setColor(bgc);
        g2.fillRect(0, 0, 1280, 720);
        g2.setFont(new Font("sans-serif", Font.BOLD, 80));

        //g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(fgc);
        g2.setStroke(new BasicStroke(25));
        g2.drawArc(-sizemul*2, sizemul*3, sizemul*40, sizemul*19, 90, 90);

        g2.setColor(bgc);
        g2.fillRect(0, (int)(7.5*sizemul), sizemul, sizemul);
        g2.setColor(fgc);
        g2.fillOval(0, (int)(7.5*sizemul), sizemul, sizemul);
        g2.setColor(bgc);
        g2.setFont(new Font("sans-serif", Font.PLAIN, 30));
        g2.drawString("P", 30, (int)(8*sizemul)+20);

        g2.setColor(fgc);
        g2.drawString("Florenc", 100, (int)(8*sizemul)+20);

        g2.setFont(new Font("sans-serif", Font.BOLD, 80));
        g2.setColor(fgc);
        g2.fillRect(0, 0, 170, 100);
        g2.setColor(bgc);
        g2.drawString("135", 0, 80);

        g2.setColor(fgc);
        g2.drawString("Chodov", 180, 80);

        g2.setFont(new Font("sans-serif", Font.PLAIN, 30));
        g2.setColor(fgc);
        g2.fillRect(0, 100, sizemul*16, 60);
        g2.setColor(bgc);
        g2.drawString("Pernerova - Náměstí Míru (A) - Ruská - Slavia - Spořilov - Roztyly (C) - Chodov (C)", 10, 140);
        return image;
    }
    

    public static byte[] makeHTTPResponse(int status, String contentType, byte[] content, int refreshAfter) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write("HTTP/1.1 ".getBytes());
        switch(status) {
            case(200):
                baos.write("200 OK".getBytes());
                break;
            case(404):
                baos.write("404 Not Found".getBytes());
                break;
            case(400):
                baos.write("400 Bad Request".getBytes());
                break;
            case(418):
                baos.write("I'm a teapot".getBytes());
                break;
        }

        baos.write("\r\nAccept-Encoding: identity\r\n".getBytes());
        if(refreshAfter != 0) {
            baos.write(String.format("Refresh: %d\r\n", refreshAfter).getBytes());
        }
        baos.write(String.format("Content-Type: %s\r\n", contentType).getBytes());
        baos.write("Content-Length: ".getBytes());
        baos.write(Integer.toString(content.length).getBytes());
        baos.write("\r\n\r\n".getBytes());

        baos.write(content);

        return baos.toByteArray();
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        ServerSocket ss = new ServerSocket(port);

        //server loop
        while(true) {
            if(System.in.available() > 0) {
                if(sc.nextLine().equals("END")) break;
            }

            System.out.println(String.format("Listening at %d", port));
            Socket s = ss.accept();
            System.out.println("Accepted");
            BufferedReader rd = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream wr = new DataOutputStream(s.getOutputStream());

            try {
                while(true) {
                    String str = rd.readLine();
                    System.out.println(String.format("INPUT: %s", str));                
                    if(str.isBlank()) {
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ImageIO.write(drawImage(), "PNG", baos);
                        byte[] imageData = baos.toByteArray();

                        byte[] res = makeHTTPResponse(200, "image/png", imageData, 1);
                        wr.write(res);
                        System.out.println(String.format("OUTPUT: %s", new String(res)));
                        wr.flush();
                        break;
                    }
                }
            }
            catch(Exception e) {
                System.out.println(String.format("Error: %s", e.getMessage()));
            }
            finally {
                s.close();
                System.out.println("Closed");
            }
        }

        sc.close();
        ss.close();
    }
}