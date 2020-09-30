package regates.mvp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Boat {

    private float degree;
    private float speed;
    private Coordinate position;

    public boolean isCollision(Coordinate a, Coordinate b) {
        return true;
    }

}
