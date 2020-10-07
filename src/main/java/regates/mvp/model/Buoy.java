package regates.mvp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class Buoy {
    @Getter
    private final double radius;
    @Getter
    private final Coordinate position;

    public Buoy(@JsonProperty("radius") double radius, @JsonProperty("position") Coordinate position) {
        this.radius = radius;
        this.position = position;
    }
}
