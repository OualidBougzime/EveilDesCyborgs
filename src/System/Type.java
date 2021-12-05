package System;
import System.Orientation.Orientation;

import java.util.List;

public class Type {
    private final List<Direction> direction;
    private final List<Orientation> orientation;


    public Type(List<Direction> direction, List<Orientation> orientation) {
        this.direction = direction;
        this.orientation = orientation;
    }
}
