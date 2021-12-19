import System.Miroir;
import System.Emetteur;
import System.Recepteur;
import System.Orientation.*;
import System.Signal;

import java.util.ArrayList;
import java.util.List;

public class Factory {

    public final Fichier fichier;

    public Factory (Fichier fichier) {
        this.fichier = fichier;
    }

    private Orientation createOrientation(String type) {
        return switch (type) {
            case "N" -> new Neutre();
            case "A" -> new Actif();
            case "SAT" -> new SemiActifTransitif();
            case "SAD" -> new SemiActifDirectif();
            case "SATA" -> new SemiActifTransitifActif();
            case "SATN" -> new SemiActifTransitifNeutre();
            case "SADA" -> new SemiActifDirectifActif();
            case "SADN" -> new SemiActifDirectifNeutre();
            default -> new Orientation(type);
        };
    }

    private Miroir createMirror(String type, List<Integer> coords) {
        return new Miroir(createOrientation(type), coords);
    }

    public List<Miroir> createMirrors() {
        String[] mirrorStrings = fichier.getMirrors();
        List<Miroir> mirrors = new ArrayList<>();

        for (String mirror: mirrorStrings
             ) {
            mirrors.add(createMirror(fichier.getMirrorType(mirror), fichier.getMirrorCoords(mirror)));
        }
        return mirrors;
    }

    public List<List<Chambre>> createMirrorMatrix() {
        Integer SizeX = fichier.getMatrixSizeX();
        Integer SizeY = fichier.getMatrixSizeY();
        List<List<Chambre>> matrix = new ArrayList<>();
        List<Miroir> mirrors = this.createMirrors();

        for (int i = 0; i < SizeX; i++) {
            List<Chambre> array = new ArrayList<>();
            for (int j = 0; j < SizeY; j++) {
                Chambre chambre = new Chambre();
                for (Miroir mirror: mirrors
                     ) {
                    if (mirror.getMatrixCordX() == i && mirror.getMatrixCordY() == j)
                        chambre.setChamber(mirror);
                }
                array.add(chambre);
            }
            matrix.add(array);
        }
        return matrix;
    }

    public List<Emetteur> createTransmitters() {
        List<Emetteur> transmitters = new ArrayList<>();
        for (int i = 0; i < 39; i++) {
            if (i < 10)
                transmitters.add(new Emetteur(new Signal(new Actif(), '-'), new Signal(new SemiActifTransitif(), '-'), new Signal(new SemiActifDirectif(), '+'), i));
            if (i >= 10 && i < 20)
                transmitters.add(new Emetteur(new Signal(new Neutre(), '-'), new Signal(new SemiActifTransitif(), '-'), new Signal(new SemiActifDirectif(), '-'), i));
            if (i >= 20 && i < 30)
                transmitters.add(new Emetteur(new Signal(new Actif(), '+'), new Signal(new SemiActifTransitif(), '+'), new Signal(new SemiActifDirectif(), '-'), i));
            if (i >= 30)
                transmitters.add(new Emetteur(new Signal(new Neutre(), '+'), new Signal(new SemiActifTransitif(), '+'), new Signal(new SemiActifDirectif(), '+'), i));
        }
        return transmitters;
    }

    public List<Recepteur> createReceivers() {
        List<Recepteur> receivers = new ArrayList<>();
        for (int i = 0; i < 39; i++) {
            if (i < 10)
                receivers.add(new Recepteur(new Signal(new Actif(), '+'), new Signal(new SemiActifTransitif(), '+'), new Signal(new SemiActifDirectif(), '-'), i, -1, i));
            if (i >= 10 && i < 20)
                receivers.add(new Recepteur(new Signal(new Neutre(), '+'), new Signal(new SemiActifTransitif(), '+'), new Signal(new SemiActifDirectif(), '+'), i, i - 10, 10));
            if (i >= 20 && i < 30)
                receivers.add(new Recepteur(new Signal(new Actif(), '-'), new Signal(new SemiActifTransitif(), '-'), new Signal(new SemiActifDirectif(), '+'), i, 10, 9 - (i - 20)));
            if (i >= 30)
                receivers.add(new Recepteur(new Signal(new Neutre(), '-'), new Signal(new SemiActifTransitif(), '-'), new Signal(new SemiActifDirectif(), '-'), i, 9 - (i - 30), -1));
        }
        return receivers;
    }

    public Signal createSignal(String measure) {
        String type = measure.substring(0, measure.length() - 1);
        Character sign = measure.charAt(measure.length() - 1);

        return new Signal(createOrientation(type), sign);
    }
}
