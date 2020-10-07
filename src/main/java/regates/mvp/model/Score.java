package regates.mvp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

/**
 * Represent the Score of a game
 *
 * @author Elise Le Mazier
 * @version 1.0
 */
@AllArgsConstructor
@Setter
@Getter

public class Score {

    private String player;
    private float value;
    private Date date;

    /**
     * Allow to compare two scores by date
     */
    public static final Comparator<Score> ComparatorDate = (o1, o2) -> o2.getDate().compareTo(o1.getDate());

    /**
     * Allow to compare two scores by score
     */
    public static final Comparator<Score> ComparatorScore = (o1, o2) -> Float.compare(o2.getValue(), o1.getValue());

    /**
     * Allow to compare two scores by player's name
     */
    public static final Comparator<Score> ComparatorPlayer = Comparator.comparing(Score::getPlayer);


    @Override
    public String toString() {
        // TODO work on proper formatting data
        return player + "\t\t\t" + value + "\t\t\t" + new SimpleDateFormat("dd-MM-yyyy", Locale.FRENCH).format(date);
    }
}

