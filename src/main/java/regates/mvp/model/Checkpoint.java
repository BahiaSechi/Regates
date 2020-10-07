package regates.mvp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;

/**
 * Represent a Checkpoint. The boat have to go through every checkpoints to complete the race.
 *
 * @see Coordinate
 */
@Getter
public class Checkpoint {

    @NonNull
    private final Coordinate position;
    private final int order;
    private final double radius;

    /**
     * Checkpoint constructor
     *
     * @param order    The checkpoint's order in the race
     * @param position The position on the sea
     * @param radius   The checkpoint radius
     */
    public Checkpoint(
            @JsonProperty("order") int order,
            @JsonProperty("position") Coordinate position,
            @JsonProperty("radius") double radius) {
        this.order = order;
        this.position = position;
        this.radius = radius;
    }
}
