package regates.mvp.model;

import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private Timer t;
    private TimerTask tt;
    private String configurationFilename;
    private Boat boat;

    public Game() {
        this.boat = new Boat(1, 1, null);
        tt = new TimerTask() {
            @Override
            public void run() {
                // Calcule des nouvelles coordonnées
                System.out.println("aaa");
                boat.setPosition(new Coordinate(boat.getPosition().getX() + 1, boat.getPosition().getY()));
                boat.notifyObservers();
            }
        };
        t = new Timer();
        t.scheduleAtFixedRate(tt, 0, 1000);
    }


    public void launchGame(String s) {

    }
}
