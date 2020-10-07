package regates.mvp.model;

import org.junit.Assert;
import org.junit.Test;

public class CoordinateTest {

    @Test
    public void testEquals_fail() {
        Coordinate a = new Coordinate(5, 5);
        Coordinate b = new Coordinate(15, 5);
        Assert.assertNotEquals(a, b);
    }

    @Test
    public void testEquals_fail2() {
        Coordinate a = new Coordinate(5, 5);
        Coordinate b = new Coordinate(5, 15);
        Assert.assertNotEquals(a, b);
    }

    @Test
    public void testEquals_fail3() {
        Coordinate a = new Coordinate(5, 15);
        Coordinate b = new Coordinate(5, 5);
        Assert.assertNotEquals(a, b);
    }
    @Test
    public void testEquals_fail4() {
        Coordinate a = new Coordinate(5, 15);
        Assert.assertNotEquals(a, "");
    }

    @Test
    public void testEquals_fail5() {
        Coordinate a = new Coordinate(5, 15);
        Assert.assertNotEquals(a, null);
    }

    @Test
    public void testHashCode_fail1(){
        Coordinate a = new Coordinate(5, 15);
        Coordinate b = new Coordinate(5, 5);
        Assert.assertNotEquals(a.hashCode(), b.hashCode());

    }

    @Test
    public void testEquals_success() {
        Coordinate a = new Coordinate(5, 5);
        Coordinate b = new Coordinate(5, 5);
        Assert.assertEquals(a, b);
        Assert.assertEquals(a, a);
    }

    @Test
    public void testToString(){
        Coordinate a = new Coordinate(5, 5);
        Assert.assertEquals("5.0 : 5.0", a.toString());
    }
}
