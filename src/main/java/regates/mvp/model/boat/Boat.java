package regates.mvp.model.boat;

import javafx.beans.property.IntegerProperty;
import lombok.Getter;
import lombok.Setter;
import regates.mvp.model.Border;
import regates.mvp.model.Coordinate;
import regates.mvp.model.utils.FileReader;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Boat implements BoatObservable {

    @Setter
    private static String[] boatSpeeds;

    private double speed;

    @Setter
    private IntegerProperty angle;

    private final List<BoatObserver> boatObservers = new ArrayList<>();

    @Setter
    private Border borders;

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
        for (Coordinate c : this.getBorders().getPoints()){
            if(Coordinate.distance(c, a) < 1){
                return true;
            }
        }
        return false;
    }

    /**
     * Move the boat according to its angle and speed
     *
     * @param windStrength Wind Strength
     */
    public synchronized void move(double speed) {
        try {
            this.borders.resetTranslation();
            this.speed = speed;
            double a = angle.getValue() - 90;
            double adj = speed * Math.cos(Math.toRadians(a));
            double opp = speed * Math.sin(Math.toRadians(a));
            this.borders.setBarycentre(new Coordinate(this.borders.getBarycentre().getX() + adj, this.borders.getBarycentre().getY() + opp));
            this.borders.translateBorders();
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
        this.borders.rotate( -shift);
        notifyObservers();
    }

    /**
     * Add an Observer for the boat
     *
     * @param bo New observer
     */
    public void addObserver(BoatObserver bo) {
        this.boatObservers.add(bo);
    }

    /**
     * Remove an Observer
     *
     * @param bo observer
     */
    public void removeObserver(BoatObserver bo) {
        this.boatObservers.remove(bo);
    }

    /**
     * Send boat data to every Observer
     */
    public void notifyObservers() {
        for (BoatObserver observer : boatObservers) {
            observer.update(this);
        }
    }

    public Coordinate getPosition() {
        return this.borders.getBarycentre();
    }
}
