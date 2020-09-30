package regates.mvp.model;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Leaderboard {


    private Leaderboard instance;

    public Leaderboard getInstance() {
        if (instance == null)
            instance = new Leaderboard();
        return instance;
    }

    public void sortByDate() {

    }

    public void sortByName() {

    }

    public void sortByScore() {

    }


}
