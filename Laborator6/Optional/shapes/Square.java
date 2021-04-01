/**
 * @author Alex Amarandei
 */

package optional.shapes;

import java.awt.*;

public class Square extends Polygon {
    public Square(int x0, int y0, int radius) {
        this.addPoint(x0 - radius, y0 - radius);
        this.addPoint(x0 + radius, y0 - radius);
        this.addPoint(x0 + radius, y0 + radius);
        this.addPoint(x0 - radius, y0 + radius);
    }
}
