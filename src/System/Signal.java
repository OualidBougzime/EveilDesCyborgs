package System;

import System.Orientation.Orientation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Signal {
    private Orientation orientation;
    private Character sign;

    public Signal(Orientation orientation, Character sign) {
        this.orientation = orientation;
        this.sign = sign;
    }

    public String getOrientation() {
        return this.orientation.getOrientation() + sign.toString();
    }

    public List<Integer> getMoveCords(int i, int j) {
        List<Integer> cords = new ArrayList<>();
        if (Objects.equals(this.getOrientation(), "A+")) {
            cords.add(i - 1);
            cords.add(j);
            return cords;
        } else if (Objects.equals(this.getOrientation(), "A-")) {
            cords.add(i + 1);
            cords.add(j);
            return cords;
        } else if (Objects.equals(this.getOrientation(), "N-")) {
            cords.add(i);
            cords.add(j - 1);
            return cords;
        } else if (Objects.equals(this.getOrientation(), "N+")) {
            cords.add(i);
            cords.add(j + 1);
            return cords;
        } else if (Objects.equals(this.getOrientation(), "SAT-")) {
            cords.add(i + 1);
            cords.add(j - 1);
            return cords;
        } else if (Objects.equals(this.getOrientation(), "SAT+")) {
            cords.add(i - 1);
            cords.add(j + 1);
            return cords;
        } else if (Objects.equals(this.getOrientation(), "SAD-")) {
            cords.add(i - 1);
            cords.add(j - 1);
            return cords;
        } else if (Objects.equals(this.getOrientation(), "SAD+")) {
            cords.add(i + 1);
            cords.add(j + 1);
            return cords;
        } else {
            cords.add(i);
            cords.add(j);
            return cords;
        }
    }

    public void setOrientation(Orientation newOrientation, Character newSign) {
        this.orientation = newOrientation;
        this.sign = newSign;
    }
}
