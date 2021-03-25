package laborator6.compulsory.panels;

import laborator6.compulsory.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel sidesLabel;
    JSpinner sidesField;
    JComboBox<String> colorCombo;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        sidesLabel = new JLabel("Number of sides:");
        sidesField = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        sidesField.setValue(6);

        colorCombo = new JComboBox<>();
        colorCombo.addItem("Black");
        colorCombo.addItem("Random");
        colorCombo.setSelectedIndex(0);

        add(sidesLabel);
        add(sidesField);
        add(colorCombo);
    }

    public int getSides() {
        return (int) sidesField.getValue();
    }

    public JComboBox<String> getColorCombo() {
        return colorCombo;
    }
}
