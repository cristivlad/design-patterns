package composite;

import java.util.Iterator;

public abstract class Contact {
    public void add(Contact contact) {
    }

    public void remove(Contact contact) {
    }

    public abstract void sendMail(String msg);

    public boolean isLeaf() {
        throw new UnsupportedOperationException("todo");
    }

    public Iterator<Contact> children() {
        throw new UnsupportedOperationException("todo");
    }
}