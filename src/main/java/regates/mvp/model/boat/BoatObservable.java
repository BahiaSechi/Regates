package regates.mvp.model.boat;

public interface BoatObservable {
    /**
     * Add an Observer for the boat
     *
     * @param observer New observer
     */
    void addObserver(BoatObserver observer);

    /**
     * Remove an Observer
     *
     * @param observer Observer to remove
     */
    void removeObserver(BoatObserver observer);

    /**
     * Send boat data to every Observer
     */
    void notifyObservers();
}
