package regates.mvp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
