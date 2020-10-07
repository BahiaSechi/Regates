package regates.mvp.presenter;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class MenuController {

    Locale locale = new Locale("fr", "FR");
    ResourceBundle bundle = ResourceBundle.getBundle("regates.mvp.MessageBundle", locale);
    static final String TITLE = "general.game_title";

    public void handleExit() {
        Platform.exit();
    }

    public void chooseMap() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/regates/mvp/MapView.fxml"), bundle);
            Parent root2 = fxmlLoader.load();
            Scene scene = new Scene(root2, 1310, 983);
            Stage stage = new Stage();
            MapController mc = fxmlLoader.getController();
            mc.setStage(stage);
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

    public void playGame() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/regates/mvp/BoardView.fxml"), bundle);
            Parent root1 = fxmlLoader.load();
            BoardController bc = fxmlLoader.getController();
            Scene scene = new Scene(root1, 1310, 983);
            bc.setScene(scene);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(bundle.getString(TITLE));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.requestFocus();
            stage.show();
            stage.setOnCloseRequest(event-> bc.exitGame());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayLeader() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/regates/mvp/MapView.fxml"), bundle);
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
}