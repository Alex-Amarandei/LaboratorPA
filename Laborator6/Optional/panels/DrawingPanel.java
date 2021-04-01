package optional.panels;

import optional.MainFrame;
import optional.shapes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class DrawingPanel extends JPanel {
    final static int W = 2560, H = 1600;
    ShapeForm shapeForm;
    final MainFrame frame;
    BufferedImage image;
    Graphics2D graphics;

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        init();
    }

    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, W, H);
    }

    private void init() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());

        shapeForm = ShapeForm.Polygon;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (frame.getConfigPanel().isDeletionSelected()) {
                    frame.getShapes()
                            .remove(frame.getShapes().stream().parallel()
                                    .filter(s -> s.getShape().contains(new Point(e.getX(), e.getY())))
                                    .reduce((a, b) -> b).orElse(null));
                    graphics.setColor(Color.WHITE);
                    graphics.fillRect(0, 0, W, H);
                    frame.getShapes()
                            .forEach(s -> {
                                graphics.setColor(s.getColor());
                                graphics.fill(s.getShape());
                            });
                } else {
                    drawShape(e.getX(), e.getY());
                }
                repaint();
            }
        });
    }

    private void drawShape(int x, int y) {
        if (shapeForm.equals(ShapeForm.Triangle)) {
            drawTriangle(x, y);
        } else if (shapeForm.equals(ShapeForm.Circle)) {
            drawCircle(x, y);
        } else if(shapeForm.equals(ShapeForm.Square)) {
            drawSquare(x, y);
        } else {
            drawPolygon(x, y);
        }
    }

    private void drawPolygon(int x, int y) {
        boolean randomnessSelected = frame.getConfigPanel().isRandomnessSelected();
        int radius;
        int sides;
        int opacity;

        if (randomnessSelected) {
            radius = (int) (Math.random() * 1000) % W / 2;
            sides = (int) (Math.random() * 9 + 1);
            Color color = new Color((int) (Math.random() * 255),
                    (int) (Math.random() * 255),
                    (int) (Math.random() * 255),
                    (int) (Math.random() * 255));
            graphics.setColor(color);
        } else {
            radius = frame.getConfigPanel().getShapeSize();
            sides = frame.getConfigPanel().getSides();
            opacity = frame.getConfigPanel().getOpacity();

            switch (frame.getConfigPanel().getColorCombo().getSelectedIndex()) {
                case 0 -> graphics.setColor(new Color(0, 0, 0, opacity));
                case 1 -> graphics.setColor(new Color(220, 20, 60, opacity));
                case 2 -> graphics.setColor(new Color(255, 140, 0, opacity));
                case 3 -> graphics.setColor(new Color(255, 215, 0, opacity));
                case 4 -> graphics.setColor(new Color(50, 205, 50, opacity));
                case 5 -> graphics.setColor(new Color(0, 128, 255, opacity));
                case 6 -> graphics.setColor(new Color(240, 130, 230, opacity));
                case 7 -> graphics.setColor(new Color(140, 50, 200, opacity));
                default -> {
                    Random random = new Random();
                    Color color = new Color(Math.abs(random.nextInt() % 255),
                            Math.abs(random.nextInt() % 255),
                            Math.abs(random.nextInt() % 255),
                            Math.abs(random.nextInt() % 255));
                    graphics.setColor(color);
                }
            }

        }
        graphics.fill(new RegularPolygon(x, y, radius, sides));
        DrawableShape shape = new DrawableShape(new RegularPolygon(x, y, radius, sides), graphics.getColor());
        frame.getShapes().add(shape);
    }

    private void drawTriangle(int x, int y) {
        boolean randomnessSelected = frame.getConfigPanel().isRandomnessSelected();
        int radius;
        int opacity;

        if (randomnessSelected) {
            radius = (int) (Math.random() * 1000) % W / 2;
            Color color = new Color((int) (Math.random() * 255),
                    (int) (Math.random() * 255),
                    (int) (Math.random() * 255),
                    (int) (Math.random() * 255));
            graphics.setColor(color);
        } else {
            radius = frame.getConfigPanel().getShapeSize();
            opacity = frame.getConfigPanel().getOpacity();

            switch (frame.getConfigPanel().getColorCombo().getSelectedIndex()) {
                case 0 -> graphics.setColor(new Color(0, 0, 0, opacity));
                case 1 -> graphics.setColor(new Color(220, 20, 60, opacity));
                case 2 -> graphics.setColor(new Color(255, 140, 0, opacity));
                case 3 -> graphics.setColor(new Color(255, 215, 0, opacity));
                case 4 -> graphics.setColor(new Color(50, 205, 50, opacity));
                case 5 -> graphics.setColor(new Color(0, 128, 255, opacity));
                case 6 -> graphics.setColor(new Color(240, 130, 230, opacity));
                case 7 -> graphics.setColor(new Color(140, 50, 200, opacity));
                default -> {
                    Random random = new Random();
                    Color color = new Color(Math.abs(random.nextInt() % 255),
                            Math.abs(random.nextInt() % 255),
                            Math.abs(random.nextInt() % 255),
                            Math.abs(random.nextInt() % 255));
                    graphics.setColor(color);
                }
            }

        }
        graphics.fill(new Triangle(x, y, radius));
        DrawableShape shape = new DrawableShape(new RegularPolygon(x, y, radius, 3), graphics.getColor());
        frame.getShapes().add(shape);
    }

    private void drawCircle(int x, int y) {
        boolean randomnessSelected = frame.getConfigPanel().isRandomnessSelected();
        int radius;
        int opacity;

        if (randomnessSelected) {
            radius = (int) (Math.random() * 1000) % W / 2;
            Color color = new Color((int) (Math.random() * 255),
                    (int) (Math.random() * 255),
                    (int) (Math.random() * 255),
                    (int) (Math.random() * 255));
            graphics.setColor(color);
        } else {
            radius = frame.getConfigPanel().getShapeSize();
            opacity = frame.getConfigPanel().getOpacity();

            switch (frame.getConfigPanel().getColorCombo().getSelectedIndex()) {
                case 0 -> graphics.setColor(new Color(0, 0, 0, opacity));
                case 1 -> graphics.setColor(new Color(220, 20, 60, opacity));
                case 2 -> graphics.setColor(new Color(255, 140, 0, opacity));
                case 3 -> graphics.setColor(new Color(255, 215, 0, opacity));
                case 4 -> graphics.setColor(new Color(50, 205, 50, opacity));
                case 5 -> graphics.setColor(new Color(0, 128, 255, opacity));
                case 6 -> graphics.setColor(new Color(240, 130, 230, opacity));
                case 7 -> graphics.setColor(new Color(140, 50, 200, opacity));
                default -> {
                    Random random = new Random();
                    Color color = new Color(Math.abs(random.nextInt() % 255),
                            Math.abs(random.nextInt() % 255),
                            Math.abs(random.nextInt() % 255),
                            Math.abs(random.nextInt() % 255));
                    graphics.setColor(color);
                }
            }

        }
        graphics.fill(new Circle(x, y, radius));
        DrawableShape shape = new DrawableShape(new RegularPolygon(x, y, radius, 100), graphics.getColor());
        frame.getShapes().add(shape);
    }

    private void drawSquare(int x, int y) {
        boolean randomnessSelected = frame.getConfigPanel().isRandomnessSelected();
        int radius;
        int opacity;

        if (randomnessSelected) {
            radius = (int) (Math.random() * 1000) % W / 2;
            Color color = new Color((int) (Math.random() * 255),
                    (int) (Math.random() * 255),
                    (int) (Math.random() * 255),
                    (int) (Math.random() * 255));
            graphics.setColor(color);
        } else {
            radius = frame.getConfigPanel().getShapeSize();
            opacity = frame.getConfigPanel().getOpacity();

            switch (frame.getConfigPanel().getColorCombo().getSelectedIndex()) {
                case 0 -> graphics.setColor(new Color(0, 0, 0, opacity));
                case 1 -> graphics.setColor(new Color(220, 20, 60, opacity));
                case 2 -> graphics.setColor(new Color(255, 140, 0, opacity));
                case 3 -> graphics.setColor(new Color(255, 215, 0, opacity));
                case 4 -> graphics.setColor(new Color(50, 205, 50, opacity));
                case 5 -> graphics.setColor(new Color(0, 128, 255, opacity));
                case 6 -> graphics.setColor(new Color(240, 130, 230, opacity));
                case 7 -> graphics.setColor(new Color(140, 50, 200, opacity));
                default -> {
                    Random random = new Random();
                    Color color = new Color(Math.abs(random.nextInt() % 255),
                            Math.abs(random.nextInt() % 255),
                            Math.abs(random.nextInt() % 255),
                            Math.abs(random.nextInt() % 255));
                    graphics.setColor(color);
                }
            }

        }
        graphics.fill(new Square(x, y, radius));
        DrawableShape shape = new DrawableShape(new RegularPolygon(x, y, radius, 4), graphics.getColor());
        frame.getShapes().add(shape);
    }

    @Override
    public void update(Graphics g) {
        System.out.println("Update");
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }

    public int getW() {
        return W;
    }

    public int getH() {
        return H;
    }

    public void setGraphics(Graphics2D graphics) {
        this.graphics = graphics;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public ShapeForm getShapeForm() {
        return shapeForm;
    }

    public void setShapeForm(ShapeForm shapeForm) {
        this.shapeForm = shapeForm;
    }
}
