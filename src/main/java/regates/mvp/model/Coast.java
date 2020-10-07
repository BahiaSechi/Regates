package regates.mvp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class Coast {

    @Getter
    private final String imgPath;

    @Getter
    private final Coordinate position;

    @Getter
    private final Coordinate size;

    @Getter
    @JsonIgnore
    private final Border borders;

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
