package System;

import System.Orientation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Miroir {
    private final Orientation orientation;
    private final List<Integer> position;

    public Miroir(Orientation orientation, List<Integer> position) {
        this.orientation = orientation;
        this.position = position;
    }

    public String getOrientation() {
        return this.orientation.getOrientation();
    }

    public Integer getCordX() { return this.position.get(0);}
    public Integer getCordY() { return this.position.get(1);}

    public Integer getMatrixCordX() { return 9 - (this.position.get(1) - 30);}
    public Integer getMatrixCordY() { return this.position.get(0);}

    public List<Integer> changeSignal(Signal signal) {
        List<Integer> cords = new ArrayList<>();
        if (Objects.equals(signal.getOrientation(), "A+")) {
            if (Objects.equals(this.getOrientation(), "A")) {
                cords.add(this.getMatrixCordX() - 1);
                cords.add(this.getMatrixCordY());
            }
            if (Objects.equals(this.getOrientation(), "N")) {
                signal.setOrientation(new Actif(), '-');
                cords.add(this.getMatrixCordX() + 1);
                cords.add(this.getMatrixCordY());
            }
            if (Objects.equals(this.getOrientation(), "SAT")) {
                signal.setOrientation(new Neutre(), '+');
                cords.add(this.getMatrixCordX());
                cords.add(this.getMatrixCordY() + 1);
            }
            if (Objects.equals(this.getOrientation(), "SAD")) {
                signal.setOrientation(new Neutre(), '-');
                cords.add(this.getMatrixCordX());
                cords.add(this.getMatrixCordY() - 1);
            }
            return cords;
        } else if (Objects.equals(signal.getOrientation(), "A-")) {
            if (Objects.equals(this.getOrientation(), "A")) {
                cords.add(this.getMatrixCordX() + 1);
                cords.add(this.getMatrixCordY());
            }
            if (Objects.equals(this.getOrientation(), "N")) {
                signal.setOrientation(new Actif(), '+');
                cords.add(this.getMatrixCordX() - 1);
                cords.add(this.getMatrixCordY());
            }
            if (Objects.equals(this.getOrientation(), "SAT")) {
                signal.setOrientation(new Neutre(), '-');
                cords.add(this.getMatrixCordX());
                cords.add(this.getMatrixCordY() - 1);
            }
            if (Objects.equals(this.getOrientation(), "SAD")) {
                signal.setOrientation(new Neutre(), '+');
                cords.add(this.getMatrixCordX());
                cords.add(this.getMatrixCordY() + 1);
            }
            return cords;
        } else if (Objects.equals(signal.getOrientation(), "N+")) {
            if (Objects.equals(this.getOrientation(), "A")) {
                signal.setOrientation(new Neutre(), '-');
                cords.add(this.getMatrixCordX());
                cords.add(this.getMatrixCordY() - 1);
            }
            if (Objects.equals(this.getOrientation(), "N")) {
                cords.add(this.getMatrixCordX());
                cords.add(this.getMatrixCordY() + 1);
            }
            if (Objects.equals(this.getOrientation(), "SAT")) {
                signal.setOrientation(new Actif(), '+');
                cords.add(this.getMatrixCordX() - 1);
                cords.add(this.getMatrixCordY());
            }
            if (Objects.equals(this.getOrientation(), "SAD")) {
                signal.setOrientation(new Actif(), '-');
                cords.add(this.getMatrixCordX() + 1);
                cords.add(this.getMatrixCordY());
            }
            return cords;
        } else if (Objects.equals(signal.getOrientation(), "N-")) {
            if (Objects.equals(this.getOrientation(), "A")) {
                signal.setOrientation(new Neutre(), '+');
                cords.add(this.getMatrixCordX());
                cords.add(this.getMatrixCordY() + 1);
            }
            if (Objects.equals(this.getOrientation(), "N")) {
                cords.add(this.getMatrixCordX());
                cords.add(this.getMatrixCordY() - 1);
            }
            if (Objects.equals(this.getOrientation(), "SAT")) {
                signal.setOrientation(new Actif(), '-');
                cords.add(this.getMatrixCordX() + 1);
                cords.add(this.getMatrixCordY());
            }
            if (Objects.equals(this.getOrientation(), "SAD")) {
                signal.setOrientation(new Actif(), '+');
                cords.add(this.getMatrixCordX() - 1);
                cords.add(this.getMatrixCordY());
            }
            return cords;
        } else if (Objects.equals(signal.getOrientation(), "SAT+")) {
            if (Objects.equals(this.getOrientation(), "A")) {
                signal.setOrientation(new SemiActifDirectif(), '-');
                cords.add(this.getMatrixCordX() - 1);
                cords.add(this.getMatrixCordY() - 1);
            }
            if (Objects.equals(this.getOrientation(), "N")) {
                signal.setOrientation(new SemiActifDirectif(), '+');
                cords.add(this.getMatrixCordX() + 1);
                cords.add(this.getMatrixCordY() + 1);
            }
            if (Objects.equals(this.getOrientation(), "SAT")) {
                cords.add(this.getMatrixCordX() - 1);
                cords.add(this.getMatrixCordY() + 1);
            }
            if (Objects.equals(this.getOrientation(), "SAD")) {
                signal.setOrientation(new SemiActifTransitif(), '-');
                cords.add(this.getMatrixCordX() + 1);
                cords.add(this.getMatrixCordY() - 1);
            }
            return cords;
        } else if (Objects.equals(signal.getOrientation(), "SAT-")) {
            if (Objects.equals(this.getOrientation(), "A")) {
                signal.setOrientation(new SemiActifDirectif(), '+');
                cords.add(this.getMatrixCordX() + 1);
                cords.add(this.getMatrixCordY() + 1);
            }
            if (Objects.equals(this.getOrientation(), "N")) {
                signal.setOrientation(new SemiActifDirectif(), '-');
                cords.add(this.getMatrixCordX() - 1);
                cords.add(this.getMatrixCordY() - 1);
            }
            if (Objects.equals(this.getOrientation(), "SAT")) {
                cords.add(this.getMatrixCordX() + 1);
                cords.add(this.getMatrixCordY() - 1);
            }
            if (Objects.equals(this.getOrientation(), "SAD")) {
                signal.setOrientation(new SemiActifTransitif(), '+');
                cords.add(this.getMatrixCordX() - 1);
                cords.add(this.getMatrixCordY() + 1);
            }
            return cords;
        } else if (Objects.equals(signal.getOrientation(), "SAD+")) {
            if (Objects.equals(this.getOrientation(), "A")) {
                signal.setOrientation(new SemiActifTransitif(), '-');
                cords.add(this.getMatrixCordX() + 1);
                cords.add(this.getMatrixCordY() - 1);
            }
            if (Objects.equals(this.getOrientation(), "N")) {
                signal.setOrientation(new SemiActifTransitif(), '+');
                cords.add(this.getMatrixCordX() - 1);
                cords.add(this.getMatrixCordY() + 1);
            }
            if (Objects.equals(this.getOrientation(), "SAT")) {
                signal.setOrientation(new SemiActifDirectif(), '-');
                cords.add(this.getMatrixCordX() - 1);
                cords.add(this.getMatrixCordY() - 1);
            }
            if (Objects.equals(this.getOrientation(), "SAD")) {
                cords.add(this.getMatrixCordX() + 1);
                cords.add(this.getMatrixCordY() + 1);
            }
            return cords;
        } else if (Objects.equals(signal.getOrientation(), "SAD-")) {
            if (Objects.equals(this.getOrientation(), "A")) {
                signal.setOrientation(new SemiActifTransitif(), '+');
                cords.add(this.getMatrixCordX() - 1);
                cords.add(this.getMatrixCordY() + 1);
            }
            if (Objects.equals(this.getOrientation(), "N")) {
                signal.setOrientation(new SemiActifTransitif(), '-');
                cords.add(this.getMatrixCordX() + 1);
                cords.add(this.getMatrixCordY() - 1);
            }
            if (Objects.equals(this.getOrientation(), "SAT")) {
                signal.setOrientation(new SemiActifDirectif(), '+');
                cords.add(this.getMatrixCordX() + 1);
                cords.add(this.getMatrixCordY() + 1);
            }
            if (Objects.equals(this.getOrientation(), "SAD")) {
                cords.add(this.getMatrixCordX() - 1);
                cords.add(this.getMatrixCordY() - 1);
            }
            return cords;
        } else {
            System.out.println("Bruh");
            cords.add(-1);
            cords.add(-1);
            return cords;
        }
    }
}
