package regates.mvp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Coordinate {
    private double x;
    private double y;

    @Override
    public String toString() {
        return this.x + " : " + this.y;
    }

    public static double distance(Coordinate a, Coordinate b) {
        return Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));
    }
}
