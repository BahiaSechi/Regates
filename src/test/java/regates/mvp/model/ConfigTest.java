package regates.mvp.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

public class ConfigTest {
    @Test
    public void testConstructor() {
        Config c = new Config(new Coordinate(15, 30), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(),
                5, 5);

        Assert.assertEquals(new Coordinate(15, 30), c.getStartingPoint());
        Assert.assertEquals(Collections.emptyList(), c.getBuoys());
        Assert.assertEquals(Collections.emptyList(), c.getCheckpoints());
        Assert.assertEquals(Collections.emptyList(), c.getCoasts());
        Assert.assertEquals(5, c.getWindDirection());
    }
}
