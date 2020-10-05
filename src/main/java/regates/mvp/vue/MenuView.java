package regates.mvp.vue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuView extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Regate game");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/regates/mvp/MenuView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 1310, 983);

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);

    }

    public static void main( String[] args ) {
        launch(args);
    }
}
