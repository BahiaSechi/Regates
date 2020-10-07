package regates.mvp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.Date;

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
    public static Comparator<Score> ComparatorDate = new Comparator<Score>() {
        @Override
        public int compare(Score o1, Score o2) {
            return o2.getDate().compareTo(o1.getDate());
        }
    };

    /**
     * Allow to compare two scores by score
     */
    public static Comparator<Score> ComparatorScore = new Comparator<>() {
        @Override
        public int compare(Score o1, Score o2) {
            return Float.compare(o2.getValue(), o1.getValue());
        }
    };

    /**
     * Allow to compare two scores by player's name
     */
    public static Comparator<Score> ComparatorPlayer = new Comparator<Score>() {
        @Override
        public int compare(Score o1, Score o2) {
            return o1.getPlayer().compareTo(o2.getPlayer());
        }
    };

}
