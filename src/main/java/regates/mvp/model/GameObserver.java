package regates.mvp.model;

public interface GameObserver {
    void win(long score);
    void lose();
}
