package regates.mvp.model;

import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents borders of an image
 * Used to handle collisions
 * @see Coordinate
 */
public class Border {

    @Getter
    private final List<Coordinate> points;

    @Getter
    @Setter
    private Coordinate barycentre;

    @Getter
    private Coordinate imgShift;

    public Border() {
        this.points = new ArrayList<>();
    }

    /**
     * Identify each point part of the boat borders and store it as a List of Coordinates
     * @param img Boat image
     * @param width Image display width
     * @param height Image display height
     */
    public void generateBordersForImage(Image img, double width, double height) {
        int nbPoints = 0;
        double wRatio = width / img.getWidth();
        double hRatio = height / img.getHeight();
        for (int x = 0; x < img.getWidth(); x++)
            for (int y = 0; y < img.getHeight(); y++)
                if (!isTransparent(img, x, y) && hasTransparentNeighbour(img, x, y)) {
                    // Only keep 1 point on 4 to reduce latency
                    // Doesn't have any effect on collision detection accuracy
                    if (nbPoints % 4 == 0) {
                        points.add(new Coordinate(x * wRatio, y * hRatio));
                    }
                    nbPoints++;
                }

        // Move each border point around barycenter
        this.imgShift = this.barycentreCalculation();
        for (Coordinate c : this.points) {
            c.setX(c.getX() - imgShift.getX());
            c.setY(c.getY() - imgShift.getY());
        }
        this.translateBorders();
    }

    /**
     * Calculate barycenter position
     * @return Barycenter position as a Coordinate
     */
    private Coordinate barycentreCalculation() {
        double moyX = 0;
        double moyY = 0;
        for (Coordinate c : this.points) {
            moyX += c.getX();
            moyY += c.getY();
        }
        return new Coordinate(moyX / this.points.size(), moyY / this.points.size());
    }

    /**
     * Rotate every border points matching boat angle
     * @param angle Boat angle
     */
    public void rotate(double angle) {
        resetTranslation(); // Place borders at origin
        // Rotate around origin
        for (Coordinate c : this.points) {
            double radAngle = Math.toRadians(angle);
            double cosAngle = Math.round(Math.cos(radAngle));
            double sinAngle = Math.sin(radAngle);
            c.setX(c.getX() * cosAngle + c.getY() * sinAngle);
            c.setY(-c.getX() * sinAngle + c.getY() * cosAngle);
        }
        translateBorders(); // Place back borders
    }

    /**
     * Move each border point relatively to barycenter
     */
    public void translateBorders() {
        for (Coordinate c : this.points) {
            c.setX(c.getX() + barycentre.getX());
            c.setY(c.getY() + barycentre.getY());
        }
    }

    /**
     * Translate every border point to origin
     */
    public void resetTranslation() {
        for (Coordinate c : this.points) {
            c.setX(c.getX() - barycentre.getX());
            c.setY(c.getY() - barycentre.getY());
        }
    }

    /**
     * Check if pixel has a transparent neighbour
     * @param bi Image
     * @param x Pixel X position
     * @param y Pixel Y position
     * @return true if neighbour is transparent
     */
    private boolean hasTransparentNeighbour(Image bi, int x, int y) {
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++)
                if (x + i < 0 ||
                        x + i >= bi.getWidth() ||
                        y + j < 0 ||
                        y + j >= bi.getHeight() ||
                        isTransparent(bi, x + i, y + j)) {
                    return true;
                }
        return false;
    }

    /**
     * Check if a pixel is transparent
     * @param bi Image
     * @param x Pixel X position
     * @param y Pixel Y position
     * @return true if pixel is transparent
     */
    private boolean isTransparent(Image bi, int x, int y) {
        int pixel = bi.getPixelReader().getArgb(x, y);
        return (pixel >> 24) == 0x00;
    }
}
