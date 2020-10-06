package regates.mvp.model;

import javafx.beans.property.SimpleIntegerProperty;
import lombok.Getter;

import java.util.Timer;
import java.util.TimerTask;

public class Game {

    private Timer t;
    private TimerTask tt;
    private String configurationFilename;
    private Boat boat;
    private int order = 0;

    public Game() {
        this.boat = new Boat(new SimpleIntegerProperty(0), new Coordinate(200, 200));
    }

    public void start() {
        tt = new TimerTask() {
            @Override
            public void run() {
                // Calcule des nouvelles coordonnées
                boat.move(4);
                boat.notifyObservers();
                if (testCollision()) {
                    System.exit(11);
                }
                if(testCheckpoint(order)){
                    order++;
                    moveCheckPoint();
                }
            }
        };
        t = new Timer();
        t.scheduleAtFixedRate(tt, 0, 100);
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

    public boolean testCheckpoint(int order) {
        for (Coordinate c : boat.getBorders().getPoints()) {
                if (Coordinate.distance(c, Board.getInstance().getCheckpoints().get(order).getPosition()) <= Board.getInstance().getCheckpoints().get(order).getRadius()) {
                    return true;
                }

        }
        return false;
    }
    public void moveCheckPoint(){

    }

    public void stop() {
        t.cancel();
        t.purge();
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
