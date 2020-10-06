package regates.mvp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

@AllArgsConstructor
@Setter
@Getter

public class gScore {

    private String player;
    private float value;
    private Date date;

    public static final Comparator<Score> ComparatorDate = (o1, o2) -> o2.getDate().compareTo(o1.getDate());

    public static final Comparator<Score> ComparatorScore = (o1, o2) -> Float.compare(o2.getValue(), o1.getValue());

    public static final Comparator<Score> ComparatorPlayer = Comparator.comparing(Score::getPlayer);

    @Override
    public String toString() {
        // TODO work on proper formatting data
        return player + "\t\t\t" + value + "\t\t\t" + new SimpleDateFormat("dd-MM-yyyy", Locale.FRENCH).format(date);
    }
}
