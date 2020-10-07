package regates.mvp.model;

import org.junit.Assert;
import org.junit.Test;

public class CheckpointTest {

    @Test
    public void testConstrutor(){
        Checkpoint c = new Checkpoint(1, new Coordinate(50, 10), 50);
        Assert.assertEquals(1, c.getOrder());
        Assert.assertEquals(50, c.getPosition().getX(), 0);
        Assert.assertEquals(10, c.getPosition().getY(), 0);
        Assert.assertEquals(50, c.getRadius(), 0);

    }
}
