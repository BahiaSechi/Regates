package regates.mvp.model;

import lombok.NoArgsConstructor;

import java.awt.geom.Point2D;

@NoArgsConstructor
public class Coordinate extends Point2D {

    public Coordinate(double x, double y) {
        setLocation(x, y);
    }

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
