package regates.mvp.model;

import javafx.beans.property.SimpleIntegerProperty;
import lombok.Getter;
import regates.mvp.model.boat.Boat;
import regates.mvp.model.boat.BoatObserver;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Game {

    private Timer t;
    private String configurationFilename;
    private final Boat boat;
    @Getter
    private int order = 0;

    //Ajouté par MOMO dev de l'espace!
    //Pour score
    private Clock clock = Clock.systemDefaultZone();
    private long tStart = 0;
    private long tFinish = 0;
    private long pScore = 0;
    //Pour popup
    final JFrame parent = new JFrame();
    JButton button = new JButton();



    public Game() {
        this.boat = new Boat(new SimpleIntegerProperty(0), new Coordinate(200, 200));
    }

    public void start() {

        Date date = new Date(); // the variable is initialized with the current date/time !
        Score playerScore = new Score("Player",0,date);
        tStart =  clock.millis(); //timeStamp

        TimerTask tt = new TimerTask() {

            @Override
            public void run() {
                // Calcule des nouvelles coordonnées
                boat.move(4);
                if (testCollision()) {
                    System.exit(11);
                }else if(testCheckpoint(order)){
                    order++;
                    // TODO gérer le cas où order > taille arraylist --> victoire
                }
            }
        };
        tFinish = clock.millis();
        pScore = 1000 - (tFinish - tStart)/1000;
        playerScore.setValue(pScore);
        if (true) {     //Mettre a la place de true si playerScore.value est supérrieur au dernier score enregistré
            playerScore.setPlayer(capturePlayerName()); //capturePlayerName est le popup qui réccupere le nom du joueur
            //Ajouter playerScore au fichier .
        }

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

    public String capturePlayerName() {
        String playerName;
        //This functio reat a pop-up to capture the player's name and return it.
        playerName = JOptionPane.showInputDialog(parent,
                        "What is your name?", null);
        return playerName;
    }

}
