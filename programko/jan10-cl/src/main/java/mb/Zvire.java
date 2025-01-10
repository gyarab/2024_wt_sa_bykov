package mb;

public class Zvire {
    protected String name;    
    protected boolean alive;

    public Zvire(String name) {
        this.name = name;
        this.alive = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAlive() {
        return alive;
    }

    public String isAliveString() {
        return alive ? "alive" : "dead";        
    }

    public void kill() {
        this.alive = false;
    }  
    
    @Override
    public String toString() {
        return new StringBuilder().append("some animal").append(this.name).toString();
    }
}
