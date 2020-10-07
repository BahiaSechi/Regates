package regates.mvp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class Checkpoint {
    private final int order;

    @NonNull
    private final Coordinate position;

    private final double radius;


    public Checkpoint(
            @JsonProperty("order") int order,
            @JsonProperty("position") Coordinate position,
            @JsonProperty("radius") double radius) {
        this.order = order;
        this.position = position;
        this.radius = radius;
    }
}
