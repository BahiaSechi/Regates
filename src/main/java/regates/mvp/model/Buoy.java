package regates.mvp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Represents a sea buoy
 * Obstacle for the boat
 * @see Coordinate
 */
public class Buoy {
    @Getter
    private final double radius;
    @Getter
    private final Coordinate position;

    /**
     * Buoy constructor
     * @param radius Hitbox in pixels
     * @param position Barycenter of the image
     */
    public Buoy(@JsonProperty("radius") double radius, @JsonProperty("position") Coordinate position) {
        this.radius = radius;
        this.position = position;
    }
}
