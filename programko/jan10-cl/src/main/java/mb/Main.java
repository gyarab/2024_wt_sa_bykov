package mb;

import java.util.*;
import java.io.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Main {
    private static final int CAT_AMOUNT = 10;
    private static final int HAD_AMOUNT = 10;

    public static void main(String[] args) throws Exception {
        Random r = new Random();
        ArrayList<Zvire> l = new ArrayList<Zvire>();

        for(int i = 0; i < CAT_AMOUNT; i++) {
            l.add(new Had(UUID.randomUUID().toString(), r.nextInt(100)));
        }

        for(int i = 0; i < HAD_AMOUNT; i++) {
            l.add(new Kocka(UUID.randomUUID().toString(), r.nextInt(100)));
        }

        for(int i = 0; i < HAD_AMOUNT+CAT_AMOUNT; i += 2) {
            l.get(i).kill();
        }

        for(Zvire z : l) {
            System.out.println(z);
        }     

        //JSON
        ObjectMapper om = new ObjectMapper(); // jackson lib
        om.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        om.enable(SerializationFeature.INDENT_OUTPUT);
        String json = om.writeValueAsString(l.get(0));
        System.out.println(json);

        //XML
        XmlMapper xm = new XmlMapper();
        xm.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        xm.enable(SerializationFeature.INDENT_OUTPUT);
        String xml = xm.writeValueAsString(l.get(0));
        System.out.println(xml);

        //write to file(s)
        File xf = new File("file.xml");
        xm.writeValue(xf, l.get(0));
        File xj = new File("file.json");
        om.writeValue(xj, l.get(0));
    }
}
