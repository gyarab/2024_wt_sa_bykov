package A;
public class Lokomotiva extends ElektrickySpotrebic {
    @Override
    public String delaZvuk() {
        return "hmmmmmmmiiiiiiiiiiiiiiiiii";
    }

    @Override
    public String getName() {
        return "Newag Griffin (PKPIC EU200)";
    }

    @Override
    public int getVoltage() {
        return 3000;
    }
}
