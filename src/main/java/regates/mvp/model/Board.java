package regates.mvp.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Board {

    private static Board board;

    @Getter
    @Setter
    private List<Checkpoint> checkpoints;
    @Getter
    @Setter
    private List<Buoy> buoys;
    @Getter
    @Setter
    private List<Coast> coasts;
    private int width; // fixé ?
    private int height;
    @Getter
    @Setter
    private Wind wind;

    private Board() {
    }

    public static Board getInstance() {
        if (board == null)
            board = new Board();
        return board;
    }

    public Checkpoint getCheckpoint(int order) {
        return checkpoints.get(order);
    }
}
