package regates.mvp.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents The sea, with Checkpoints and Buoys. Handle Wind.
 *
 * @see Checkpoint
 * @see Buoy
 * @see Coast
 * @see Wind
 */
@Getter
@Setter
public class Board {
    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private static Board board;

    private List<Checkpoint> checkpoints;
    private List<Buoy> buoys;
    private List<Coast> coasts;
    private Wind wind;

    private Board() {
        checkpoints = new ArrayList<>();
        buoys = new ArrayList<>();
        coasts = new ArrayList<>();
        wind = new Wind(getClass().getResource("/regates/mvp/windData.txt").getPath());
    }


    /**
     * Implementation of Singleton design pattern.
     *
     * @return The only instance of Board.
     */
    public static Board getInstance() {
        if (board == null)
            board = new Board();
        return board;
    }

    public Checkpoint getCheckpoint(int order) {
        return checkpoints.get(order);
    }
}
