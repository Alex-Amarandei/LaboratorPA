package optional.shapes;

import java.awt.*;

public class Triangle extends Polygon {
    public Triangle(int x0, int y0, int radius) {
        this.addPoint(x0, y0 - (int) (0.66 * radius));
        this.addPoint(x0 + (int) (0.66 * radius), y0 + (int) (0.33 * radius));
        this.addPoint(x0 - (int) (0.66 * radius), y0 + (int) (0.33 * radius));
    }
}
