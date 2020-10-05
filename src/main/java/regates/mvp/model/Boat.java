package regates.mvp.model;

import lombok.Getter;
import lombok.Setter;
import regates.mvp.model.utils.FileReader;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Boat {

    private static String[] boatSpeeds;
    private int angle;
    private double degree;
    private double speed;
    private Coordinate position;
    private List<BoatObserver> boatObservers = new ArrayList<>();

    /**
     *
     * @param degree
     * @param position
     */
    public Boat(int degree, Coordinate position) {
        this.angle = degree;
        this.position = position;
        Boat.boatSpeeds = FileReader.readFile(getClass().getResource("/regates/mvp/windData.txt").getPath());
    }

    /**
     * Return if the boat is colliding or not.
     * @param a Coordinates of another entity
     * @return true if colliding
     */
    public boolean isCollision(Coordinate a) {
        return a.equals(this.position);
    }

    /**
     * Determine the speed of the boat according to wind strength and angle
     * @param windStrength Wind Strength
     * @return Boat Speed
     */
    public double determinateSpeed(int windStrength) {
        String[] speedByAngle = Boat.boatSpeeds[Math.abs(this.angle % 180)].split(" "); // Extract the line matching the angle
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
     *
     * @param windStrength
     */
    public void move(int windStrength) {
        try {
            double speed = 3; // TODO use determinateSpeed
            int adj = (int)Math.floor(speed * Math.cos(this.angle));
            int opp = (int)Math.floor(speed * Math.sin(this.angle));
            this.position = new Coordinate(this.position.getX() - adj, this.position.getY() - opp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param shift
     */
    public void rotate(int shift) {
        if (shift == -1 && this.angle - 1 == 0) {
            this.angle = 360;
        } else if (shift == 1 && this.angle + 1 == 361) {
            this.angle = 1;
        } else {
            this.angle += shift;
        }
    }

    public void addObserver(BoatObserver bo){
        this.boatObservers.add(bo);
    }

    public void removeObserver(BoatObserver bo){
        this.boatObservers.remove(bo);
    }

    public void notifyObservers() {
        for (BoatObserver observer : boatObservers) {
            observer.update(this);
        }
    }
}
