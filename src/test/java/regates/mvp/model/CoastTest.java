package regates.mvp.model;

import javafx.scene.image.Image;
import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

public class CoastTest {

    @Test
    public void testConstructor() {
        Coast c = new Coast(new Coordinate(0, 0), "", new Coordinate(0, 0));
        Assert.assertEquals(0, c.getPosition().getY(), 0);
        Assert.assertEquals(0, c.getPosition().getX(), 0);
        Assert.assertEquals(0, c.getSize().getY(), 0);
        Assert.assertEquals(0, c.getSize().getY(), 0);

        Assert.assertEquals("", c.getImgPath());
    }
}
