package iterator;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class WalkingCollection<E>
        extends AbstractCollection<E> {
    private final Collection<E> wrappedCollection;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public WalkingCollection(Collection<E> wrappedCollection) {
        this.wrappedCollection = wrappedCollection;
    }

    public void iterate(Processor<? super E> processor) {
        // lock using a ReadLock, then iterate through collection calling
        // processor.process(e) on each element
        lock.readLock().lock();
        try {
            for (E e : wrappedCollection) {
                if (!processor.process(e))
                    return;
            }
        } finally {
            lock.readLock().unlock();
        }
//        throw new UnsupportedOperationException("please implement");
    }

    public Iterator<E> iterator() {
        // this method should not really be called by users anymore, instead
        // they should call the iterate(Processor) method

        // return an iterator that locks a ReadLock on hasNext() and next()
        // and a WriteLock on remove().

        // Should throw IllegalMonitorStateException if a thread tries to call
        // remove() during iteration.
        lock.readLock().lock();
        try {
            Iterator<E> iterator = wrappedCollection.iterator();
            return new Iterator<>() {

                @Override
                public boolean hasNext() {
                    lock.readLock().lock();
                    try {
                        return iterator.hasNext();
                    } finally {
                        lock.readLock().unlock();
                    }
                }

                @Override
                public E next() {
                    lock.readLock().lock();
                    try {
                        return iterator.next();
                    } finally {
                        lock.readLock().unlock();
                    }
                }

                @Override
                public void remove() {
                    checkReadLockStatus();
                    lock.writeLock().lock();
                    try {
                        iterator.remove();
                    } finally {
                        lock.writeLock().unlock();
                    }
                }
            };
        } finally {
            lock.readLock().unlock();
        }
//        throw new UnsupportedOperationException("please implement");
    }

    private void checkReadLockStatus() {
        if
        (lock.getReadHoldCount() > 0)
            throw new IllegalMonitorStateException("ReadLock is held");
    }

    public int size() {
        // the size of the wrappedCollection, but wrapped with a ReadLock
        lock.readLock().lock();
        try {
            return wrappedCollection.size();
        } finally {
            lock.readLock().unlock();
        }
//        throw new UnsupportedOperationException("please implement");
    }

    public boolean add(E e) {
        // adds the element to the collection, throws IllegalMonitorStateException
        // if that thread is busy iterating
        checkReadLockStatus();
        lock.writeLock().lock();
        try {
            return wrappedCollection.add(e);
        } finally {
            lock.writeLock().unlock();
        }
//        throw new UnsupportedOperationException("please implement");
    }
}