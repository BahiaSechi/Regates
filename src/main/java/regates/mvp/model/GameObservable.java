package regates.mvp.model;

public interface GameObservable {
    void addObserver(GameObserver go);
    void removeObserver(GameObserver go);
    void notifyObservers(boolean win, long score);
}
