package regates.mvp.model;

import javafx.beans.property.SimpleIntegerProperty;
import lombok.Getter;

import java.util.Timer;
import java.util.TimerTask;

public class Game {
    @Getter
    private Timer t;
    private TimerTask tt;
    private String configurationFilename;
    private Boat boat;

    public Game() {
        this.boat = new Boat(new SimpleIntegerProperty(0), new Coordinate(200, 200));
    }

    public void start() {
        tt = new TimerTask() {
            @Override
            public void run() {
                // Calcule des nouvelles coordonnées
                boat.move(4);
            }
        };
        t = new Timer();
        t.scheduleAtFixedRate(tt, 0, 100);
    }

    public Boat getBoat() {
        return this.boat;
    }

    public void setObserver(BoatObserver bo) {
        this.boat.addObserver(bo);
    }

}
