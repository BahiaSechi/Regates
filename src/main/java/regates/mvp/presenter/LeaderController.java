package regates.mvp.presenter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import regates.mvp.model.Leaderboard;
import regates.mvp.model.Score;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class LeaderController implements Initializable {

    @FXML
    private ListView<Score> listV;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Leaderboard l = Leaderboard.getInstance();
            l.readScore("/regates/mvp/scoresData.txt");
            List<Score> list = Leaderboard.getInstance().getScores();
            ObservableList<Score> scores = FXCollections.observableArrayList(list);
            listV.setItems(scores);

        } catch (NullPointerException e) {
            e.getMessage();
        }
    }
}

