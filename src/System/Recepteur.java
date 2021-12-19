package System;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Recepteur {
    private final List<Signal> signalPossible = new ArrayList<>();
    private final List<Signal> signalReceivedThisSequence = new ArrayList<>();
    private final List<Signal> signalReceived = new ArrayList<>();
    private final int id;
    private final int x;
    private final int y;

    public Recepteur(Signal signal1, Signal signal2, Signal signal3, int id, int x, int y) {
        this.signalPossible.add(signal1);
        this.signalPossible.add(signal2);
        this.signalPossible.add(signal3);
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public void addSignalReceivedThisSequence(Signal signal) {
        signalReceivedThisSequence.add(signal);
    }

    public void clearSignalReceivedThisSequence() {
        for (Signal signal: this.signalReceivedThisSequence) {
            this.signalReceived.add(signal);
        }
        signalReceivedThisSequence.clear();
    }

    public void addSignalReceived(Signal signal) {
        signalReceived.add(signal);
    }

    public int getCordX() {
        return this.x;
    }

    public int getCordY() {
        return this.y;
    }

    public List<Signal> getSignalPossible() {
        return this.signalPossible;
    }

    public int getId() {
        return this.id;
    }

    public boolean checkPossibleSignal(Signal signal) {
        boolean isPossible = true;

        for (Signal s: this.signalPossible) {
            if (!Objects.equals(s.getOrientation(), s.getOrientation())) {
                isPossible = false;
                break;
            }
        }
        return isPossible;
    }

    public List<Signal> getSignalReceivedThisSequence() { return this.signalReceivedThisSequence; }

    public List<Signal> getSignalReceived() { return this.signalReceived; }
}
