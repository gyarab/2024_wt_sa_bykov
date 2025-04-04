import java.io.*;
import java.net.*;

public class Main {
    private static final String HTML_BASE = """
<!DOCTYPE html>
<html>
<head>
<title>Tablereturner 9999</title>
</head>
<body>
<table>
<tr>
<th>Klic</th>
<th>Hodnota</th>
</tr>
%s
</table>
</body>
</html>
            """;
    private static final String RESPONSE_BASE = """
HTTP/1.1 200 OK
Connection: close
Accept-Encoding: identity
Content-Type: text/html
Content-Length: %d

%s
            """;
    private static final String TABLE_KV_PAIR_ROW_BASE = """
<tr>
  <td><b>%s</b></td>
  <td><i>%s</i></td>
</tr>
            """;

    public static String makeResponse(String content) {
        return String.format(RESPONSE_BASE, content.length()+1, content);
    }

    public static String makePage(String content) {
        return String.format(HTML_BASE, content);
    }

    public static String makeTableRow(String httpKVPair) throws Exception {
        String[] kvArray = httpKVPair.split(": ");
        if (kvArray.length != 2)
            throw new Exception(String.format("KV pair \"%s\" invalid - length %d", httpKVPair, kvArray.length));
        return String.format(TABLE_KV_PAIR_ROW_BASE, kvArray[0], kvArray[1]);
    }

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(9999);
            System.out.println("Listening at 9999");
            Socket s = ss.accept();
            BufferedReader rd = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            try {
                String header = rd.readLine();
                System.out.println(header);
                StringBuilder sb = new StringBuilder();

                String str = new String();
                while (true) {
                    str = rd.readLine();
                    if (str.isEmpty() || str == null)
                        break; // new line only after header
                    sb.append(makeTableRow(str));
                }
                //System.out.println(makeResponse(makePage(sb.toString()))); //debug
                wr.write(makeResponse(makePage(sb.toString())));
                wr.flush();
            } catch (Exception e) {
                System.out.println(String.format("Error: %s", e.getMessage()));
            } finally {
                s.close();
                System.out.println("Closed");
            }
            ss.close();
        } catch (Exception e) {
            System.out.println(String.format("Load error: %s", e.getMessage()));
        }
    }
}