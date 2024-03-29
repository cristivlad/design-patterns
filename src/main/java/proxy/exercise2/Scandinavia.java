package proxy.exercise2;

import java.util.ArrayList;
import java.util.List;

//DON'T CHANGE
public class Scandinavia {
    private final List<Scandinavian> citizens = new ArrayList<>();

    public void addCitizen(Scandinavian per) {
        citizens.add(per);
    }

    public void normalTime() {
        for (Scandinavian s : citizens) {
            s.work();
            s.learn();
        }
    }

    public void christmasTime() {
        citizens.forEach(Scandinavian::celebrateChristmas);
    }
}
