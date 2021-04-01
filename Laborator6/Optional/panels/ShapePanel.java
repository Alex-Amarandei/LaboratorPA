/**
 * @author Alex Amarandei
 */

package optional.panels;

import optional.MainFrame;
import optional.shapes.ShapeForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ShapePanel extends JPanel {
    final MainFrame frame;
    JButton circleButton;
    JButton triangleButton;
    JButton squareButton;
    JButton polygonButton;

    public ShapePanel(MainFrame frame) {
        this.frame = frame;
        circleButton = new JButton("Circle");
        triangleButton = new JButton("Triangle");
        squareButton = new JButton("Square");
        polygonButton = new JButton("Regular Polygon");
        init();
    }

    public void init() {
        setLayout(new GridLayout(1, 5));

        add(circleButton);
        add(triangleButton);
        add(squareButton);
        add(polygonButton);

        circleButton.addActionListener(this::shapeSelected);
        triangleButton.addActionListener(this::shapeSelected);
        squareButton.addActionListener(this::shapeSelected);
        polygonButton.addActionListener(this::shapeSelected);
    }

    private void shapeSelected(ActionEvent actionEvent) {
        JButton buttonPressed = (JButton) actionEvent.getSource();

        if(buttonPressed.equals(circleButton)) {
            frame.getConfigPanel().getSidesField().setValue(100);
            frame.getCanvas().setShapeForm(ShapeForm.Circle);
        } else if(buttonPressed.equals(triangleButton)) {
            frame.getConfigPanel().getSidesField().setValue(3);
            frame.getCanvas().setShapeForm(ShapeForm.Triangle);
        } else if(buttonPressed.equals(squareButton)) {
            frame.getConfigPanel().getSidesField().setValue(4);
            frame.getCanvas().setShapeForm(ShapeForm.Square);
        } else {
            frame.getConfigPanel().getSidesField().setValue(6);
            frame.getCanvas().setShapeForm(ShapeForm.Polygon);
        }
    }
}
