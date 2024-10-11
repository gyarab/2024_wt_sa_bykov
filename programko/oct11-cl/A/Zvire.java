package A;

public abstract class Zvire implements IDelaZvuk {
    public int silaZvuku() {
        return this.delaZvuk().length();
    }
};
