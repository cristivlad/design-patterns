package iterator;

import java.util.ArrayList;

//DON'T CHANGE
public class WalkingCollectionExample {
    public static void main(String... args) {
        WalkingCollection<Long> ages = new WalkingCollection<>(
                new ArrayList<>()
        );

        ages.add(10L);
        ages.add(35L);
        ages.add(12L);
        ages.add(33L);

        PrintProcessor pp = new PrintProcessor();
        ages.iterate(pp);

        AddProcessor<Long> ap = new AddProcessor<>();
        ages.iterate(ap);
        System.out.println("ap.getTotal() = " + ap.getTotal());

        // composite
        System.out.println("Testing Composite");
        ap.reset();

        CompositeProcessor<Long> composite =
                new CompositeProcessor<>();
        composite.add(new Processor<>() {
            private long previous = Long.MIN_VALUE;

            public boolean process(Long current) {
                boolean result = current >= previous;
                previous = current;
                return result;
            }
        });
        composite.add(ap);
        composite.add(pp);
        ages.iterate(composite);
        System.out.println("ap.getTotal() = " + ap.getTotal());
    }
}