package regates.mvp.model;

import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class WindTest {
    @Test
    public void should_return_first_boat_speed() {
        Wind w = new Wind(Objects.requireNonNull(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("windData.txt")).getPath()));
        w.setStrength(1);
        double speed = w.determinateSpeed(1);
        assertEquals(1.7, speed, 0.01);
    }

    @Test
    public void should_return_wind_strength_and_direction() {
        Wind w = new Wind(Objects.requireNonNull(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("windData.txt")).getPath()));
        w.setStrength(1);
        w.setDirection(0);
        assertEquals(1, w.getStrength());
        assertEquals(0, w.getDirection());
    }
}
