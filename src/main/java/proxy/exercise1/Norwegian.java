package proxy.exercise1;

public class Norwegian extends Scandinavian {
    private Lutefisk lutefisk = new VirtualLutefisk();

    public void work() {
        System.out.println("Working hard whilst it is dark outside.");
    }

    public void learn() {
        System.out.println("Going to Crete to attend Heinz's course");
    }

    public void celebrateChristmas() {
        lutefisk.eat();
    }

    public void entertain() {
        System.out.println("Chasing reindeer");
    }
}
