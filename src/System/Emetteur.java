package System;

import System.Orientation.Actif;
import System.Orientation.Neutre;
import System.Orientation.SemiActifDirectif;
import System.Orientation.SemiActifTransitif;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Emetteur {
    private List<Signal> signalPossible = new ArrayList<>();
    private Signal signalToSend = null;
    private int id;

    public Emetteur(Signal signal1, Signal signal2, Signal signal3, int id) {
        this.signalPossible.add(signal1);
        this.signalPossible.add(signal2);
        this.signalPossible.add(signal3);
        this.id = id;
    }

    public void setupSignal(Signal signal) {
        boolean isPossible = false;
        for (Signal s: this.signalPossible
             ) {
            if ((Objects.equals(s.getOrientation(), signal.getOrientation()))) {
                isPossible = true;
                break;
            }
        }
        if (isPossible)
            this.signalToSend = signal;
        else
            System.out.println("Le signal: " + signal.getOrientation() + " ne peut pas être pris en charge par l'émetteur: " + this.id);
    }

    public List<Integer> sendSignal(List<Recepteur> recepteurs, int matrixSize) {
        List<Integer> cords = new ArrayList<>();

        cords.add(-1);
        cords.add(-1);

        if (this.signalToSend == null)
            return cords;
        if (this.id == 0 && Objects.equals(this.signalToSend.getOrientation(), "SAT-")) {
            recepteurs.get(39).addSignalReceivedThisSequence(this.signalToSend);
            return cords;
        }
        if (this.id == 9 && Objects.equals(this.signalToSend.getOrientation(), "SAD+")) {
            recepteurs.get(10).addSignalReceivedThisSequence(this.signalToSend);
            return cords;
        }
        if (this.id == 10 && Objects.equals(this.signalToSend.getOrientation(), "SAD-")) {
            recepteurs.get(9).addSignalReceivedThisSequence(this.signalToSend);
            return cords;
        }
        if (this.id == 19 && Objects.equals(this.signalToSend.getOrientation(), "SAT-")) {
            recepteurs.get(20).addSignalReceivedThisSequence(this.signalToSend);
            return cords;
        }
        if (this.id == 20 && Objects.equals(this.signalToSend.getOrientation(), "SAT+")) {
            recepteurs.get(19).addSignalReceivedThisSequence(this.signalToSend);
            return cords;
        }
        if (this.id == 29 && Objects.equals(this.signalToSend.getOrientation(), "SAD-")) {
            recepteurs.get(30).addSignalReceivedThisSequence(this.signalToSend);
            return cords;
        }
        if (this.id == 30 && Objects.equals(this.signalToSend.getOrientation(), "SAT+")) {
            recepteurs.get(29).addSignalReceivedThisSequence(this.signalToSend);
            return cords;
        }
        if (this.id == 39 && Objects.equals(this.signalToSend.getOrientation(), "SAT+")) {
            recepteurs.get(0).addSignalReceivedThisSequence(this.signalToSend);
            return cords;
        }

        cords.clear();

        for (int i = 0; i < 39; i++) {
            if (i == this.id && i < 10) {
                if (Objects.equals(this.signalToSend.getOrientation(), "A-")) {
                    cords.add(0);
                    cords.add(i);
                } else if (Objects.equals(this.signalToSend.getOrientation(), "SAT-")) {
                    cords.add(0);
                    cords.add(i - 1);
                } else {
                    cords.add(0);
                    cords.add(i + 1);
                }
                return cords;
            }
            if (i == this.id && i < 20) {
                if (Objects.equals(this.signalToSend.getOrientation(), "N-")) {
                    cords.add(i - 10);
                    cords.add(matrixSize - 1);
                } else if (Objects.equals(this.signalToSend.getOrientation(), "SAD-")) {
                    cords.add((i - 10) - 1);
                    cords.add(matrixSize - 1);
                } else {
                    cords.add((i - 10) + 1);
                    cords.add(matrixSize - 1);
                }
                return cords;
            }
            if (i == this.id && i < 30) {
                if (Objects.equals(this.signalToSend.getOrientation(), "A+")) {
                    cords.add(matrixSize - 1);
                    cords.add(10 - (i - 20) - 1);
                } else if (Objects.equals(this.signalToSend.getOrientation(), "SAT-")) {
                    cords.add(matrixSize - 1);
                    cords.add(10 - (i - 20) - 2);
                } else {
                    cords.add(matrixSize - 1);
                    cords.add(10 - (i - 20));
                }
                return cords;
            }
            if (i == this.id) {
                if (Objects.equals(this.signalToSend.getOrientation(), "N+")) {
                    cords.add(i - 30);
                    cords.add(0);
                } else if (Objects.equals(this.signalToSend.getOrientation(), "SAT+")) {
                    cords.add((i - 30) - 1);
                    cords.add(0);
                } else {
                    cords.add((i - 30) + 1);
                    cords.add(0);
                }
                return cords;
            }
        }
        return cords;
    }

    public Signal getSignalToSend() {
        return this.signalToSend;
    }

    public Integer getId() {
        return this.id;
    }

    public void clearSignal() {
        this.signalToSend = null;
    }
}
