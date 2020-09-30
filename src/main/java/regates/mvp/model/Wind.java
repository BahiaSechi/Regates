package regates.mvp.model;

public class Wind {


    private float windSpeed;
    private float windAngle;

    public Wind(float windSpeed, float windAngle) {
        this.windSpeed = windSpeed;
        this.windAngle = windAngle;
    }

    public Wind() {

    }

    public float getCurrentSpeed(float angle) {
        return 0f;
    }


}
