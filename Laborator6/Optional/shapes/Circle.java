/**
 * @author Alex Amarandei
 */

package optional.shapes;

import java.awt.*;

public class Circle extends Polygon {
    public Circle(int x0, int y0, int radius) {
        double alpha = 2 * Math.PI / 100;
        for (int i = 0; i < 100; i++) {
            double x = x0 + radius * Math.cos(alpha * i);
            double y = y0 + radius * Math.sin(alpha * i);
            this.addPoint((int) x, (int) y);
        }
    }
}
