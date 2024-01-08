package strategy.exercise2;

import java.util.stream.Stream;

public class ConsultingHouse {
    private final Programmer[] programmers;

    public ConsultingHouse(Programmer... programmers) {
        this.programmers = programmers.clone();
    }

    public Stream<Programmer> getProgrammersByRichestSmartest() {
        return Stream.of(programmers);
    }

    public Stream<Programmer> getProgrammersBySmartestRichest() {
        return Stream.of(programmers);
    }

    public Stream<Programmer> getProgrammersByName() {
        return Stream.of(programmers);
    }
}
