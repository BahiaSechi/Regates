package regates.mvp.model;

import javafx.beans.property.SimpleIntegerProperty;

import java.time.Clock;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private Timer t;
    private TimerTask tt;
    private String configurationFilename;
    private Boat boat;

    //Ajouté par MOMO dev de l'espace!
    private Clock clock = Clock.systemDefaultZone();
    private long tStart = 0;
    private long tFinish = 0;
    private long tLap = 0;



    public Game() {
        this.boat = new Boat(new SimpleIntegerProperty(0), new Coordinate(200, 200));
        tt = new TimerTask() {
            @Override
            public void run() {
                // Calcule des nouvelles coordonnées
                boat.move(4);
                boat.notifyObservers();
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


    public void launchGame(String s) {
        // Cette partie permet de calculer le temps du lap
        // Ajouté au lancement de la partie.
        tStart = clock.millis();

        //Ajouté juste apres la fin de la partie
        tFinish = clock.millis();

        //Le Score en seconde est alors :
        tLap = (tStart - tFinish) / 1000;
    }
}
