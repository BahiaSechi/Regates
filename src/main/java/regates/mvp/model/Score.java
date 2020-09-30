package regates.mvp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Setter
@Getter
public class Score {

    private float value;
    private Date date;
    private String player;

}
