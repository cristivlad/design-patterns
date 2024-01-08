package abstractClass;

import java.util.Objects;
import java.util.function.IntConsumer;

public interface IntIterator {
    boolean hasNext();

    int next();

    default void remove() {
        throw new UnsupportedOperationException("remove");
    }

    default void forEachRemaining(IntConsumer action) {
        Objects.requireNonNull(action);
        while (hasNext())
            action.accept(next());
    }
}