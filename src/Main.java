import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Fichier fichier = new Fichier(new File("omega_miroir.txt").getAbsolutePath(), new File("omega_mesure.txt").getAbsolutePath());

        Factory factory = new Factory(fichier);

        System.out.println("Séquence #0 : Chargement de la matrice, aucun signal émis");
        MatriceMiroir matrix = new MatriceMiroir(factory.createMirrorMatrix(), factory.createTransmitters(), factory.createReceivers());

//        matrix.displayAllMirrors();

        for (int i = 1; i < fichier.getSequenceNumber() + 1; i++) {
            System.out.println("----------------------------------\n");
            System.out.printf("Séquence #%d: ", i);

            List<String> measures = fichier.getMeasuresFromSequence(i);

            if (measures.isEmpty()) {
                System.out.println("Aucun signal émis");
                continue;
            }

            for (int j = 0; j < measures.size(); j++) {
                String signalDirection = fichier.getMeasureDirection(measures.get(j));
                System.out.print(signalDirection);
                if (j < measures.size() - 1)
                    System.out.print(", ");
                else
                    System.out.println();
                matrix.setupSignal(factory.createSignal(signalDirection), fichier.getMeasureTransmitter(measures.get(j)));
            }
            matrix.sendSignals();
            matrix.sequenceAllSignals();

            System.out.print("Signaux reçus cette séquences: ");
            matrix.displaySequenceSignalsReceived();
        }
        System.out.println("----------------------------------");

        System.out.print("Mesures de sortie: ");
        matrix.displayEndSignalsReceived();
    }
}