import System.Recepteur;
import System.Emetteur;
import System.Mirroir;
import java.util.List;

public class MatriceMirroir {
    private final List<Recepteur> recepteur;
    private final List<Emetteur> emetteur;
    private final List<Mirroir> mirroir;

    public MatriceMirroir(List<Recepteur> recepteur, List<Emetteur> emetteur, List<Mirroir> mirroir) {
        this.recepteur = recepteur;
        this.emetteur = emetteur;
        this.mirroir = mirroir;
    }
}
