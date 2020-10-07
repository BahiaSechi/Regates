package regates.mvp.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LeaderBoardTest {
    private Leaderboard l;
    private List<Score> scores;
    private List<Score> trie;

    @Before
    public void init(){
        l = Leaderboard.getInstance();
        scores = new ArrayList<>();
        trie = new ArrayList<>();
    }

    @Test
    public void sortByDateTest(){
        l.readScore("score/score.txt");
        scores = l.getScores();
        l.readScore("score/scoreDate.txt");
        trie = l.getScores();
        Assert.assertSame(trie, scores);
        l.sortByScore();
    }

    @Test
    public void sortByScoreTest(){
        l.readScore("score/score.txt");
        scores = l.getScores();
        l.readScore("score/scoreScore.txt");
        trie = l.getScores();
        Assert.assertSame(trie, scores);
        l.sortByScore();
    }

    @Test
    public void sortByNameTest(){
        l.readScore("score/score.txt");
        scores = l.getScores();
        l.readScore("score/scoreName.txt");
        trie = l.getScores();
        Assert.assertSame(trie, scores);
        l.sortByScore();
    }

    @Test
    public void emptySortByDateTest() {
        l.readScore("score/empty.txt");
        l.sortByDate();
    }

    @Test
    public void emptySortByScoreTest() {
        l.readScore("score/empty.txt");
        l.sortByScore();
    }

    @Test
    public void emptySortByNameTest() {
        l.readScore("score/empty.txt");
        l.sortByName();
    }
}
