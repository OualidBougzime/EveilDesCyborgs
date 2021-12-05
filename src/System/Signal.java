package System;

import java.util.List;

public class Signal {
    private final List<Recepteur> recepteur;
    private final List<Emetteur> emetteur;
    private final List<Sequence> sequence;

    public Signal(List<Recepteur> recepteur, List<Emetteur> emetteur, List<Sequence> sequence) {
        this.recepteur = recepteur;
        this.emetteur = emetteur;
        this.sequence = sequence;
    }
}
