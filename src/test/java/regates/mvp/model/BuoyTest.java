package regates.mvp.model;

import org.junit.Assert;
import org.junit.Test;

public class BuoyTest {

    @Test
    public void testConstructor() {
        Buoy b = new Buoy(50, new Coordinate(10, 15));
        Assert.assertEquals(15, b.getPosition().getY(), 0);
        Assert.assertEquals(10, b.getPosition().getX(), 0);
        Assert.assertEquals(50, b.getRadius(), 0);
    }
}
