package regates.mvp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

/**
 * Represent the Score of a game
 */
@AllArgsConstructor
@Getter
public class Score {

    private final String player;
    private final float value;
    private final Date date;

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
        return String.format("%s\t\t\t%s\t\t\t%s", player, value, new SimpleDateFormat("dd-MM-yyyy", Locale.FRENCH).format(date));
    }
}
