package mb;

public class Had extends Zvire {
    private int length;

    public Had(String name, int length) {
        super(name);
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return new StringBuffer().append(this.isAliveString()).append(" Had ").append(this.name).append(" of length ").append(this.length).toString();
    }
};