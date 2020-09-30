package regates.mvp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Boat {

    private float degree;
    private float speed;
    private Coordinate position;
    private List<BoatObserver> boatObservers = new ArrayList<>();

    public boolean isCollision(Coordinate a, Coordinate b) {
        return true;
    }

    public void addObserver(BoatObserver bo){
        this.boatObservers.add(bo);
    }

    public void removeObserver(BoatObserver bo){
        this.boatObservers.remove(bo);
    }

    public void notifyObservers() {
        for (BoatObserver observer : boatObservers) {
            observer.update(this);
        }
    }
}
