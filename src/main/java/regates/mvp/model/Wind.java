package regates.mvp.model;

import lombok.Getter;
import lombok.Setter;
import regates.mvp.model.utils.FileReader;

public class Wind {
    private final String[] fileContent;
    @Getter
    private final int[] availableStrengths;
    @Getter
    @Setter
    private int strength;
    @Getter
    @Setter
    private int direction;

    public Wind(String path) {
        this.fileContent = FileReader.readFile(path);
        this.availableStrengths = new int[this.fileContent[0].split(" ").length - 1];
        int tabIndex = 0;
        for (int i = 0; i < this.fileContent[0].split(" ").length; i++) {
            try {
                int st = Integer.parseInt(this.fileContent[0].split(" ")[i]);
                this.availableStrengths[tabIndex++] = st;
            } catch (Exception ignored) {
            }
        }
    }


    /**
     * Determine the speed of the boat according to wind strength and angle
     *
     * * @return Boat Speed
     */
    public double determinateSpeed(int angle) {
        String[] speedByAngle = this.fileContent[Math.abs(angle % 180)].split(" "); // Extract the line matching the angle
        int index;
        for (index = 0; index < this.availableStrengths.length; index++) {
            // Identify column index matching wind strength
            if (this.availableStrengths[index] == this.strength) {
                break;
            }
        }
        return Float.parseFloat(speedByAngle[index + 1]);
    }
}
