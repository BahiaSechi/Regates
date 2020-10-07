package regates.mvp.model;

import javafx.scene.image.Image;
import org.junit.Assert;
import org.junit.Test;
import regates.mvp.model.boat.BoatObserver;

import java.util.Objects;

public class GameTest {

    @Test
    public void testStartStopGame() throws Exception {
        Game g = new Game(Objects.requireNonNull(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("configFiles/conf_normandie.yaml")).getPath()));
        int before = Thread.activeCount();
        g.start();
        Assert.assertEquals(before + 1, Thread.activeCount());
        g.stop();
    }

    @Test
    public void testgetAngle() throws Exception {
        Game g = new Game(Objects.requireNonNull(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("configFiles/conf_normandie.yaml")).getPath()));
        Assert.assertEquals(Integer.valueOf(1), g.getBoat().getAngle().getValue());
    }

    @Test
    public void testAddObserver() throws Exception {
        Game g = new Game(Objects.requireNonNull(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("configFiles/conf_normandie.yaml")).getPath()));
        BoatObserver bo = boat -> {
        };
        Assert.assertEquals(0, g.getBoat().getBoatObservers().size());
        g.setBoatObserver(bo);
        Assert.assertEquals(1, g.getBoat().getBoatObservers().size());
    }

    @Test
    public void testGetOrder() throws Exception {
        Game g = new Game(Objects.requireNonNull(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("configFiles/conf_normandie.yaml")).getPath()));
        Assert.assertEquals(0, g.getOrder());
    }

    @Test
    public void testTestBuoyCollision() throws Exception {
        Game g = new Game(Objects.requireNonNull(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("configFiles/conf_normandie.yaml")).getPath()));
        g.getBoat().getBorders().setBarycentre(new Coordinate(0, 0));
        g.getBoat().getBorders().generateBordersForImage(new Image(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream("img/ship.png"))), 45, 56);
        Board.getInstance().getBuoys().add(new Buoy(100, new Coordinate(0, 0)));

        Assert.assertTrue(g.testBuoyCollision());
    }

    @Test
    public void testTestCoastCollision() throws Exception {
        Game g = new Game(Objects.requireNonNull(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("configFiles/conf_normandie.yaml")).getPath()));
        g.getBoat().getBorders().setBarycentre(new Coordinate(1100, 400));
        g.getBoat().getBorders().generateBordersForImage(new Image(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream("img/ship.png"))), 45, 56);


        Assert.assertFalse(g.testCoastCollision());
    }

    @Test
    public void testTestBoatExitWindow() throws Exception {
        Game g = new Game(Objects.requireNonNull(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("configFiles/conf_normandie.yaml")).getPath()));
        g.getBoat().getBorders().setBarycentre(new Coordinate(0, 0));
        g.getBoat().getBorders().generateBordersForImage(new Image(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream("img/ship.png"))), 45, 56);
        g.getBoat().getBorders().translateBorders();
        Assert.assertTrue(g.testBoatExitWindow());

        g.getBoat().getBorders().setBarycentre(new Coordinate(600, 550));
        g.getBoat().getBorders().translateBorders();
        Assert.assertFalse(g.testBoatExitWindow());
    }

    @Test
    public void testTestCheckpointID() throws Exception {
        Game g = new Game(Objects.requireNonNull(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("configFiles/conf_normandie.yaml")).getPath()));
        g.getBoat().getBorders().setBarycentre(new Coordinate(1100, 400));
        g.getBoat().getBorders().generateBordersForImage(new Image(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream("img/ship.png"))), 45, 56);

        Assert.assertFalse(g.testCheckpoint(0));
        g.getBoat().getBorders().resetTranslation();
        g.getBoat().getBorders().setBarycentre(new Coordinate(600, 270));
        g.getBoat().getBorders().translateBorders();
        Assert.assertTrue(g.testCheckpoint(0));

    }

}
