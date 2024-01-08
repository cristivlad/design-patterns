package abstractClass;

import java.util.Objects;
import java.util.stream.Collectors;

public abstract class
AbstractIntCollection
        implements IntCollection {
    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(int o) {
        IntIterator it = iterator();
        while (it.hasNext())
            if (o == it.next())
                return true;
        return false;
    }

    public int[] toArray() {
        return stream().toArray();
    }

    public boolean add(int e) {
        throw new UnsupportedOperationException();
    }

    public boolean remove(int o) {
        for (IntIterator it = iterator(); it.hasNext(); ) {
            int next = it.next();
            if (o == next) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public boolean containsAll(IntCollection c) {
        for (IntIterator it = c.iterator(); it.hasNext(); ) {
            if (!contains(it.next()))
                return false;
        }
        return true;
    }

    public boolean addAll(IntCollection c) {
        boolean modified = false;
        for (IntIterator it = c.iterator(); it.hasNext(); ) {
            int e = it.next();
            if (add(e))
                modified = true;
        }
        return modified;
    }

    public boolean removeAll(IntCollection c) {
        Objects.requireNonNull(c);
        boolean modified = false;
        IntIterator it = iterator();
        while (it.hasNext()) {
            if (c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    public boolean retainAll(IntCollection c) {
        Objects.requireNonNull(c);
        boolean modified = false;
        IntIterator it = iterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    public void clear() {
        IntIterator it = iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    public final String toString() {
        return stream().mapToObj(Integer::toString)
                .collect(Collectors.joining(", ", "[", "]"));
    }
}
