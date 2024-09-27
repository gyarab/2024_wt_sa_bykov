package B;

public class Clovek extends Zvire {
    public Clovek() {
        super.runSpeed = 5;
        super.size = "velky";
    }

    public String delaZvuk() {
        return "Vysíláme jasný kulinářský signál: Segedín podle prof. Petra Fialy";
    }
}
