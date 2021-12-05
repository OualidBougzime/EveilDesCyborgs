package System;

import java.util.List;

public class Mirroir {
    private final List<Type> type;
    private final List<Resonnance> resonnance;
    private final List<Signal> signal;

    public Mirroir(List<Type> type, List<Resonnance> resonnance, List<Signal> signal) {
        this.type = type;
        this.resonnance = resonnance;
        this.signal = signal;
    }
}
