package regates.mvp.presenter;

import regates.mvp.model.boat.BoatObserver;

public interface Observable {
    void addObserver(BoatObserver observer);

    void removeObserver(BoatObserver observer);

    void notifyObservers();
}
