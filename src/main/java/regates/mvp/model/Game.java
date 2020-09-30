package regates.mvp.model;

import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private Timer t;
    private TimerTask tt;
    private String configurationFilename;
    private Boat boat;

    public Game() {

        tt = new TimerTask() {
            @Override
            public void run() {
                // Calcule des nouvelles coordonnées
                boat.setPosition(new Coordinate(boat.getPosition().getX() + 1, boat.getPosition().getY()));

            }
        };
        t = new Timer();
        t.scheduleAtFixedRate(tt, 0, 1000);
    }


    public void launchGame(String s) {

    }

    public static void main(String[] args) {
        Game g = new Game();
        for (; ; ) {

        }
    }


}
