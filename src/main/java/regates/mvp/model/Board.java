package regates.mvp.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;

public class Board {

    @Getter
    private ArrayList<Checkpoint> checkpoints = new ArrayList<>();

    private static Board board;
    @Getter
    private ArrayList<Buoy> listBuoy = new ArrayList<>();

    private int width; // fixé ?

    private int height;

    private float windDirection;

    private int windSpeed;

    private Date timestamp;


    private Board() {
        checkpoints.add(new Checkpoint(0, new Coordinate(200, 15), 30));
        checkpoints.add(new Checkpoint(1, new Coordinate(500, 600), 30));
        checkpoints.add(new Checkpoint(2, new Coordinate(50, 50), 3));
        listBuoy.add(new Buoy(1, 4, new Coordinate(464, 51), "yolo"));
    }

    public static Board getInstance() {
        if (board == null)
            board = new Board();
        return board;
    }

    public Checkpoint getCheckpoint(int order) {
        return checkpoints.get(order);
    }

    public boolean start() {
        return true;
    }

    public boolean testEnd() {
        return true;
    }


}
