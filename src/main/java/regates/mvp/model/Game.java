package regates.mvp.model;

import javafx.beans.property.SimpleIntegerProperty;
import lombok.Getter;

import java.time.Clock;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Game {
    @Getter
    private Timer t;
    private TimerTask tt;
    private String configurationFilename;
    private Boat boat;

    //Ajouté par MOMO dev de l'espace!
    //Pour score
    private Clock clock = Clock.systemDefaultZone();
    private long tStart = 0;
    private long tFinish = 0;
    private long tLap = 0;
    //Pour popup
    final JFrame parent = new JFrame();
    JButton button = new JButton();
    private String playerName;


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

    public void launchGame(String s) {
    }

    public String capturePlayerName() {
        //This functio reat a pop-up to capture the player's name and return it.
        playerName = JOptionPane.showInputDialog(parent,
                        "What is your name?", null);
        return playerName;
    }

}
