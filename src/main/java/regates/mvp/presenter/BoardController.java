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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import regates.mvp.model.*;
import regates.mvp.model.Boat;
import regates.mvp.model.BoatObserver;
import regates.mvp.model.Coordinate;
import regates.mvp.model.Game;

import java.net.URL;
import java.util.ArrayList;

import java.util.List;
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
    AnchorPane gameBoard;



    @FXML
    Label labelCheckpoint;

    @FXML
    Circle nextCheckpoint;


    private Game game;
    private Scene scene;

    // Debug display
    private List<Rectangle> r;
    private final Circle c = new Circle();
    // Debug display

    /**
     * Handle menu about.
     *
     */
    public void handleAbout() {
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
        game.getBoat().getBorders().generateBordersForImage(
                game.getBoat().getPosition(),
                regate.getImage(),
                regate.getFitWidth(),
                regate.getFitHeight()
        );
        game.start();

        // TODO add debug config
        if (true) {
            r = new ArrayList<>();
            for (Coordinate ignored : game.getBoat().getBorders().getPoints()) {
                Rectangle rec = new Rectangle();
                rec.setWidth(1);
                rec.setHeight(1);
                rec.setFill(Color.RED);
                this.r.add(rec);
                this.gameBoard.getChildren().add(rec);
            }
            c.resize(10, 10);
            c.setStroke(Color.GREEN);
            c.setStrokeWidth(10);
            c.setFill(Color.GREEN);
            this.gameBoard.getChildren().add(c);
        }
    }

    @Override
    public void update(Boat boat) {
        regate.setLayoutX(boat.getPosition().getX() - boat.getBorders().getImgshift().getX());
        regate.setLayoutY(boat.getPosition().getY() - boat.getBorders().getImgshift().getY());

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
            AnchorPane.setLeftAnchor(labelCheckpoint, next.getPosition().getX() + nextCheckpoint.getRadius()*0.9);
            AnchorPane.setTopAnchor(labelCheckpoint, next.getPosition().getY() + nextCheckpoint.getRadius()*0.9);
            AnchorPane.setLeftAnchor(nextCheckpoint, next.getPosition().getX());
            AnchorPane.setTopAnchor(nextCheckpoint, next.getPosition().getY());
            labelCheckpoint.setText(String.valueOf(next.getOrder()));
            nextCheckpoint.setRadius(next.getRadius());
            txtSpeed.setText((Math.round(boat.getSpeed() * 10) / 10.0) + " nd");
            txtCap.setText(boat.getAngle().getValue() + " °");

            if (true) {
                // Barycentre
                c.setLayoutX(boat.getBorders().getBarycentre().getX());
                c.setLayoutY(boat.getBorders().getBarycentre().getY());

                // Borders
                for ( int j = 0; j < game.getBoat().getBorders().getPoints().size(); j++) {
                    this.r.get(j).setLayoutX(game.getBoat().getBorders().getPoints().get(j).getX());
                    this.r.get(j).setLayoutY(game.getBoat().getBorders().getPoints().get(j).getY());
                }
            }
        });
        }else{
            System.out.println("Fin du jeu");
        }
    }
}
