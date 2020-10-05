package regates.mvp.model;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private Timer t;
    private TimerTask tt;
    private String configurationFilename;
    private Boat boat;

    public Game() {
        this.boat = new Boat(new SimpleIntegerProperty(0), new Coordinate(500, 300));
        tt = new TimerTask() {
            @Override
            public void run() {
                // Calcule des nouvelles coordonnées
                boat.move(4);
                boat.notifyObservers();
                if(testCollision()){
                    System.exit(11);
                }

            }
        };
        t = new Timer();
        t.scheduleAtFixedRate(tt, 0, 54);
    }

    public boolean testCollision() {
        for (Coordinate c : boat.getBorders().getPoints()) {
            for (Buoy b : Board.getInstance().getListBuoy()) {
                if (Coordinate.distance(c, b.getPosition()) <= b.getRadius()) {
                    return true;
                }
            }
        }
        return false;
    }

    public Boat getBoat() {
        return this.boat;
    }

    public void setObserver(BoatObserver bo) {
        this.boat.addObserver(bo);
    }


    public void launchGame(String s) {

    }
}
