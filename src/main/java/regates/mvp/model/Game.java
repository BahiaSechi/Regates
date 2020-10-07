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
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Game {

    private Timer timer;
    private final Boat boat;
    private final Wind wind;

    @Getter
    @Setter
    private String configFile;

    @Getter
    private int order = 0;

    public Game(String configFile) throws Exception {
        this.configFile = configFile;
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
