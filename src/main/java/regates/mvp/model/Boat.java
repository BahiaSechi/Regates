package regates.mvp.model;

import javafx.beans.property.IntegerProperty;
import lombok.Getter;
import lombok.Setter;
import regates.mvp.model.utils.FileReader;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Boat {

    private static String[] boatSpeeds;
    private double speed;
    private IntegerProperty angle;
    private List<BoatObserver> boatObservers = new ArrayList<>();
    private Border borders;

    public Boat(IntegerProperty degree, Coordinate position) {
        this.angle = degree;
        this.borders = new Border();
        this.borders.setBarycentre(position);
        this.borders.translateBorders();
        Boat.boatSpeeds = FileReader.readFile(getClass().getResource("/regates/mvp/windData.txt").getPath());
    }

    /**
     * Return if the boat is colliding or not
     * @param a Coordinates of another entity
     * @return true if colliding
     */
    public boolean isCollision(Coordinate a) {
        return false;
    }

    /**
     * Determine the speed of the boat according to wind strength and angle
     * @param windStrength Wind Strength
     * @return Boat Speed
     */
    public double determinateSpeed(int windStrength) {
        String[] speedByAngle = Boat.boatSpeeds[Math.abs(this.angle.getValue() % 180)].split(" "); // Extract the line matching the angle
        String[] strengths = Boat.boatSpeeds[0].split(" ");
        int index;
        for (index = 1; index < strengths.length; index++) {
            // Identify column index matching wind strength
            if (strengths[index].equals("" + windStrength)) {
                break;
            }
        }
        return Float.parseFloat(speedByAngle[index]);
    }

    /**
     * Move the boat according to its angle and speed
     * @param windStrength Wind Strength
     */
    public synchronized void move(int windStrength) {
        try {
            this.borders.resetTranslation();
            this.speed = determinateSpeed(windStrength);
            double a = angle.getValue() - 90;
            double adj = speed * Math.cos(Math.toRadians(a));
            double opp = speed * Math.sin(Math.toRadians(a));
            this.borders.barycentre = new Coordinate(this.borders.barycentre.getX() + adj, this.borders.barycentre.getY() + opp);
            this.borders.translateBorders();
            System.out.println(this.borders.getBarycentre());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Rotate the boat
     * @param shift rotation shift in degrees
     */
    public synchronized void rotate(int shift) {
        this.angle.setValue(shift + angle.getValue());
        this.borders.rotate(this.borders.getBarycentre(), -shift);
    }

    /**
     * Add an Observer for the boat
     * @param bo New observer
     */
    public void addObserver(BoatObserver bo){
        this.boatObservers.add(bo);
    }

    /**
     * Remove an Observer
     * @param bo observer
     */
    public void removeObserver(BoatObserver bo){
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
