package C;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Linka> prahaTramvaje = new ArrayList<Linka>();
        prahaTramvaje.add(new Linka(1, 12, 60));
        prahaTramvaje.add(new Linka(2, 13, 30));
        prahaTramvaje.add(new Linka(20, 14, 60));
        prahaTramvaje.add(new Linka(26, 14, 60));

        prahaTramvaje.sort(null);
        System.out.println(prahaTramvaje);
    }
}
