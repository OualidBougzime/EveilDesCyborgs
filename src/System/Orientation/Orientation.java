package System.Orientation;

public class Orientation {
    private final String orientation;

    public Orientation(String orientation) {
        this.orientation = orientation;
    }

    public String getOrientation() {
        return orientation.replaceAll("[^a-zA-Z0-9]", "");
    }
}
