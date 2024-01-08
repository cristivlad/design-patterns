package memento;

import java.util.function.Consumer;

public class HIVLab {
    public static void main(String... args) {
        LabRat mickey = new LabRat();
        while (true) {
            experiment(LabRat::feedDrugs, mickey);
            experiment(LabRat::blastWithRadar, mickey);
        }
    }

    private static void experiment(Consumer<LabRat> experiment, LabRat rat) {
        Memento memento = rat.createMemento();
        experiment.accept(rat);
        if (!rat.isAlive()) {
            rat.setMemento(memento);
        }
    }
}