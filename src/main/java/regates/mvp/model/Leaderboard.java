package regates.mvp.model;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Leaderboard {

    private List<Score> scores = new ArrayList<>();
    private Leaderboard instance;

    public Leaderboard getInstance() {
        if (instance == null)
            instance = new Leaderboard();
        return instance;
    }

    public void readScore() {

        try {
            Scanner scanner = new Scanner(new File("../../../resources/regates/mvp/scoresData.txt"));
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String[] buffer = new String[3];
                buffer = line.split(";");
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                scores.add(new Score(Float.parseFloat(buffer[0]), formatter.parse(buffer[1]), buffer[1]));
            }
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void sortByDate() {
        Collections.sort(scores, Score.ComparatorDate);
    }

    public void sortByScore() {
        Collections.sort(scores, Score.ComparatorScore);
    }
}
