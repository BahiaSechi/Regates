package regates.mvp.presenter;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * The menu view's controller. Aim to handled event when starting a game, change a map, exit the program.
 */
public class MenuController implements Initializable {

    private final ResourceBundle bundle = ResourceBundle.getBundle("regates.mvp.MessageBundle", new Locale("fr", "FR"));
    private static final String TITLE = "general.game_title";
    @Setter
    private String configPath = Objects.requireNonNull(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("regates/mvp/configFiles/conf_normandie.yaml")).getPath());
    @FXML
    private ImageView imgGame;

    public void handleExit() {
        Platform.exit();
    }

    /**
     * Open the changing map view
     */
    public void chooseMap() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/regates/mvp/MapView.fxml"), bundle);
            Parent root2 = fxmlLoader.load();
            Scene scene = new Scene(root2, 1310, 983);
            Stage stage = new Stage();
            MapController mc = fxmlLoader.getController();
            mc.setStage(stage);
            mc.setMenuController(this);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(bundle.getString(TITLE));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.requestFocus();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Open the game view
     */
    public void playGame() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/regates/mvp/BoardView.fxml"), bundle);
            Parent root1 = fxmlLoader.load();
            BoardController bc = fxmlLoader.getController();
            Scene scene = new Scene(root1, 1310, 983);
            Stage stage = new Stage();
            bc.setScene(stage, scene);
            bc.startGame(this.configPath);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(bundle.getString(TITLE));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.requestFocus();
            stage.show();
            stage.setOnCloseRequest(event -> bc.exitGame());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Display the leaderboard
     */
    public void displayLeader() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/regates/mvp/LeaderView.fxml"), bundle);
            Parent root3 = fxmlLoader.load();
            Scene scene = new Scene(root3, 1310, 983);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(bundle.getString(TITLE));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.requestFocus();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the menu image according to the map loaded
     *
     * @param imgPath The path to the image
     */
    void editImage(String imgPath) {
        this.imgGame.setImage(
                new Image(
                        Objects.requireNonNull(
                                Thread.currentThread().getContextClassLoader().getResourceAsStream(imgPath)
                        )
                )
        );
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.editImage("regates/mvp/img/map/cotentin_map.png");
    }
}