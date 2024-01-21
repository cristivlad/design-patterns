package iterator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//DON'T CHANGE
public class IteratorTest {
    @Test
    public void testIteratorMethods() throws Exception {
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
        assertEquals(90.0, ap.getTotal(), 0.01, "Total does not match up");

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
        assertEquals(45.0, ap.getTotal(), 0.01, "Total does not match up");
    }

    @Test
    public void testModifyingWhilstIterating() {
        final WalkingCollection<String> names =
                new WalkingCollection<>(new ArrayList<>());

        names.add("Maximilian");
        names.add("Constance");
        names.add("Priscilla");
        names.add("Evangeline");

        Processor<String> pp = s -> {
            if ("Priscilla".equals(s)) names.remove(s);
            return true;
        };
        try {
            names.iterate(pp);
            fail("We expected an IllegalMonitorStateException");
        } catch (IllegalMonitorStateException | IllegalStateException success) {
            // success
        }
    }

    @Test
    public void testReadWriteLocks() throws Exception {
        final WalkingCollection<Long> ages = new WalkingCollection<>(
                new ArrayList<>()
        );

        ages.add(10L);
        ages.add(35L);
        ages.add(12L);
        ages.add(33L);

        new Thread("slow iterating thread") {
            public void run() {
                // make a slow processor
                ages.iterate(o -> {
                    try {
                        System.out.println("Processing: " + o);
                        Thread.sleep(100);
                        System.out.println("Done: " + o);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    return true;
                });
            }
        }.start();

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        long time = System.currentTimeMillis();
        ages.add(32L);
        time = System.currentTimeMillis() - time;
        assertTrue(time > 300, "We were able to update whilst another processor " +
                               "was iterating: time=" + time);

        AddProcessor<Long> ap = new AddProcessor<>();
        ages.iterate(ap);
        assertEquals(122.0, ap.getTotal(), 0.01, "Total does not match up");
    }

    @Test
    public void testReadLocks() throws Exception {
        final WalkingCollection<Long> ages = new WalkingCollection<>(
                new ArrayList<>()
        );

        ages.add(10L);
        ages.add(35L);
        ages.add(12L);
        ages.add(33L);

        Thread[] iteratingThreads = new Thread[3];
        for (int i = 0; i < iteratingThreads.length; i++) {
            iteratingThreads[i] = new Thread("slow iterating thread " + i) {
                public void run() {
                    // make a slow processor
                    ages.iterate(o -> {
                        try {
                            System.out.println("Processing: " + o);
                            Thread.sleep(100);
                            System.out.println("Done: " + o);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        return true;
                    });
                }
            };
            iteratingThreads[i].start();
        }

        long time = System.currentTimeMillis();
        try {
            // wait for all the threads to stop
            for (Thread iteratingThread : iteratingThreads) {
                iteratingThread.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        time = System.currentTimeMillis() - time;
        assertTrue( time < 800, "We were not able to iterate concurrently: time=" + time);
    }

    @Test
    public void testTypeAnnotations() {
        // no annotations needed as this implementation deviates too much from
        // the standard pattern structure.
    }
}

