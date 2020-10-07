package regates.mvp.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Alert;
import lombok.Getter;
import regates.mvp.model.boat.Boat;
import regates.mvp.model.boat.BoatObserver;


import java.io.File;
import java.io.IOException;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Game {

    Locale locale = new Locale("fr", "FR");
    ResourceBundle bundle = ResourceBundle.getBundle("regates.mvp.MessageBundle", locale);
    private Timer timer;
    private final Boat boat;
    private final Wind wind;

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

    Leaderboard leaderboard = Leaderboard.getInstance() ;
    List<Score> scores = new ArrayList<>();


public Game() throws Exception {
        Config c = this.loadConfiguration();
        this.wind = new Wind(getClass().getResource("/regates/mvp/windData.txt").getPath());
        this.checkConfigValidity(c);

        this.wind.setStrength(c.getWindStrength());
        this.boat = new Boat(new SimpleIntegerProperty(1), c.getStartingPoint());

        Board b = Board.getInstance();
        b.setCheckpoints(c.getCheckpoints());
        b.setBuoys(c.getBuoys());
        b.setCoasts(c.getCoasts());
        b.setWind(this.wind);
    }

    public void start() {

        Date date = new Date(); // the variable is initialized with the current date/time !
        Score playerScore = new Score("Player",0,date);
        tStart =  clock.millis(); //timeStamp

        TimerTask tt = new TimerTask() {

            @Override
            public void run() {
                // Calcule des nouvelles coordonnées
                boat.move(wind.determinateSpeed(boat.getAngle().getValue()));
                if (testBuoyCollision()) {
                    System.exit(11);
                } else if (testCoastCollision()) {
                    System.exit(12);
                } else if (testCheckpoint(order)) {
                    order++;
                    if (order == Board.getInstance().getCheckpoints().size()) {
                        tFinish = clock.millis();
                        Alert about = new Alert(Alert.AlertType.INFORMATION);
                        about.setContentText(bundle.getString("game.victory"));
                        about.setTitle(bundle.getString("game.end"));
                        about.show();
                        pScore = 1000 - (tFinish - tStart)/1000;
                        playerScore.setValue(pScore);
                        addPlayer (playerScore, leaderboard);//Ajouter playerScore au fichier .
                    }
                    // TODO gérer le cas où order > taille arraylist --> victoire
                }
            }
        };
        timer = new Timer();
        timer.scheduleAtFixedRate(tt, 0, 100);
    }

    public boolean testBuoyCollision() {
        for (Coordinate c : boat.getBorders().getPoints()) {
            for (Buoy b : Board.getInstance().getBuoys()) {
                if (Coordinate.distance(c, b.getPosition()) <= b.getRadius()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean testCoastCollision() {
        for (Coast c : Board.getInstance().getCoasts()) {
            for (Coordinate coo : c.getBorders().getPoints()) {
                if (boat.isCollision(coo)) {
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
        timer.cancel();
        timer.purge();
    }

    public Boat getBoat() {
        return this.boat;
    }

    public void setObserver(BoatObserver bo) {
        this.boat.addObserver(bo);
    }
    public String capturePlayerName() {
        String playerName;
        //This function reat a pop-up to capture the player's name and return it.
        playerName = JOptionPane.showInputDialog(parent,
                bundle.getString("game.playername"), null);
        return playerName;
    }

    public void addPlayer(Score playerScore, Leaderboard leaderboard) {
        List<Score> scores = new ArrayList<>();
        Score min;
        leaderboard.sortByScore();
        scores = leaderboard.getScores();
        min = scores.get(scores.size() - 1);
        if (leaderboard.getScores().size() < 100 ) {
            playerScore.setPlayer(capturePlayerName());
            leaderboard.getScores().add(playerScore);
        }
        else if (playerScore.getValue() > min.getValue()) {
            playerScore.setPlayer(capturePlayerName());
            leaderboard.getScores().remove(leaderboard.getScores().size() - 1);
            leaderboard.getScores().add(playerScore);
        }
    }

    private Config loadConfiguration() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        try {
            return mapper.readValue(new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("regates/mvp/configFiles/conf_normandie.yaml")).getPath()), Config.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void checkConfigValidity(Config c) throws Exception {
        // Check file
        if (c == null) {
            throw new Exception(ResourceBundle.getBundle("error.config_load_error").toString());
        }
        // Check boat position
        if (c.getStartingPoint().getX() < 0 || c.getStartingPoint().getY() < 0) {
            throw new Exception(ResourceBundle.getBundle("error.invalid_start_coordinate").toString());
        }
        // Check wind strength
        boolean isStrengthValid = false;
        for (int strength : this.wind.getAvailableStrengths()) {
            if (strength == c.getWindStrength()) {
                isStrengthValid = true;
                break;
            }
        }
        if (!isStrengthValid) {
            throw new Exception(ResourceBundle.getBundle("error.invalid_wind_strength").toString());
        }
    }

}
