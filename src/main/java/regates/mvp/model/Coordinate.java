package regates.mvp.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Coordinate   {
    private int x;
    private int y;

    @Override
    public String toString() {
        return this.x + " : " + this.y;
    }
}
