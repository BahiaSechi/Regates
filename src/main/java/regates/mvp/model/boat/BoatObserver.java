package regates.mvp.model.boat;

public interface BoatObserver {
    /**
     * Update observer with new boat data
     * @param boat Boat data
     */
    void update(Boat boat);
}
