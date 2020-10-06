package regates.mvp.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Setter
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Leaderboard {

    private final List<Score> scores = new ArrayList<>();
    private static Leaderboard instance;

    public static Leaderboard getInstance() {
        if (instance == null)
            instance = new Leaderboard();
        return instance;
    }

    public void readScore(String path) {
        String[] buffer;
        try (Scanner scanner = new Scanner(
                new File(Objects.requireNonNull(
                        Thread.currentThread().getContextClassLoader().getResource(path)).getPath()))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                buffer = line.split(";");
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                scores.add(new Score(buffer[0], Float.parseFloat(buffer[1]), formatter.parse(buffer[2])));
            }
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void sortByDate() {
        scores.sort(Score.ComparatorDate);
    }

    public void sortByScore() {
        scores.sort(Score.ComparatorScore);
    }

    public void sortByName() {
        scores.sort(Score.ComparatorPlayer);
    }

}
