package regates.mvp.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class MenuView extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Locale locale = new Locale("fr", "FR");
        ResourceBundle bundle = ResourceBundle.getBundle("regates.mvp.MessageBundle", locale);

        primaryStage.setTitle(bundle.getString("general.game_title"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/regates/mvp/MenuView.fxml"), bundle);
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
