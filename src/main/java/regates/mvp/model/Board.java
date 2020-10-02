package regates.mvp.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;

public class Board {

    @Getter
    private ArrayList<Checkpoint> checkpoints = new ArrayList<>();

    private static Board board;

    private int width; // fixé ?

    private int height;

    private float windDirection;

    private int windSpeed;

    private Date timestamp;

    private Board() {
        checkpoints.add(new Checkpoint(0, new Coordinate(200, 15), 30));
        checkpoints.add(new Checkpoint(1, new Coordinate(50, 50), 3));
        checkpoints.add(new Checkpoint(2, new Coordinate(50, 50), 3));

    }

    public static Board getInstance() {
        if (board == null)
            board = new Board();
        return board;
    }

    public boolean start() {
        return true;
    }

    public boolean testEnd() {
        return true;
    }

    public Score endGame() {
        return new Score(0, new Date(), "Maxence");
    }

}
