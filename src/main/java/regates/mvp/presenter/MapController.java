package regates.mvp.presenter;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Objects;

public class MapController {

    private Stage stage;
    private MenuController mc;

    public void customClick() {
        FileChooser fileChooser = new FileChooser();
        File chosenFile = fileChooser.showOpenDialog(stage);
        this.mc.setConfigPath(chosenFile.getAbsolutePath());
        this.mc.editImage("regates/mvp/img/custom.png");
        this.stage.close();
    }

    public void handleCotentin() {
        this.mc.setConfigPath(Objects.requireNonNull(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("regates/mvp/configFiles/conf_normandie.yaml")).getPath()));
        this.mc.editImage("regates/mvp/img/map/cotentin_map.png");
        this.stage.close();
    }

    public void handleCorsica() {
        this.mc.setConfigPath(Objects.requireNonNull(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("regates/mvp/configFiles/conf_corse.yaml")).getPath()));
        this.mc.editImage("regates/mvp/img/map/corsica_map.png");
        this.stage.close();
    }

    public void handleScandinavia() {
        this.mc.setConfigPath(Objects.requireNonNull(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("regates/mvp/configFiles/conf_scandinavia.yaml")).getPath()));
        this.mc.editImage("regates/mvp/img/map/scandinavia_map.png");
        this.stage.close();
    }
    /**
     * Function to get the stage we are in.
     * @param stage Stage we are in.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
        }

    public void setMenuController(MenuController mc) {
        this.mc = mc;
        this.mc.setConfigPath(Objects.requireNonNull(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("regates/mvp/configFiles/conf_normandie.yaml")).getPath()));
    }
}
