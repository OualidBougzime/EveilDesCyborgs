import System.Recepteur;
import System.Emetteur;
import System.Signal;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class MatriceMiroir {
    private final List<Emetteur> emetteurs;
    private final List<Recepteur> recepteurs;
    private final List<List<Chambre>> matrice;

    public MatriceMiroir(List<List<Chambre>> matrix, List<Emetteur> emetteurs, List<Recepteur> recepteurs) {
        this.matrice = matrix;
        this.emetteurs = emetteurs;
        this.recepteurs = recepteurs;
    }

    public void setupSignal(Signal signal, int transmitterId) {
        this.emetteurs.get(transmitterId).setupSignal(signal);
    }

    public void sendSignals() {
        for (Emetteur emetteur: this.emetteurs
             ) {
            List<Integer> signalCords = emetteur.sendSignal(this.recepteurs, this.matrice.size());

            if (emetteur.getSignalToSend() != null)
                System.out.println("L'Emetteur: " + emetteur.getId().toString() + " à envoyer un signal: " + emetteur.getSignalToSend().getOrientation() + " to cords: " + signalCords.get(0).toString() + "; " + signalCords.get(1).toString());

            if (signalCords.get(0) != -1 && signalCords.get(1) != -1)
                this.matrice.get(signalCords.get(0)).get(signalCords.get(1)).setChamber(emetteur.getSignalToSend());

            emetteur.clearSignal();
        }
    }

    private Boolean checkSignalCords(int i, int j) {
        return (i < (this.matrice.size()) && i >= 0) && (j < (this.matrice.size()) && j >= 0);
    }

    private void addToReceiver(Signal signal, int i, int j) {
        for (Recepteur receiver: this.recepteurs
             ) {
            if (receiver.getCordX() == i && receiver.getCordY() == j) {
                if (receiver.checkPossibleSignal(signal)) {
                    receiver.addSignalReceivedThisSequence(signal);
                    System.out.println("Le signal: " + signal.getOrientation() + " à été reçu par le récepteur: " + receiver.getId() + ", aux coordonnées: " + i + "; " + j);
                }
                else
                    System.out.println("Le signal: " + signal.getOrientation() + " ne peut pas être pris en charge par le récepteur: " + receiver.getId());
            }
        }
    }

    private void moveSignalsOnMirror(Chambre chamber) {
        for (Signal signal : chamber.getChamberSignals()
        ) {
            List<Integer> nextCords = chamber.getChamberMirror().changeSignal(signal);
            if (this.checkSignalCords(nextCords.get(0), nextCords.get(1))) {
                this.matrice.get(nextCords.get(0)).get(nextCords.get(1)).setChamber(signal);
                System.out.println("Le signal: " + signal.getOrientation() + " à été déplacé vers: " + nextCords.get(0) + "; " + nextCords.get(1) + " par un miroir");
//                this.displayMatricePoint(nextCords.get(0), nextCords.get(1));
            } else
                this.addToReceiver(signal, nextCords.get(0), nextCords.get(1));
        }
        chamber.clearSignals();
    }

    private void moveSignals(Chambre chambre, int i, int j) {
        for (Signal signal: chambre.getChamberSignals()) {
            List<Integer> nextCords = signal.getMoveCords(i, j);
            if (this.checkSignalCords(nextCords.get(0), nextCords.get(1))) {
                this.matrice.get(nextCords.get(0)).get(nextCords.get(1)).setChamber(signal);
                System.out.println("Le signal: " + signal.getOrientation() + " à été déplacé de: " + i + "; " + j + " à: " + nextCords.get(0) + "; " + nextCords.get(1));
//                this.displayMatricePoint(nextCords.get(0), nextCords.get(1));
            } else
                this.addToReceiver(signal, nextCords.get(0), nextCords.get(1));
        }
        this.matrice.get(i).get(j).clearSignals();
    }

    private boolean checkAllSignalsForSequence() {
        boolean allGood = true;
        for (int i = 0; i < this.matrice.size(); i++) {
            for (int j = 0; j < this.matrice.size(); j++) {
                if (!this.matrice.get(i).get(j).getChamberSignals().isEmpty()) {
                    allGood = false;
                    break;
                }
            }
        }
        return allGood;
    }

    public void sequenceAllSignals() {
        while (!this.checkAllSignalsForSequence()) {
            for (int i = 0; i < this.matrice.size(); i++) {
                for (int j = 0; j < this.matrice.get(i).size(); j++) {
                    if (this.matrice.get(i).get(j).getChamberSignals() == null || this.matrice.get(i).get(j).getChamberSignals().isEmpty())
                        continue;
                    if (this.matrice.get(i).get(j).getChamberMirror() != null)
                        this.moveSignalsOnMirror(this.matrice.get(i).get(j));
                    else if (this.matrice.get(i).get(j).getChamberMirror() == null) {
                        this.moveSignals(this.matrice.get(i).get(j), i, j);
                    }
                }
            }
        }
    }

    public void displaySequenceSignalsReceived() {
        for (Recepteur receiveur: this.recepteurs) {
            int index = 0;
            for (Signal signal: receiveur.getSignalReceivedThisSequence()) {
                System.out.print(signal.getOrientation());
                if (index != receiveur.getSignalReceivedThisSequence().size() - 1)
                    System.out.print(", ");
                index++;
            }
            if (!receiveur.getSignalReceivedThisSequence().isEmpty())
                System.out.print(", ");
            receiveur.clearSignalReceivedThisSequence();
        }
        System.out.println();
    }

    public void displayEndSignalsReceived() {
        for (Recepteur receiveur: this.recepteurs) {
            int index = 0;
            for (Signal signal: receiveur.getSignalReceived()) {
                System.out.print(signal.getOrientation());
                if (index != receiveur.getSignalReceived().size() - 1)
                    System.out.print(", ");
                index++;
            }
            if (!receiveur.getSignalReceived().isEmpty())
                System.out.print(", ");
        }
    }

    private void displayMatricePoint(int x, int y) {
        for (int i = 0; i < this.matrice.size(); i++) {
            for (int j = 0; j < this.matrice.size(); j++) {
                System.out.print("|");
                if (i == x && y == j)
                    System.out.print(" @ ");
                else System.out.print("   ");
            }
            System.out.println("|");
        }
        try
        {
            Thread.sleep(250);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public void displayAllMirrors() {
        for (int i = 0; i < this.matrice.size(); i++) {
            for (int j = 0; j < this.matrice.size(); j++) {
                System.out.print("|");
                if (this.matrice.get(i).get(j).getChamberMirror() != null)
                    System.out.print(" M ");
                else System.out.print("   ");
            }
            System.out.println("|");
        }
    }
}
