package laborator6.compulsory.panels;

import laborator6.compulsory.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton saveButton;
    JButton loadButton;
    JButton resetButton;
    JButton exitButton;

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        this.saveButton = new JButton("Save");
        this.loadButton = new JButton("Load");
        this.resetButton = new JButton("Reset");
        this.exitButton = new JButton("Exit");
        init();
    }

    private void init() {
        setLayout(new GridLayout(1, 4));

        add(saveButton);
        add(loadButton);
        add(resetButton);
        add(exitButton);

        saveButton.addActionListener(this::save);
        loadButton.addActionListener(this::load);
        resetButton.addActionListener(this::reset);
        exitButton.addActionListener(this::exit);
    }

    private void save(ActionEvent e) {
        try {
            ImageIO.write(frame.getCanvas().getImage(), "PNG",
                    new FileOutputStream("/Users/alex-ama/Desktop/test.png"));
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private void load(ActionEvent e) {
        JFileChooser jFileChooser = new JFileChooser("/Users/alex-ama/Desktop");
        jFileChooser.setFileFilter(
                new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        String name = f.getName().toLowerCase();
                        return name.endsWith(".png") || name.endsWith(".jpg");
                    }

                    @Override
                    public String getDescription() {
                        return "Images (*.png, *.jpg)";
                    }
                }
        );

        int status = jFileChooser.showOpenDialog(null);
        BufferedImage bufferedImage = null;

        if(status == JFileChooser.APPROVE_OPTION && jFileChooser.getSelectedFile().exists()) {
            try {
                bufferedImage = ImageIO.read(new File(jFileChooser.getSelectedFile().getAbsolutePath()));
            }
            catch(IOException exception) {
                System.err.println("Error loading image.");
                exception.printStackTrace();
            }
            finally {
                frame.getCanvas().setImage(bufferedImage);
                if(bufferedImage != null)
                    frame.getCanvas().setGraphics(bufferedImage.createGraphics());
                frame.getCanvas().repaint();
            }
        } else {
            System.out.println("Operation cancelled.");
        }
    }

    private void reset(ActionEvent e) {
        DrawingPanel canvas = frame.getCanvas();
        canvas.graphics.setColor(Color.WHITE);
        canvas.graphics.fill(new Rectangle(canvas.getW(), canvas.getH()));
        canvas.repaint();
    }

    private void exit(ActionEvent e) {
        System.exit(0);
    }
}
