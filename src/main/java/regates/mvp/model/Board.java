package regates.mvp.model;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Board {

    private Board board;

    private int width; // fixé ?

    private int height;

    private float windDirection;

    private int windSpeed;

    private Date timestamp;

    public Board getInstance(){
        if(board == null)
            board = new Board();
        return board;
    }

    public boolean start(){
        return true;
    }

    public boolean testEnd(){
        return true;
    }

    public Score endGame(){
        return new Score("Maxence", 0, new Date());
    }

}
