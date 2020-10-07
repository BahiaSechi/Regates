package regates.mvp.presenter;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MapController {

    private Stage stage;

    public void customClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.showOpenDialog(stage);
    }

    /**
     * Function to get the stage we are in.
     * @param stage Stage we are in.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
        }
}
