package regates.mvp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

@AllArgsConstructor
@Setter
@Getter

public class Score {

    private String player;
    private float value;
    private Date date;

    public static Comparator<Score> ComparatorDate = new Comparator<Score>() {
        @Override
        public int compare(Score o1, Score o2) {
            return o2.getDate().compareTo(o1.getDate());
        }
    };

    public static Comparator<Score> ComparatorScore = new Comparator<>() {
        @Override
        public int compare(Score o1, Score o2) {
            return Float.compare(o2.getValue(), o1.getValue());
        }
    };

    public static Comparator<Score> ComparatorPlayer = new Comparator<Score>() {
        @Override
        public int compare(Score o1, Score o2) {
            return o1.getPlayer().compareTo(o2.getPlayer());
        }
    };

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        return player + "\t\t\t" + value + "\t\t\t" + formatter.format(date); // TODO work on proper formatting data

    }
}