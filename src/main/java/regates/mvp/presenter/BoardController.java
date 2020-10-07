package regates.mvp.presenter;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import regates.mvp.model.Coordinate;
import regates.mvp.model.Game;
import regates.mvp.model.*;
import regates.mvp.model.boat.Boat;
import regates.mvp.model.boat.BoatObserver;

import java.text.MessageFormat;
import java.util.*;

/**
 * Link between Model and View
 * Handle players interactions and dynamic display
 * @see Boat
 * @see Border
 * @see Coast
 * @see Coordinate
 */
public class BoardController implements BoatObserver, GameObserver {
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

    private final ResourceBundle bundle = ResourceBundle.getBundle("regates.mvp.MessageBundle", new Locale("fr", "FR"));
    private Stage stage;
    private Game game;


    /**
     * Define the scene key listeners
     * @param scene Game scene
     */
    public void setScene(Stage s, Scene scene) {
        this.stage = s;
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                game.getBoat().rotate(-2);
            } else if (event.getCode() == KeyCode.RIGHT) {
                game.getBoat().rotate(+2);
            }
        });
    }

    @Override
    public void update(Boat boat) {
        Platform.runLater(() -> {
            // Display the boat
            regate.setLayoutX(boat.getPosition().getX() - boat.getBorders().getImgShift().getX());
            regate.setLayoutY(boat.getPosition().getY() - boat.getBorders().getImgShift().getY());

            // Display the checkpoint
            Checkpoint next = Board.getInstance().getCheckpoint(this.game.getOrder());
            AnchorPane.setLeftAnchor(labelCheckpoint, next.getPosition().getX() + nextCheckpoint.getRadius() * 0.9);
            AnchorPane.setTopAnchor(labelCheckpoint, next.getPosition().getY() + nextCheckpoint.getRadius() * 0.9);
            AnchorPane.setLeftAnchor(nextCheckpoint, next.getPosition().getX());
            AnchorPane.setTopAnchor(nextCheckpoint, next.getPosition().getY());
            labelCheckpoint.setText(String.valueOf(next.getOrder()));
            nextCheckpoint.setRadius(next.getRadius());
            txtSpeed.setText((Math.round(boat.getSpeed() * 10) / 10.0) + " nd");
            txtCap.setText((boat.getAngle().getValue() > 0 ? Math.abs(boat.getAngle().getValue() % 360) : 360 - Math.abs(boat.getAngle().getValue() % 360)) + " °");
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

    /**
     * Start the game
     * @param configPath Absolute path of the map configuration file
     */
    public void startGame(String configPath) {
        try {
            // Load the game
            this.game = new Game(configPath);
        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText(e.getMessage());
            error.setTitle("Erreur");
            error.showAndWait();
            this.stage.close();
        }

        this.game.setBoatObserver(this);
        this.game.addObserver(this);

        // Rotate boat and wheel according to player rotation
        game.getBoat().getAngle().addListener((o, oldValue, newValue) -> {
            regate.setRotate(newValue.doubleValue());
            imgWheel.setRotate(newValue.doubleValue());
        });
        // Generate collision borders for boat
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

            // Draw buoys radius
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
        }

        this.txtStrength.setText(Board.getInstance().getWind().getStrength() + " nd");
        this.txtWind.setText(Board.getInstance().getWind().getDirection() + " °");
        game.start();
    }

    /**
     * End the game and ask player his name if his score reaches Top 100
     * @param score Player score
     */
    @Override
    public void win(long score) {
        Platform.runLater(() -> {
            Leaderboard.getInstance().sortByScore();
            List<Score> scores = Leaderboard.getInstance().getScores();
            Score min = null;
            if (scores.size() > 0) {
                min = scores.get(scores.size() - 1);
            }
            if (Leaderboard.getInstance().getScores().size() < 100 ) {
                String playerName = capturePlayerName(score);
                Leaderboard.getInstance().getScores().add(new Score(playerName, score, new Date()));
            } else if (min == null || score > min.getValue()) {
                String playerName = capturePlayerName(score);
                Leaderboard.getInstance().getScores().remove(Leaderboard.getInstance().getScores().size() - 1);
                Leaderboard.getInstance().getScores().add(new Score(playerName, score, new Date()));
            } else {
                Alert about = new Alert(Alert.AlertType.INFORMATION);
                about.setTitle(this.bundle.getString("dialog.win.title"));
                about.setHeaderText(MessageFormat.format(this.bundle.getString("dialog.win.header"), score));
                about.showAndWait();
            }
            this.stage.close();
        });
    }

    /**
     * End the game and display a defeat popup
     */
    @Override
    public void lose() {
        Platform.runLater(() -> {
            Alert about = new Alert(Alert.AlertType.INFORMATION);
            about.setTitle(this.bundle.getString("dialog.lost.title"));
            about.setHeaderText(this.bundle.getString("dialog.lost.header"));
            about.showAndWait();
            this.stage.close();
        });
    }

    /**
     * Open popup to ask player his pseudo
     * @param score Player score
     * @return Player's pseudo
     */
    private String capturePlayerName(long score) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(this.bundle.getString("dialog.win.title"));
        dialog.setHeaderText(MessageFormat.format(this.bundle.getString("dialog.win.header"), score));
        dialog.setContentText(this.bundle.getString("dialog.win.enterName"));

        Optional<String> result = dialog.showAndWait();
        return result.orElseGet(() -> capturePlayerName(score));
    }
}
