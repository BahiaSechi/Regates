package regates.mvp.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.awt.geom.Point2D;

@NoArgsConstructor
@AllArgsConstructor
public class Coordinate extends Point2D {
    @Override
    public double getX() {
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }


    @Override
    public void setLocation(double x, double y) {

    }
    //Create a vector

}