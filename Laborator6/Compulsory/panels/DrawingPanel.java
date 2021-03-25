package laborator6.compulsory.panels;

import laborator6.compulsory.MainFrame;
import laborator6.compulsory.shapes.RegularPolygon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 2560, H = 1600;
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
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawShape(e.getX(), e.getY());
                repaint();
            }
        });
    }

    private void drawShape(int x, int y) {
        int radius = Math.abs((int) (Math.random() * 1000)) % W/2;
        int sides = frame.getConfigPanel().getSides();

        if(frame.getConfigPanel().getColorCombo().getSelectedIndex() == 0)
            graphics.setColor(Color.BLACK);
        else {
            Random random = new Random();
            Color color = new Color(Math.abs(random.nextInt() % 255),
                    Math.abs(random.nextInt() % 255),
                    Math.abs(random.nextInt() % 255),
                    100);
            graphics.setColor(color);
        }

        graphics.fill(new RegularPolygon(x, y, radius, sides));
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

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }
}
