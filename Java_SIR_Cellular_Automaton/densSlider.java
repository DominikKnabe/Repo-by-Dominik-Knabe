package mycompany.com.abschlussprojekt;

// Import all necessary packages
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class densSlider extends JPanel {

    private JSlider slider;
    private JLabel valueLabel;

    public densSlider() {
        // Use BoxLayout with Y_AXIS orientation
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Pop. Density [%]", JLabel.CENTER);
        add(titleLabel);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));

        slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 40); // Corrected line

        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // Update the label when the slider value changes
                valueLabel.setText("Value: " + slider.getValue());
            }
        });
        
        
        
        valueLabel = new JLabel("Value: " + slider.getValue());
        valueLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add components to this JPanel
        add(slider);
        add(valueLabel);
    }

    public int getValue() {
        return slider.getValue();
    }

    public JSlider getSlider() {
    return slider;
    }
}
