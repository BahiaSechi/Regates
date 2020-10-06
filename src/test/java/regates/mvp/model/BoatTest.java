package regates.mvp.model;

import javafx.beans.property.SimpleIntegerProperty;
import org.junit.Assert;
import org.junit.Test;
import regates.mvp.model.boat.Boat;
import regates.mvp.model.boat.BoatObserver;

public class BoatTest {

    @Test
    public void addRemoveObserver() {
        Boat b = new Boat(new SimpleIntegerProperty(1), new Coordinate(300, 300));
        BoatObserver bo = boat -> {
        };
        b.addObserver(bo);
        Assert.assertEquals(1, b.getBoatObservers().size());

        b.move(4);

        b.removeObserver(bo);
        Assert.assertEquals(0, b.getBoatObservers().size());
    }

    @Test
    public void testNumberPointBorder(){
        Boat b = new Boat(new SimpleIntegerProperty(1), new Coordinate(300, 300));
        Assert.assertEquals(0, b.getBorders().getPoints().size());
    }


}
