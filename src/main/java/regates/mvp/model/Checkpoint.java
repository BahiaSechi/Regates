package regates.mvp.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class Checkpoint {
    @NonNull
    private int order;

    @NonNull
    private Coordinate position;

    @NonNull
    private double radius;

    @Setter
    private boolean valid;
}
