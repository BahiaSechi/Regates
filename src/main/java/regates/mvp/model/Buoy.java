package regates.mvp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Buoy {

    private int id;
    @Getter
    private float radius;
    @Getter
    private Coordinate position;
    private String status;

}
