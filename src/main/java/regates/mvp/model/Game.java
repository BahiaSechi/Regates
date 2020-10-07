package regates.mvp.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Alert;
import lombok.Getter;
import regates.mvp.model.boat.Boat;
import regates.mvp.model.boat.BoatObserver;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Game {

    private Timer t;
    private final Boat boat;
    private Config config;

    @Getter
    private int order = 0;

    public Game() {
        this.loadConfiguration();
        this.boat = new Boat(new SimpleIntegerProperty(0), config.getStartingPoint());
        Board b = Board.getInstance();
        b.setCheckpoints(this.config.getCheckpoints());
        b.setBuoys(this.config.getBuoys());
        b.setCoasts(this.config.getCoasts());
        b.setWindDirection(this.config.getWindDirection());
        b.setWindSpeed(this.config.getWindStrength());
    }

    public void start() {
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                // Calcule des nouvelles coordonnées
                boat.move(4);
                if (testBuoyCollision()||testCoastCollision()) {

                    Platform.runLater(() -> {
                        Alert about = new Alert(Alert.AlertType.INFORMATION);
                        about.setContentText("eng game collision");
                        about.setTitle("end game");
                        about.showAndWait();
                        Platform.exit();
                    });
                    t.cancel();
                    t.purge();

                } else if (testCheckpoint(order)) {
                    order++;
                    if(order==Board.getInstance().getCheckpoints().size()){
                        // TODO gérer le cas où order > taille arraylist --> victoire
                    }
                }
            }
        };
        t = new Timer();
        t.scheduleAtFixedRate(tt, 0, 100);
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
        t.cancel();
        t.purge();
    }

    public Boat getBoat() {
        return this.boat;
    }

    public void setObserver(BoatObserver bo) {
        this.boat.addObserver(bo);
    }

    private void loadConfiguration() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        try {
            this.config = mapper.readValue(new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("regates/mvp/configFiles/conf_normandie.yaml")).getPath()), Config.class);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
