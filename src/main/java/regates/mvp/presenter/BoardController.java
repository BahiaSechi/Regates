package regates.mvp.presenter;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import lombok.Getter;
import regates.mvp.model.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BoardController implements Initializable, BoatObserver {
    @FXML
    @Getter
    ImageView regate;
    @FXML
    Label txtCap;
    @FXML
    Label txtStrength;
    @FXML
    Label txtSpeed;
    @FXML
    Label txtWind;
    @FXML
    ImageView imgWheel;

    @FXML
    AnchorPane board;

    @FXML
    Label labelCheckpoint;

    @FXML
    Circle nextCheckpoint;


    private Game game;
    private Scene scene;

    /**
     * Handle menu about.
     *
     * @param actionEvent UI event
     */
    public void handleAbout(javafx.event.ActionEvent actionEvent) {
        Alert about = new Alert(Alert.AlertType.INFORMATION);
        about.setContentText("This project is part of the software engineering course (ENSICAEN - Engineering School). \n" +
                "Authors : ALOUACHE Loan & BURON Manfred \n" +
                "FAVE Anthony & HESLOUIN Alexis \n" +
                "LE MAZIER Elise & MORIN Maxence \n" +
                "RICH Mohamed & SECHI Bahia \n" +
                "Date : September 2020 \n" +
                "Version : 1.0");
        about.setTitle("Regate - About");
        about.show();
    }


    public void setScene(Scene scene) {
        this.scene = scene;
        this.scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                game.getBoat().rotate(-1);
            } else if (event.getCode() == KeyCode.RIGHT) {
                game.getBoat().rotate(+1);
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.game = new Game();
        this.game.setObserver(this);
        game.getBoat().getAngle().addListener((o, oldValue, newValue) -> {
            regate.setRotate(newValue.doubleValue());
            imgWheel.setRotate(newValue.doubleValue());
        });
    }

    @Override
    public void update(Boat boat) {
        regate.setLayoutX(boat.getPosition().getX());
        regate.setLayoutY(boat.getPosition().getY());

        ArrayList<Checkpoint> checkpoints = Board.getInstance().getCheckpoints();
        int i = 0;
        while (i < checkpoints.size() && checkpoints.get(i).isValid()) {
            i++;
        }

        if (i < checkpoints.size()) {

            Checkpoint next = checkpoints.get(i);

            double distance = Math.sqrt(Math.pow(boat.getPosition().getX() + regate.getBoundsInParent().getWidth() / 2 - next.getPosition().getX(), 2) + Math.pow(boat.getPosition().getY() + regate.getBoundsInParent().getHeight() / 2 - next.getPosition().getY(), 2));
            System.out.println(next.getPosition() + " " + boat.getPosition() + " " + Math.round(distance));
            if (distance <= next.getRadius()) {
                next.setValid(true);
            }
            System.out.println(next.isValid());

            Platform.runLater(() -> {
                AnchorPane.setLeftAnchor(labelCheckpoint, next.getPosition().getX());
                AnchorPane.setTopAnchor(labelCheckpoint, next.getPosition().getY());
                AnchorPane.setLeftAnchor(nextCheckpoint, next.getPosition().getX() - next.getRadius());
                AnchorPane.setTopAnchor(nextCheckpoint, next.getPosition().getY() - next.getRadius());
                labelCheckpoint.setText(String.valueOf(next.getOrder()));
                nextCheckpoint.setRadius(next.getRadius());

                txtSpeed.setText(boat.getSpeed() + " nd");
                txtCap.setText(boat.getAngle().getValue() + " °");
            });

        }else{
            System.out.println("Fin du jeu");
        }

    }
}
