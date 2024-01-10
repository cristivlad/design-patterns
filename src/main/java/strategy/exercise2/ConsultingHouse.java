package strategy.exercise2;

import java.util.Comparator;
import java.util.stream.Stream;

public class ConsultingHouse {
    private final Programmer[] programmers;

    public ConsultingHouse(Programmer... programmers) {
        this.programmers = programmers.clone();
    }

    public Stream<Programmer> getProgrammersByRichestSmartest() {
        return Stream.of(programmers).sorted(Comparator.comparingDouble(Programmer::getSalary)
                .thenComparing(p -> p.getLanguages().size()).reversed()
                .thenComparing(Programmer::getName));
    }

    public Stream<Programmer> getProgrammersBySmartestRichest() {
        return Stream.of(programmers).sorted(Comparator.<Programmer>comparingInt(p -> p.getLanguages().size())
                .thenComparing(Programmer::getSalary).reversed()
                .thenComparing(Programmer::getName));
    }

    public Stream<Programmer> getProgrammersByName() {
        return Stream.of(programmers).sorted(Comparator.comparing(Programmer::getName));

    }
}
