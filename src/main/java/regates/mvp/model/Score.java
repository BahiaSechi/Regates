package regates.mvp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.Date;

@AllArgsConstructor
@Setter
@Getter
public class Score {

    private float value;
    private Date date;
    private String player;


    public static Comparator<Score> ComparatorDate = new Comparator<Score>() {
        @Override
        public int compare(Score o1, Score o2) {
            return o1.getDate().compareTo(o2.getDate());
        }
    };

    public static Comparator<Score> ComparatorScore = new Comparator<Score>() {
        @Override
        public int compare(Score o1, Score o2) {
            return Float.compare(o1.getValue(), o2.getValue());
        }
    };

}
