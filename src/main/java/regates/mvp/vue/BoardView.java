package regates.mvp.vue;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import regates.mvp.presenter.BoardController;

public class BoardView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Regate game");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/regates/mvp/BoardView.fxml"));
        Parent root = loader.load();
        BoardController bc = loader.getController();
        Scene scene = new Scene(root, 1167, 951);

        bc.setScene(scene);

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }


    public static void main( String[] args ) {
        launch(args);
    }

}
