package composite;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

//DON'T CHANGE
public class CompositeTest {
    @Test
    public void testComplicatedTree() {
        Contact a = new Person("a@a.ws");
        Contact b = new Person("b@a.ws");
        Contact c = new Person("c@a.ws");
        Contact d = new Person("d@a.ws");
        Contact e = new Person("e@a.ws");
        Contact f = new Person("f@a.ws");
        Contact dl1 = new DistributionList();
        Contact dl2 = new DistributionList();
        Contact dl3 = new DistributionList();
        dl1.add(dl2);
        dl3.add(a);
        dl3.add(b);
        dl3.add(c);
        dl3.add(d);
        dl1.add(dl3);
        dl1.add(e);
        dl1.add(f);
        ContactIterator it;
        it = new ContactIterator(a);
        assertSame(a, it.next());
        assertFalse(it.hasNext());
        it = new ContactIterator(b);
        assertSame(b, it.next());
        assertFalse(it.hasNext());
        it = new ContactIterator(c);
        assertSame(c, it.next());
        assertFalse(it.hasNext());
        it = new ContactIterator(d);
        assertSame(d, it.next());
        assertFalse(it.hasNext());
        it = new ContactIterator(e);
        assertSame(e, it.next());
        assertFalse(it.hasNext());
        it = new ContactIterator(f);
        assertSame(f, it.next());
        assertFalse(it.hasNext());
        it = new ContactIterator(dl1);
        assertSame(a, it.next());
        assertSame(b, it.next());
        assertSame(c, it.next());
        assertSame(d, it.next());
        assertSame(e, it.next());
        assertSame(f, it.next());
        assertFalse(it.hasNext());
        it = new ContactIterator(dl2);
        assertTrue(!it.hasNext());
        it = new ContactIterator(dl3);
        assertSame(a, it.next());
        assertSame(b, it.next());
        assertSame(c, it.next());
        assertSame(d, it.next());
        assertFalse(it.hasNext());
    }

    @Test()
    public void testRootLeafRemove() {
        ContactIterator it = new ContactIterator(new Person("a@a.ws"));
        assertThrows(IllegalStateException.class, it::remove);
    }

    @Test
    public void testRemove() {
        Contact a = new Person("a@a.ws");
        Contact b = new Person("b@a.ws");
        Contact c = new Person("c@a.ws");
        Contact d = new Person("d@a.ws");
        Contact e = new Person("e@a.ws");
        Contact f = new Person("f@a.ws");
        Contact dl1 = new DistributionList();
        Contact dl2 = new DistributionList();
        Contact dl3 = new DistributionList();
        dl1.add(dl2);
        dl3.add(a);
        dl3.add(b);
        dl3.add(c);
        dl3.add(d);
        dl1.add(dl3);
        dl1.add(e);
        dl1.add(f);
        ContactIterator it;

        System.out.println("Removing c@c.ws");
        it = new ContactIterator(dl1);
        assertSame(a, it.next());
        assertSame(b, it.next());
        assertSame(c, it.next());
        it.remove();
        assertSame(d, it.next());
        assertSame(e, it.next());
        assertSame(f, it.next());
        assertFalse(it.hasNext());

        System.out.println("Removing f@f.ws");
        it = new ContactIterator(dl1);
        assertSame(a, it.next());
        assertSame(b, it.next());
        assertSame(d, it.next(), "c@c.ws was not removed from the composite tree");
        assertSame(e, it.next());
        assertSame(f, it.next());
        it.remove();
        assertFalse(it.hasNext());

        System.out.println("Removing a@a.ws");
        it = new ContactIterator(dl1);
        assertSame(a, it.next());
        it.remove();
        assertSame(b, it.next());
        assertSame(d, it.next());
        assertSame(e, it.next());
        assertFalse(it.hasNext(), "f@f.ws was not removed from the composite tree");

        System.out.println("Removing d@d.ws");
        it = new ContactIterator(dl1);
        assertSame(b, it.next(), "a@a.ws was not removed from the composite tree");
        assertSame(d, it.next());
        it.remove();
        assertSame(e, it.next());
        assertFalse(it.hasNext());

        System.out.println("Removing e@e.ws");
        it = new ContactIterator(dl1);
        assertSame(b, it.next());
        assertSame(e, it.next(), "d@d.ws was not removed from the composite tree");
        it.remove();
        assertFalse(it.hasNext());

        System.out.println("Removing b@b.ws");
        it = new ContactIterator(dl1);
        assertSame(b, it.next());
        it.remove();
        assertFalse(it.hasNext(), "e@e.ws was not removed from the composite tree");

        System.out.println("Empty ContactIterator");
        it = new ContactIterator(dl1);
        assertFalse(it.hasNext(), "b@b.ws was not removed from the composite tree");
    }


    @Test
    public void testContactHasDefaultsForLeaf() {
        Contact contact = new Contact() {
            public void sendMail(String msg) {
                // do nothing
            }
        };
        assertTrue(contact.isLeaf(), "Usually we make the Component have the defaults for all leaves");
        assertTrue(contact.children() instanceof NullIterator, "Usually we make the Component have the defaults for all leaves");
    }

    @Test
    public void testNullIteratorUsed() {
        Contact contact = new Contact() {
            public void sendMail(String msg) {
                // do nothing
            }
        };
        Iterator<Contact> it1 = contact.children();
        Iterator<Contact> it2 = new Person("bla@aol.com").children();
        assertSame(it1, it2, "Leaf iterators should give back the same shared instance");
    }

    @Test
    public void testDistributionList() {
        DistributionList list = new DistributionList();
        assertFalse(list.isLeaf(), "Even an empty DistributionList is a non-leaf");
        assertFalse(list.children() instanceof NullIterator, "DistributionList will never return a NullIterator");
    }
}