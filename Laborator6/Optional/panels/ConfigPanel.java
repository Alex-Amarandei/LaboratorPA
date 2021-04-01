package optional.panels;

import optional.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel sidesLabel;
    JLabel colorLabel;
    JLabel opacityLabel;
    JLabel sizeLabel;
    JSpinner sidesField;
    JSpinner opacityField;
    JSpinner sizeField;
    JButton randomnessButton;
    JComboBox<String> colorCombo;
    private boolean randomnessSelected = false;
    private boolean deletionSelected = false;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        sidesLabel = new JLabel("Number of sides: ");
        sidesField = new JSpinner(new SpinnerNumberModel(3, 3, 100, 1));
        sidesField.setValue(6);

        colorLabel = new JLabel("Color: ");
        colorCombo = new JComboBox<>();
        colorCombo.addItem("Black");
        colorCombo.addItem("Red");
        colorCombo.addItem("Orange");
        colorCombo.addItem("Yellow");
        colorCombo.addItem("Green");
        colorCombo.addItem("Blue");
        colorCombo.addItem("Pink");
        colorCombo.addItem("Purple");
        colorCombo.addItem("Random");
        colorCombo.setSelectedIndex(0);

        opacityLabel = new JLabel("Opacity: ");
        opacityField = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
        opacityField.setValue(255);

        sizeLabel = new JLabel("Size (radius): ");
        sizeField = new JSpinner(new SpinnerNumberModel(1, 1, 1200, 1));
        sizeField.setValue(100);

        randomnessButton = new JButton("Complete Randomness");
        randomnessButton.addActionListener(this::randomSelected);

        add(sidesLabel);
        add(sidesField);

        add(colorLabel);
        add(colorCombo);

        add(opacityLabel);
        add(opacityField);

        add(sizeLabel);
        add(sizeField);

        add(randomnessButton);
    }

    private void randomSelected(ActionEvent actionEvent) {
        this.randomnessSelected = !this.randomnessSelected;
    }

    public int getSides() {
        return (int) sidesField.getValue();
    }

    public JComboBox<String> getColorCombo() {
        return colorCombo;
    }

    public int getOpacity() {
        return (int) opacityField.getValue();
    }

    public int getShapeSize() {
        return (int) sizeField.getValue();
    }

    public boolean isRandomnessSelected() {
        return randomnessSelected;
    }

    public void setRandomnessSelected(boolean randomnessSelected) {
        this.randomnessSelected = randomnessSelected;
    }

    public boolean isDeletionSelected() {
        return deletionSelected;
    }

    public void setDeletionSelected(boolean deletionSelected) {
        this.deletionSelected = deletionSelected;
    }

    public JSpinner getSidesField() {
        return sidesField;
    }

    public void setSidesField(JSpinner sidesField) {
        this.sidesField = sidesField;
    }


}
