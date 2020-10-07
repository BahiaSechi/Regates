package regates.mvp.presenter;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Objects;

/**
 * The map view's controller. Aim to change to loaded map in the game.
 */
public class MapController {

    private Stage stage;
    private MenuController mc;

    /**
     * Triggered when cliking on the custom map button
     */
    public void customClick() {
        FileChooser fileChooser = new FileChooser();
        File chosenFile = fileChooser.showOpenDialog(stage);
        this.mc.setConfigPath(chosenFile.getAbsolutePath());
        this.mc.editImage("regates/mvp/img/custom.png");
        this.stage.close();
    }

    /**
     * Triggered when cliking on the Cotentin map button
     */
    public void handleCotentin() {
        this.mc.setConfigPath(Objects.requireNonNull(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("regates/mvp/configFiles/conf_normandie.yaml")).getPath()));
        this.mc.editImage("regates/mvp/img/map/cotentin_map.png");
        this.stage.close();
    }

    /**
     * Triggered when cliking on the Corsica map button
     */
    public void handleCorsica() {
        this.mc.setConfigPath(Objects.requireNonNull(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("regates/mvp/configFiles/conf_corse.yaml")).getPath()));
        this.mc.editImage("regates/mvp/img/map/corsica_map.png");
        this.stage.close();
    }

    /**
     * Triggered when cliking on the Scandinavia map button
     */
    public void handleScandinavia() {
        this.mc.setConfigPath(Objects.requireNonNull(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("regates/mvp/configFiles/conf_scandinavia.yaml")).getPath()));
        this.mc.editImage("regates/mvp/img/map/scandinavia_map.png");
        this.stage.close();
    }

    /**
     * Function to get the stage we are in.
     *
     * @param stage Stage we are in.
     */
    void setStage(Stage stage) {
        this.stage = stage;
    }

    void setMenuController(MenuController mc) {
        this.mc = mc;
        this.mc.setConfigPath(Objects.requireNonNull(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("regates/mvp/configFiles/conf_normandie.yaml")).getPath()));
    }
}
