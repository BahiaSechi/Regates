package regates.mvp.model;

import org.junit.Assert;
import org.junit.Test;
import regates.mvp.model.boat.BoatObserver;

public class GameTest {

    @Test
    public void testStartStopGame() throws Exception {
        Game g = new Game();
        int before = Thread.activeCount();
        g.start();
        Assert.assertEquals(before + 1, Thread.activeCount());
        g.stop();
    }

    @Test
    public void testgetT() throws Exception {
        Game g = new Game();
        Assert.assertEquals(Integer.valueOf(0), g.getBoat().getAngle().getValue());
    }

    @Test
    public void testAddObserver() throws Exception {
        Game g = new Game();
        BoatObserver bo = boat -> {
        };
        Assert.assertEquals(0, g.getBoat().getBoatObservers().size());
        g.setObserver(bo);
        Assert.assertEquals(1, g.getBoat().getBoatObservers().size());
    }

}
