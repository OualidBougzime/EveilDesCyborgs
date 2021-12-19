package System;

import java.util.List;

public class Sequence {
    private final List<Signal> signals;
    private final List<Resonnance> resonnance;

    public Sequence(List<Signal> signals, List<Resonnance> resonnance) {
        this.signals = signals;
        this.resonnance = resonnance;
    }
}
