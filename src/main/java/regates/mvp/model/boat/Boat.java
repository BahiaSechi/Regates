package regates.mvp.model.boat;

import javafx.beans.property.IntegerProperty;
import lombok.Getter;
import lombok.Setter;
import regates.mvp.model.Border;
import regates.mvp.model.Coordinate;

import java.util.ArrayList;
import java.util.List;

/**
 * Player controllable boat
 * @see Coordinate
 * @see Border
 */
@Getter
public class Boat implements BoatObservable {
    private double speed;
    @Setter
    private IntegerProperty angle;
    @Setter
    private Border borders;
    private final List<BoatObserver> boatObservers = new ArrayList<>();

    /**
     * Boat constructor
     * @param degree Initial orientation of the boat in degrees
     * @param position Initial position of the boat
     */
    public Boat(IntegerProperty degree, Coordinate position) {
        this.angle = degree;
        this.borders = new Border();
        this.borders.setBarycentre(position);
        this.borders.translateBorders();
    }

    /**
     * Return if the boat is colliding or not
     *
     * @param a Coordinates of another entity
     * @return true if colliding
     */
    public boolean isCollision(Coordinate a) {
        for (Coordinate c : this.getBorders().getPoints()) {
            if (Coordinate.distance(c, a) < 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Move the boat according to its angle and speed
     *
     * @param speed Speed according to wind strength
     */
    public synchronized void move(double speed) {
        try {
            this.borders.resetTranslation();
            this.speed = speed;
            double a = angle.getValue() - 90.0;
            double adj = speed * Math.cos(Math.toRadians(a));
            double opp = speed * Math.sin(Math.toRadians(a));
            this.borders.setBarycentre(new Coordinate(this.borders.getBarycentre().getX() + adj, this.borders.getBarycentre().getY() + opp));
            this.borders.translateBorders();
            System.out.println(this.getPosition());
            notifyObservers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Rotate the boat
     *
     * @param shift rotation shift in degrees
     */
    public synchronized void rotate(int shift) {
        this.angle.setValue(shift + angle.getValue());
        this.borders.rotate(-shift);
        notifyObservers();
    }


    public void addObserver(BoatObserver bo) {
        this.boatObservers.add(bo);
    }

    public void removeObserver(BoatObserver bo) {
        this.boatObservers.remove(bo);
    }

    public void notifyObservers() {
        for (BoatObserver observer : boatObservers) {
            observer.update(this);
        }
    }

    /**
     * Returns the boat position
     *
     * @return Coordinate of the barycenter
     */
    public Coordinate getPosition() {
        return this.borders.getBarycentre();
    }
}
