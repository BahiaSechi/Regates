package regates.mvp.presenter;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import regates.mvp.model.Boat;
import regates.mvp.model.BoatObserver;
import regates.mvp.model.Game;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardController implements Initializable, BoatObserver {
    @FXML
    ImageView regate;
    @FXML
    Label txtCap, txtStrength, txtSpeed, txtWind;
    @FXML
    ImageView imgWheel;

    private Game game;
    private Scene scene;

    /**
     *
     * @param scene
     */
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

    /**
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.game = new Game();
        this.game.setObserver(this);
    }

    /**
     *
     * @param boat
     */
    @Override
    public void update(Boat boat) {
        regate.setLayoutX(boat.getPosition().getX());
        regate.setLayoutY(boat.getPosition().getY());
        regate.setRotate(boat.getAngle());
        imgWheel.setRotate(boat.getAngle());

        Platform.runLater(() -> {
            txtSpeed.setText(boat.getSpeed() + " nd");
            txtCap.setText(boat.getAngle() + " °");
        });
    }

    // MENU FUNCTIONS

    /**
     * Handle menu about.
     * @param actionEvent
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

    public void handleExit(ActionEvent actionEvent) {
        exitGame();
    }

    public void exitGame() {
        game.getT().cancel();
        game.getT().purge();
        Platform.exit();
    }
}
