package regates.mvp.presenter;

import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class MapController {

    private Stage stage;

    /**
     * When you click on the custom ImageView, you can load a file.
     * @param mouseEvent
     */
    public void customClick(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(stage);
    }

    /**
     * Function to get the stage we are in.
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
        }
}
