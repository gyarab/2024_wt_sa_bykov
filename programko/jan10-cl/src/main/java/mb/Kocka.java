package mb;

public class Kocka extends Zvire {
    private int lifeAmount;

    public Kocka(String name) {
        super(name);
        this.lifeAmount = 9;
    }
    public Kocka(String name, int lifeAmount) {
        super(name);
        this.lifeAmount = lifeAmount;
    }

    public int getLifeAmount() {
        return lifeAmount;
    }

    public void setLifeAmount(int lifeAmount) {
        this.lifeAmount = lifeAmount;
        if(this.lifeAmount == 0) {
            this.kill();
        }
    }

    @Override
    public void kill() {
        this.lifeAmount = 0;
        super.kill();
    }

    @Override
    public String toString() {
        return new StringBuffer().append(this.isAliveString()).append(" Kocka ").append(this.name).append(" with ").append(this.lifeAmount).append(" lives").toString();
    }
};
