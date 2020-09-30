package regates.mvp.presenter;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import regates.mvp.model.Boat;
import regates.mvp.model.BoatObserver;
import regates.mvp.model.Game;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardController implements Initializable, BoatObserver {
    @FXML
    ImageView regate;

    private Game game;
    private Scene scene;

    /**
     * Handle menu about.
     *
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


    public void setScene(Scene scene) {
        this.scene = scene;
        this.scene.setOnKeyPressed(event -> {
            System.out.println("hello");
            if (event.getCode() == KeyCode.LEFT) {
                regate.setLayoutX(regate.getLayoutX() - 2);
            } else if (event.getCode() == KeyCode.RIGHT) {
                regate.setLayoutX(regate.getLayoutX() + 2);
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.game = new Game();
        this.game.setObserver(this);
    }

    @Override
    public void update(Boat boat) {
        System.out.println("update");
        regate.setLayoutX(0);
        regate.setLayoutY(0);
        regate.setRotate(boat.getAngle());
    }
}
