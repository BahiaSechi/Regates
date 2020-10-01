package regates.mvp.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class BoatSpeedTest {
    @Test
    public void should_return_first_boat_speed() throws Exception {
        Boat b = new Boat(1, null);
        double speed = b.determinateSpeed(1);
        assertEquals(speed, 1.7, 0.01);
    }
}