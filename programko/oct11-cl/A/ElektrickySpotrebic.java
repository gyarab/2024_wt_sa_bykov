package A;
public abstract class ElektrickySpotrebic implements IDelaZvuk {
    public int silaZvuku() {
        return this.delaZvuk().length();
    }
    
    public boolean jeNaElektrinu() {
        return true;
    }
    public boolean jeNaBaterky() {
        return this.getVoltage() == 0 ? true : false; //pokud nebere napeti tak je na baterky i guess
    }

    public abstract String getName();
    public abstract int getVoltage();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: "+this.getName()+"\n").append("Uses batteries? "+this.jeNaBaterky()+"\n").append("Sound: "+this.delaZvuk()+"\n").append("Sound strength: ").append(this.silaZvuku());
        return sb.toString();
    }
}