package regates.mvp.vue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class BoardView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Regate game");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/regates/mvp/BoardView.fxml"));
        Parent root = loader.load();

        primaryStage.setScene(new Scene(root, 1167, 951));
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public static void main( String[] args ) {
        launch(args);
    }

    /**
     * Handle menu about. (TODO peut etre le changer de place, je voulais surtout tester si tout fonctionnait)
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
}
