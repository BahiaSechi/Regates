package regates.mvp.model;

import javafx.beans.property.SimpleIntegerProperty;
import org.junit.Test;
import regates.mvp.model.boat.Boat;

import static org.junit.Assert.assertEquals;

public class BoatSpeedTest {
    @Test
    public void should_rotate_boat() {
        Boat b = new Boat(new SimpleIntegerProperty(1), null);
        int angle1 = b.getAngle().getValue();
        b.rotate(+1);
        assertEquals(b.getAngle().getValue().intValue(), angle1 + 1);
        b.rotate(-2);
        assertEquals(b.getAngle().getValue().intValue(), angle1 - 1);
    }

    @Test
    public void should_move_boat() {
        Boat b = new Boat(new SimpleIntegerProperty(1), new Coordinate(0, 0));
        b.setAngle(new SimpleIntegerProperty(1));
        b.move(1);
        assertEquals(1, b.getSpeed(), 0.1);
        assertEquals(0, b.getPosition().getX(), 0.1);
        assertEquals(-1, b.getPosition().getY(), 0.1);
    }
}