/**
 * @author Alex Amarandei
 */

package optional.shapes;

import java.awt.*;

public class DrawableShape {
    private final Shape shape;
    private final Color color;

    public DrawableShape(Shape shape, Color color) {
        this.shape = shape;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Shape getShape() {
        return shape;
    }
}
