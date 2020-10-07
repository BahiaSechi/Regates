package regates.mvp.model;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Objects;

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

    @Test(expected = Exception.class)
    public void testInvalidFile() throws Exception {
        Game g = new Game("invalid_path");
    }

    @Test(expected = Exception.class)
    public void testInvalidStartingPoint() throws Exception {
        Game g = new Game(Objects.requireNonNull(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("configFiles/conf_invalid.yaml")).getPath()));
    }

    @Test(expected = Exception.class)
    public void testInvalidWindStrength() throws Exception {
        Game g = new Game(Objects.requireNonNull(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("configFiles/conf_invalid2.yaml")).getPath()));
    }
}
