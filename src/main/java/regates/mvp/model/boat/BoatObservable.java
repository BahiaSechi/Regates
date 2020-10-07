package regates.mvp.model.boat;

public interface BoatObservable {
    void addObserver(BoatObserver observer);
    void removeObserver(BoatObserver observer);
    void notifyObservers();
}
