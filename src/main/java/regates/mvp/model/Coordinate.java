package regates.mvp.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.geom.Point2D;

@NoArgsConstructor
@Getter
@Setter

public class Coordinate   {
    public int X;
    public int Y;
    // Cette variable est a 0 si ces coordonnées sont occuppés pas des bouées et 1 sinon.
    public boolean estVide;


}

