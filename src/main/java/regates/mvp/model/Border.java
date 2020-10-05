package regates.mvp.model;

import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Border {
    @Getter
    List<Coordinate> points;
    @Getter
    @Setter
    Coordinate barycentre;
    @Getter
    Coordinate imgshift;

    public Border() {
        this.points = new ArrayList<>();
    }

    public void generateBordersForImage(Coordinate origin, Image img, double width, double height) {
        double wRatio = width / img.getWidth();
        double hRatio = height / img.getHeight();
        for (int x = 0; x < img.getWidth(); x++)
            for (int y = 0; y < img.getHeight(); y++)
                if (!isTransparent(img, x, y) && hasTransparentNeighbour(img, x, y)) {
                    points.add(new Coordinate(x * wRatio, y * hRatio));
                }

        this.imgshift = this.barycentreCalculation();
        for (Coordinate c : this.points) {
            c.setX(c.getX() - imgshift.getX());
            c.setY(c.getY() - imgshift.getY());
        }
        this.translateBorders();
    }

    private Coordinate barycentreCalculation() {
        double moyX = 0, moyY = 0;
        for (Coordinate c : this.points) {
            moyX += c.getX();
            moyY += c.getY();
        }
        return new Coordinate(moyX / this.points.size(), moyY / this.points.size());
    }

    public void rotate(Coordinate origin, double angle) {
        resetTranslation();
        for (Coordinate c : this.points) {
            double radAngle = Math.toRadians(angle);
            double cosAngle = Math.round(Math.cos(radAngle));
            double sinAngle = Math.sin(radAngle);
            c.setX(c.getX() * cosAngle + c.getY() * sinAngle);
            c.setY(-c.getX() * sinAngle + c.getY() * cosAngle);
        }
        translateBorders();
    }

    public void translateBorders() {
        for (Coordinate c : this.points) {
            c.setX(c.getX() + barycentre.getX());
            c.setY(c.getY() + barycentre.getY());
        }
    }

    public void resetTranslation() {
        for (Coordinate c : this.points) {
            c.setX(c.getX() - barycentre.getX());
            c.setY(c.getY() - barycentre.getY());
        }
    }

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

    private boolean isTransparent(Image bi, int x, int y) {
        int pixel = bi.getPixelReader().getArgb(x, y);
        return (pixel >> 24) == 0x00;
    }
}
