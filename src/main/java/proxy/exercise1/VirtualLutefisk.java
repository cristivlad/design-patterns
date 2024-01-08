package proxy.exercise1;

public class VirtualLutefisk implements Lutefisk {
    private Lutefisk realLutefisk;

    private Lutefisk realLuteFisk() {
        if (realLutefisk == null) {
            realLutefisk = new RealLutefisk();
        }
        return realLutefisk;
    }

    @Override
    public void eat() {
        realLuteFisk().eat();
    }
}
