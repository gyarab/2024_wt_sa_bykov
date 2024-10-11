package A;
public class Elektrobus extends ElektrickySpotrebic {
    @Override
    public String delaZvuk() {
        return "vroom";
    }

    @Override
    public String getName() {
        return "Škoda 36 BB E'City 12";
    }

    @Override
    public int getVoltage() {
        return 0; //baterky
    }
}
