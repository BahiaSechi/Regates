package regates.mvp.model;

import javafx.beans.property.SimpleIntegerProperty;
import org.junit.Test;
import regates.mvp.model.boat.Boat;

import static org.junit.Assert.assertEquals;

public class BoatSpeedTest {
    @Test
    public void should_return_first_boat_speed() {
        Boat b = new Boat(new SimpleIntegerProperty(1), null);
        double speed = b.determinateSpeed(1);
        assertEquals(1.7, speed, 0.01);
    }

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
        assertEquals(b.getSpeed(), 1.7, 0.01);
        assertEquals(b.getPosition().getX(), 0, 0.1);
        assertEquals(b.getPosition().getY(), -1.7, 0.1);
    }
}