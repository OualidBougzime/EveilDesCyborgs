import System.Miroir;
import System.Signal;

import java.util.ArrayList;
import java.util.List;

public class Chambre {
    private Miroir chamberMirror = null;
    private List<Signal> chamberSignals = new ArrayList<Signal>();

    public void setChamber(Miroir mirror) {
        this.chamberMirror = mirror;
    }

    public void setChamber(Signal signal) {
        this.chamberSignals.add(signal);
    }

    public List<Signal> getChamberSignals() {
        return this.chamberSignals;
    }

    public Miroir getChamberMirror() {
        return this.chamberMirror;
    }

    public void clearSignals() {
        this.chamberSignals.clear();
    }
}
