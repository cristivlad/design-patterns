package proxy.exercise2;

public class Swede extends Scandinavian {
    private final Lutefisk lutefisk = Proxies.virtual(Lutefisk.class, RealLutefisk::new);

    public void work() {
        System.out.println("Slaving away to pay my taxes (Jan-Nov)");
    }

    public void learn() {
        System.out.println("Going to Crete to attend Heinz's course");
    }

    public void celebrateChristmas() {
        lutefisk.eat();
    }

    public void entertain() {
        lutefisk.eat();
    }
}

