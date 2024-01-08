package memento;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class LabRat {
    private double cd4Ratio = 0.5;
    private boolean alive = true;

    public void blastWithRadar() {
        if (!alive)
            throw new IllegalStateException("lab rat is dead");
        cd4Ratio /= ThreadLocalRandom.current().nextDouble();
        cd4Ratio -= Math.floor(cd4Ratio);
        checkPulse();
    }

    public void feedDrugs() {
        if (!alive)
            throw new IllegalStateException("lab rat is dead");
        cd4Ratio *= ThreadLocalRandom.current().nextDouble();
        cd4Ratio -= Math.floor(cd4Ratio);
        checkPulse();
    }

    private void checkPulse() {
        if (cd4Ratio < 0.1) {
            alive = false;
        }
        System.out.printf(Locale.US, "Lab rat ha%s CD4 ratio of %.2f%n",
                (alive ? "s" : "d"), cd4Ratio);
    }

    public boolean isAlive() {
        return alive;
    }

    public Memento createMemento() {
        return new MementoImpl(this);
    }

    public void setMemento(Memento memento) {
        MementoImpl mementoImpl = (MementoImpl) memento;
        if (this != mementoImpl.originator.get()) throw new IllegalArgumentException("Memento not created by this object");
        this.cd4Ratio = mementoImpl.cd4Ratio;
        this.alive = mementoImpl.alive;
    }

    private static class MementoImpl implements Memento {
        private final double cd4Ratio;
        private final boolean alive;
        private final Reference<LabRat> originator;

        public MementoImpl(LabRat labRat) {
            this.cd4Ratio = labRat.cd4Ratio;
            this.alive = labRat.alive;
            this.originator = new WeakReference<>(labRat);
        }
    }
}