package fr.ensicaen.genielogiciel.mvp;

import java.util.ResourceBundle;

import fr.ensicaen.genielogiciel.mvp.model.LoginModel;
import fr.ensicaen.genielogiciel.mvp.presenter.IPresenter;
import fr.ensicaen.genielogiciel.mvp.presenter.LoginPresenter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class LoginMain extends Application {
    public static void main( String[] args ) {
        launch(args);
    }

    public static ResourceBundle getMessageBundle() {
        return ResourceBundle.getBundle("fr.ensicaen.genielogiciel.mvp.MessageBundle");
    }

    @Override
    public void start( final Stage primaryStage ) throws Exception {
        primaryStage.setTitle("Login screen using MVP");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginscreen.fxml"), getMessageBundle());
        Parent root = loader.load();
        // getController() does return a presenter but a class of the View
        // if we want the presenter independent from the API JavaFX.
        IPresenter view = loader.getController();

        LoginPresenter presenter = new LoginPresenter();
        view.setPresenter(presenter);
        presenter.setView(view);
        LoginModel model = new LoginModel();
        presenter.setModel(model);

        Scene scene = new Scene(root, 400, 120);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
