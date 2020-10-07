package regates.mvp.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Represent the Leaderboard of a game
 *
 * @Author Elise Le Mazier
 * @Version 1.0
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Leaderboard {

    private List<Score> scores = new ArrayList<>();
    private static Leaderboard instance;

    /**
     * Create a leaderboard instance
     *
     * @return the instance of the leaderboard
     */
    public static Leaderboard getInstance() {
        if (instance == null)
            instance = new Leaderboard();
        return instance;
    }

    /**
     * Allow to read scores from a text file
     *
     * @param path the text file's path
     */
    public void readScore(String path) {    //"/regates/mvp/scoresData.txt"
        String[] buffer;
        Scanner scanner = null;
        try {
            String s = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(path)).getPath();
            File f = new File(s);
            scanner = new Scanner(f);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                buffer = line.split(";");
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                scores.add(new Score(buffer[0], Float.parseFloat(buffer[1]), formatter.parse(buffer[2])));
            }
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }

    }

    /**
     * Allow to sort by date
     */
    public void sortByDate() {
        Collections.sort(scores, Score.ComparatorDate);
    }

    /**
     * Allow to sort by score
     */
    public void sortByScore() {
        Collections.sort(scores, Score.ComparatorScore);
    }

    /**
     * Allow to sort by name
     */
    public void sortByName() {
        Collections.sort(scores, Score.ComparatorPlayer);
    }
}
