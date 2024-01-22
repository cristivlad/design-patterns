package composite;

import java.util.Iterator;

public class ContactIterator implements Iterator<Contact> {
    public ContactIterator(Contact contact) {
        throw new UnsupportedOperationException("todo");
    }

    public boolean hasNext() {
        throw new UnsupportedOperationException("todo");
    }

    public Contact next() {
        throw new UnsupportedOperationException("todo");
    }

    /**
     * This should throw an IllegalStateException if the root node of the
     * ContactIterator is a leaf; otherwise it should remove the element from
     * the composite tree structure.
     */
    public void remove() {
        throw new UnsupportedOperationException("todo");
    }
}
