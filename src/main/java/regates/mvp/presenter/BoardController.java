package regates.mvp.presenter;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import regates.mvp.model.Coordinate;
import regates.mvp.model.Game;
import regates.mvp.model.*;
import regates.mvp.model.boat.Boat;
import regates.mvp.model.boat.BoatObserver;

import javax.xml.transform.Result;
import java.net.URL;
import java.util.*;

public class BoardController implements Initializable, BoatObserver {
    @FXML
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
    // Debug display
    private List<Rectangle> r;
    private List<Rectangle> r2;
    private final Circle c = new Circle();
    // Debug display


    public void setScene(Scene scene) {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                game.getBoat().rotate(-1);
            } else if (event.getCode() == KeyCode.RIGHT) {
                game.getBoat().rotate(+1);
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.game = new Game();
        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText(e.getMessage());
            error.setTitle("Erreur");
            error.showAndWait();
            System.exit(-1);
        }

        this.game.setObserver(this);
        game.getBoat().getAngle().addListener((o, oldValue, newValue) -> {
            regate.setRotate(newValue.doubleValue());
            imgWheel.setRotate(newValue.doubleValue());
        });
        game.getBoat().getBorders().generateBordersForImage(
                regate.getImage(),
                regate.getFitWidth(),
                regate.getFitHeight()
        );

        // Draw buoys
        for (Buoy b : Board.getInstance().getBuoys()) {
            ImageView buoy = new ImageView();
            buoy.setImage(
                    new Image(
                            Objects.requireNonNull(
                                    Thread.currentThread().getContextClassLoader().getResourceAsStream("regates/mvp/img/map/buoy.png")
                            )
                    )
            );
            buoy.setFitHeight(67);
            buoy.setFitWidth(66);
            buoy.setLayoutX(b.getPosition().getX() - buoy.getFitWidth() / 2);
            buoy.setLayoutY(b.getPosition().getY() - buoy.getFitHeight() / 2);

            Circle buoyRadius = new Circle();
            buoyRadius.setRadius(b.getRadius());
            buoyRadius.setStroke(Color.BLACK);
            buoyRadius.setStrokeWidth(3);
            buoyRadius.setCenterX(b.getPosition().getX());
            buoyRadius.setCenterY(b.getPosition().getY());
            buoyRadius.setFill(new Color(0, 0, 0, 0));
            this.gameBoard.getChildren().add(buoy);
            this.gameBoard.getChildren().add(buoyRadius);
        }

        // Draw coasts
        for (Coast coast : Board.getInstance().getCoasts()) {
            ImageView coastIV = new ImageView();
            coastIV.setImage(
                    new Image(
                            Objects.requireNonNull(
                                    Thread.currentThread().getContextClassLoader().getResourceAsStream(coast.getImgPath())
                            )
                    )
            );
            coastIV.setFitWidth(coast.getSize().getX());
            coastIV.setFitHeight(coast.getSize().getY());
            coast.getBorders().generateBordersForImage(coastIV.getImage(), coastIV.getFitWidth(), coastIV.getFitHeight());
            coastIV.setLayoutX(coast.getPosition().getX() - coast.getBorders().getImgShift().getX());
            coastIV.setLayoutY(coast.getPosition().getY() - coast.getBorders().getImgShift().getY());
            this.gameBoard.getChildren().add(coastIV);

            this.txtStrength.setText(Board.getInstance().getWind().getStrength() + " nd");
            this.txtWind.setText(Board.getInstance().getWind().getDirection() + " °");
            game.start();
        }

        // TODO add debug config
        if (true) {
            r = new ArrayList<>();
            r2 = new ArrayList<>();
            for (Coordinate ignored : game.getBoat().getBorders().getPoints()) {
                Rectangle rec = new Rectangle();
                rec.setWidth(1);
                rec.setHeight(1);
                rec.setFill(Color.RED);
                this.r.add(rec);
                this.gameBoard.getChildren().add(rec);
            }
            for (Coordinate ignored : Board.getInstance().getCoasts().get(0).getBorders().getPoints()) {
                Rectangle rec = new Rectangle();
                rec.setWidth(1);
                rec.setHeight(1);
                rec.setFill(Color.RED);
                this.r2.add(rec);
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

        Platform.runLater(() -> {
            regate.setLayoutX(boat.getPosition().getX() - boat.getBorders().getImgShift().getX());
            regate.setLayoutY(boat.getPosition().getY() - boat.getBorders().getImgShift().getY());

            Checkpoint next = Board.getInstance().getCheckpoint(this.game.getOrder());
            AnchorPane.setLeftAnchor(labelCheckpoint, next.getPosition().getX() + nextCheckpoint.getRadius() * 0.9);
            AnchorPane.setTopAnchor(labelCheckpoint, next.getPosition().getY() + nextCheckpoint.getRadius() * 0.9);
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

                // Boat Borders
                for (int j = 0; j < game.getBoat().getBorders().getPoints().size(); j++) {
                    this.r.get(j).setLayoutX(game.getBoat().getBorders().getPoints().get(j).getX());
                    this.r.get(j).setLayoutY(game.getBoat().getBorders().getPoints().get(j).getY());
                }

                // Coast Borders
                for (int j = 0; j < Board.getInstance().getCoasts().get(0).getBorders().getPoints().size(); j++) {
                    this.r2.get(j).setLayoutX(Board.getInstance().getCoasts().get(0).getBorders().getPoints().get(j).getX());
                    this.r2.get(j).setLayoutY(Board.getInstance().getCoasts().get(0).getBorders().getPoints().get(j).getY());
                }
            }
        });
    }

    // MENU FUNCTIONS

    /**
     * Handle menu about.
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

    /**
     * Handle exit the game.
     */
    public void handleExit() {
        exitGame();
    }

    /**
     * Exit the game and finish the thread.
     */
    public void exitGame() {
        game.stop();
        Platform.exit();
    }
}
