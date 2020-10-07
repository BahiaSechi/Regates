package regates.mvp.model;

import lombok.*;

/**
 * Represent a point on the sea.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Coordinate {
    private double x;
    private double y;

    @Override
    public String toString() {
        return this.x + " : " + this.y;
    }

    /**
     * Calculate the euclidian distance between two Coordinate
     *
     * @param a The first Coordinate
     * @param b The seconde Coordinate
     * @return The euclidian distance
     */
    public static double distance(Coordinate a, Coordinate b) {
        return Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));
    }
}
