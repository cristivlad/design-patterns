package abstractClass;

public interface IntListIterator extends IntIterator {
    boolean hasNext();

    int next();

    boolean hasPrevious();

    int previous();

    int nextIndex();

    int previousIndex();

    void remove();

    void set(int e);

    void add(int e);
}
