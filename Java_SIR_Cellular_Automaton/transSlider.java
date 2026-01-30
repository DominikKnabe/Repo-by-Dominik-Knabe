package mycompany.com.abschlussprojekt;

// Import all necessary packages
import java.text.DecimalFormat;
import java.util.Hashtable;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;


public class transSlider extends JPanel {

    private JSlider slider;
    private JLabel valueLabel;

    public transSlider() {

        // Use BoxLayout with Y_AXIS orientation
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Transmission", JLabel.CENTER);
        add(titleLabel);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));


        // Scale factor to map 0-1 range to 0-100 for the slider
        int scaleFactor = 100;

        // Create a JSlider with a range from 0 to 100
        slider = new JSlider(JSlider.HORIZONTAL, 0, scaleFactor, scaleFactor / 2);
        slider.setMajorTickSpacing(scaleFactor / 10);
        slider.setMinorTickSpacing(scaleFactor / 20);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        // Create a JLabel to display the current float value of the slider
        valueLabel = new JLabel("Value: " + formatFloat(getScaledValue()));
        valueLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Customize label formatting
        NumberFormatter formatter = new NumberFormatter(new DecimalFormat("0.00"));
        formatter.setMinimum(0.0);
        formatter.setMaximum(1.0);
        JFormattedTextField ftf = new JFormattedTextField(formatter);
        ftf.setValue(slider.getValue() / (double) scaleFactor);
        ftf.setColumns(5);
        ftf.setEditable(false);

        // Add a ChangeListener to the slider
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // Update the label when the slider value changes
                valueLabel.setText("Value: " + formatFloat(getScaledValue()));
            }
        });

        // Add components to this JPanel
        add(slider);
        add(valueLabel);

        // Set custom labels for ticks
        setCustomTickLabels();
    }

    private void setCustomTickLabels() {
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        for (int i = 0; i <= 5; i++) {
            double value = i / 5.0;
            labelTable.put((int) (value * slider.getMaximum()), new JLabel(formatFloat((float) value)));
        }
        slider.setLabelTable(labelTable);
    }

    public float getScaledValue() {
        // Map the slider value back to the 0-1 range
        return (float) slider.getValue() / slider.getMaximum();
    }

    private String formatFloat(float value) {
        // Format the float value if needed
        DecimalFormat decimalFormat = new DecimalFormat("#0.0");
        return decimalFormat.format(value);
    }
}
