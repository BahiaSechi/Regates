package regates.mvp.model;

import lombok.Getter;
import lombok.Setter;
import regates.mvp.model.utils.FileReader;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Boat {

    private float angle;
    private static String[] boatSpeeds;
    private float degree;
    private float speed;
    private Coordinate position;
    private List<BoatObserver> boatObservers = new ArrayList<>();

    public Boat(float degree, float speed, Coordinate position) {
        this.degree = degree;
        this.speed = speed;
        this.position = position;
        Boat.boatSpeeds = FileReader.readFile(getClass().getResource("/regates/mvp/windData.txt").getPath());
    }

    public void setPosition(Coordinate c) {
        System.out.println(c);
        this.position = c;
    }

    /**
     * Return if the boat is colliding or not
     * @param a Coordinates of another entity
     * @return true if colliding
     */
    public boolean isCollision(Coordinate a) {
        return a.equals(this.position);
    }

    /**
     * Determine the speed of the boat according to wind strength and angle
     * @param windStrength Wind Strength
     * @param angle Angle between boat and wind
     * @return Boat Speed
     * @throws Exception If angle is invalid
     */
    public float determinateSpeed(int windStrength, int angle) throws Exception {
        if (angle < 1 || angle > 180) {
            throw new Exception("Invalid angle");
        }

        String[] speedByAngle = Boat.boatSpeeds[angle].split(" "); // Extract the line matching the angle
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
