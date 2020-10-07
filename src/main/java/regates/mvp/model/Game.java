package regates.mvp.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import javafx.beans.property.SimpleIntegerProperty;
import lombok.Getter;
import lombok.Setter;
import regates.mvp.model.boat.Boat;
import regates.mvp.model.boat.BoatObserver;

import java.io.File;
import java.io.IOException;
import java.time.Clock;
import java.util.*;

/**
 * Represent the main class of the game. Contain the thread responsible for collision, movement and end of the game.
 */
public class Game implements GameObservable {

    private final ResourceBundle bundle = ResourceBundle.getBundle("regates.mvp.MessageBundle", new Locale("fr", "FR"));
    private Timer timer;
    @Getter
    private final Boat boat;

    @Getter
    @Setter
    private String configFile;

    @Getter
    private int order = 0;

    @Getter
    private final List<GameObserver> observers;
    private Clock clock = Clock.systemDefaultZone();
    private long tStart = 0;
    private long tFinish = 0;


    /**
     * Game constructor.
     *
     * @param configFile The path to the map configuration file.
     * @throws Exception If something goes wrong when loading map configuration file
     */
    public Game(String configFile) throws Exception {
        this.observers = new ArrayList<>();
        this.configFile = configFile;
        Config c = this.loadConfiguration();
        Board.getInstance().setWind(new Wind(getClass().getResource("/regates/mvp/windData.txt").getPath()));
        this.checkConfigValidity(c);

        Board.getInstance().getWind().setStrength(c.getWindStrength());

        this.boat = new Boat(new SimpleIntegerProperty(1), c.getStartingPoint());

        Board b = Board.getInstance();
        b.setCheckpoints(c.getCheckpoints());
        b.setBuoys(c.getBuoys());
        b.setCoasts(c.getCoasts());
        b.setWind(Board.getInstance().getWind());
    }

    /**
     * Start the game's thread
     */
    public void start() {
        tStart = clock.millis();

        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                // Calcule des nouvelles coordonnées
                boat.move(Board.getInstance().getWind().determinateSpeed(boat.getAngle().getValue()));
                if (testBuoyCollision() || testCoastCollision() || testBoatExitWindow()) {
                    notifyObservers(false, 0);
                    stop();
                } else if (testCheckpoint(order)) {
                    order++;
                    if (order >= Board.getInstance().getCheckpoints().size()) {
                        tFinish = clock.millis();
                        notifyObservers(true, 1000 - (tFinish - tStart) / 1000);
                        stop();
                    }
                }
            }
        };
        timer = new Timer();
        timer.scheduleAtFixedRate(tt, 0, 100);
    }

    /**
     * Test if any of the boat border hit any of the Buoy border
     *
     * @return True if the boat hit the Buoy
     */
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

    /**
     * Test if any of the boat border exit the window
     *
     * @return True if the boat exit the window
     */
    public synchronized boolean testBoatExitWindow() {
        if (boat.getBorders().isInRotation()) {
            return false;
        }

        for (Coordinate c : boat.getBorders().getPoints()) {
            if (c.getX() < 0 || c.getX() > 1310 || c.getY() < 0 || c.getY() > 983) {
                return true;
            }
        }
        return false;
    }

    /**
     * Test if any of the boat border hit any of the Coast border
     *
     * @return True if the boat hit the Coast
     */
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

    /**
     * Test if the boat is in the Checkpint
     *
     * @param order The order of the checkpoint to test
     * @return True if the boat hit the checkpoint
     */
    public boolean testCheckpoint(int order) {
        for (Coordinate c : boat.getBorders().getPoints()) {
            if (Coordinate.distance(c, Board.getInstance().getCheckpoints().get(order).getPosition()) <= Board.getInstance().getCheckpoints().get(order).getRadius()) {
                return true;
            }

        }
        return false;
    }

    /**
     * Stop the Thread of the game
     */
    public void stop() {
        timer.cancel();
        timer.purge();
    }

    /**
     * Set the game's observer (The controller actually)
     *
     * @param bo The Observer
     */
    public void setBoatObserver(BoatObserver bo) {
        this.boat.addObserver(bo);
    }

    private Config loadConfiguration() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        try {
            return mapper.readValue(new File(this.configFile), Config.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void checkConfigValidity(Config c) throws Exception {
        // Check file
        if (c == null) {
            throw new Exception(this.bundle.getString("error.config_load_error"));
        }
        // Check boat position
        if (c.getStartingPoint().getX() < 0 || c.getStartingPoint().getY() < 0) {
            throw new Exception(this.bundle.getString("error.invalid_start_coordinate"));
        }
        // Check wind strength
        boolean isStrengthValid = false;
        for (int strength : Board.getInstance().getWind().getAvailableStrengths()) {
            if (strength == c.getWindStrength()) {
                isStrengthValid = true;
                break;
            }
        }
        if (!isStrengthValid) {
            throw new Exception(this.bundle.getString("error.invalid_wind_strength"));
        }
    }

    @Override
    public void addObserver(GameObserver go) {
        this.observers.add(go);
    }

    @Override
    public void removeObserver(GameObserver go) {
        this.observers.remove(go);
    }

    @Override
    public void notifyObservers(boolean win, long score) {
        for (GameObserver go : this.observers) {
            if (win) {
                go.win(score);
            } else {
                go.lose();
            }
        }
    }
}
