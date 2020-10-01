package regates.mvp.presenter;

import regates.mvp.model.BoatObserver;

import java.util.Observer;

public interface Observable {
    void addObserver(BoatObserver observer);

    void removeObserver(BoatObserver observer);

    void notifyObservers();
}
