import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Fichier {
    private final String[] Mirrors;
    private final String[] Measures;

    public Fichier(String pathFileMirrors, String pathFileMeasures) throws IOException {
        this.Mirrors = new String(Files.readAllBytes(Paths.get(pathFileMirrors))).split("\n");
        this.Measures = new String(Files.readAllBytes(Paths.get(pathFileMeasures))).split("/");

//        for (String mirror: this.Mirrors
//             ) {
//            System.out.println(mirror);
//            System.out.println(mirror.replaceAll("[0-9]*", ""));
//        }
//        for (String measure: this.Measures
//        ) {
//            System.out.println(measure);
//            System.out.println(measure.replaceAll("[^0-9].*", ""));
//            System.out.println(measure.replaceAll("[0-9]", ""));
//            System.out.println(measure.substring(measure.length() - 1));
//        }
    }

    public String[] getMirrors() {
        return this.Mirrors;
    }

    public String[] getMeasures() {
        return this.Measures;
    }

    public List<Integer> getMirrorCoords(String mirror) {
        List<Integer> coords = new ArrayList<>();
        // Regex to return the first and second number in the mirror string
        coords.add(Integer.parseInt(mirror.replaceAll("[^0-9]", "").substring(0, 2)));
        // Regex to return the third and fourth number in the mirror string
        coords.add(Integer.parseInt(mirror.replaceAll("[^0-9]", "").substring(2, 4)));
        return coords;
    }

    public String getMirrorType(String mirror) {
        // Regex to return the mirror type and without coords in mirror string
        String mirrorType = mirror.replaceAll("[0-9]*", "");
        return mirrorType;
    }

    public Integer getMeasureTransmitter(String measure) {
        // Regex to return the first numbers in measure string
        return Integer.parseInt(measure.replaceAll("[^0-9].*", ""));
    }

    public String getMeasureDirection(String measure) {
        // Regex to return the direction in measure string
        return measure.replaceAll("[0-9]", "");
    }

    public Integer getMeasureSequence(String measure) {
        // Return the last number (sequence number) in measure string
        return Integer.parseInt(measure.substring(measure.length() - 1));
    }

    public List<String> getMeasuresFromSequence(int sequence) {
        List<String> measures = new ArrayList<>();
        for (String measure: this.getMeasures()
             ) {
            if (getMeasureSequence(measure) == sequence) {
                measures.add(measure);
            }
        }
        return measures;
    }

    public Integer getSequenceNumber() {
        int sequence = 0;
        for (String measure: this.getMeasures()
             ) {
            sequence = Math.max(sequence, getMeasureSequence(measure));
        }
        return sequence;
    }

    public Integer getMatrixSizeX() {
        Integer x = -1;
        for (String mirror: this.Mirrors
             ) {
            List<Integer> coords = this.getMirrorCoords(mirror);
            x = Math.max(x, coords.get(0));
        }
        return x + 1;
    }

    public Integer getMatrixSizeY() {
        Integer y = 40;
        for (String mirror: this.Mirrors
        ) {
            List<Integer> coords = this.getMirrorCoords(mirror);
            y = Math.min(y, coords.get(1));
        }
        return 39 - y + 1;
    }
}
