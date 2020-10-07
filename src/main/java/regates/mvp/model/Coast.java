package regates.mvp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * Represent a coast. Boat have to avoid it.
 * @see Coordinate
 * @see Border
 */
@Getter
public class Coast {

    private final String imgPath;
    private final Coordinate position;
    private final Coordinate size;

    @JsonIgnore
    private final Border borders;

    /**
     * Coast constructor.
     * @param position The position of the coast (Barycenter's position)
     * @param imgPath The path of the coast's image
     * @param size The size of coast
     */
    public Coast(
            @JsonProperty("position") Coordinate position,
            @JsonProperty("imgPath") String imgPath,
            @JsonProperty("size") Coordinate size) {
        this.position = position;
        this.imgPath = imgPath;
        this.size = size;

        this.borders = new Border();
        this.borders.setBarycentre(position);
        this.borders.translateBorders();
    }
}
