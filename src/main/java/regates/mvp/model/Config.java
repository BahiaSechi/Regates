package regates.mvp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Represent the configuration of map. Used when loading a new map to link data with Jackson.
 *
 * @see Coordinate
 * @see Buoy
 * @see Checkpoint
 * @see Coast
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Config {
    private Coordinate startingPoint;
    private List<Buoy> buoys;
    private List<Checkpoint> checkpoints;
    private List<Coast> coasts;
    private int windDirection;
    private int windStrength;
}
