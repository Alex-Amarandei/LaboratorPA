package optional;

import optional.panels.ConfigPanel;
import optional.panels.ControlPanel;
import optional.panels.DrawingPanel;
import optional.panels.ShapePanel;
import optional.shapes.DrawableShape;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    ShapePanel shapePanel;
    DrawingPanel canvas;
    List<DrawableShape> shapes = new ArrayList<>();

    public MainFrame() {
        super("My Drawing Application");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        configPanel = new ConfigPanel(this);
        controlPanel = new ControlPanel(this);
        canvas = new DrawingPanel(this);
        shapePanel = new ShapePanel(this);

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());
        northPanel.add(configPanel, BorderLayout.NORTH);
        northPanel.add(shapePanel, BorderLayout.SOUTH);

        add(northPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        pack();
    }

    public ConfigPanel getConfigPanel() {
        return configPanel;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public DrawingPanel getCanvas() {
        return canvas;
    }

    public List<DrawableShape> getShapes() {
        return shapes;
    }

    public void setShapes(List<DrawableShape> shapes) {
        this.shapes = shapes;
    }

    public ShapePanel getShapePanel() {
        return shapePanel;
    }
}
