package abstractClass;

import java.util.Objects;
import java.util.Spliterator;
import java.util.function.IntConsumer;

public interface IntIterable {
    IntIterator iterator();

    default void forEach(IntConsumer action) {
        Objects.requireNonNull(action);
        for (IntIterator it = iterator(); it.hasNext(); ) {
            action.accept(it.next());
        }
    }

    default Spliterator.OfInt spliterator() {
        return new IntIteratorSpliterator(iterator(), 0);
    }
}
