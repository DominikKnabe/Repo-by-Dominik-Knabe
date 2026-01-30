package mycompany.com.abschlussprojekt;

// Import all necessary packages
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;


public class Abschlussprojekt {

    public static void main(String[] args) {
        // Create JLabels for infection states (Legend)
        JLabel infected=new JLabel("Infected");
        JLabel recovered=new JLabel("Recovered");
        JLabel susceptible=new JLabel("Susceptible");
        
        // Create an empty JLabel as a placeholder
        JLabel empty=new JLabel("\n");
        
        // Color labels according to grid colors
        infected.setForeground(Color.red);
        recovered.setForeground(Color.orange);
        susceptible.setForeground(Color.green);
        
        // Define parameters for simulation
        int steps = 100; // number of simulation ticks
        int delay = 100; // milliseconds between simulation steps

        // Create Frame and Panels
        JFrame frame = new JFrame();
        JPanel rootpanel = new JPanel();
        JPanel controls = new JPanel();
        controls.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));

        // Start Button
        JButton sButton = new JButton("START");

        // Slider Density
        densSlider densSlider1 = new densSlider();

        // Slider Transmission
        transSlider transSlider1 = new transSlider();

        // Slider Stay at home
        sahSlider sahSlider1 = new sahSlider();

        // Pass sliders to the Gui constructor
        Gui gui = new Gui(densSlider1, transSlider1, sahSlider1);

        // Exit on close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a Timer to update the simulation
        Timer timer = new Timer(delay, new ActionListener() {
            private int stepCount = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (stepCount < steps) {
                    // Call the methods to move agents and infect neighbors
                    gui.moveAgents();
                    gui.infectNeighbors();
                    gui.updateAgents();
                    // Repaint the GUI after each step
                    gui.repaint();
                    stepCount++;
                } else {
                    // Stop the timer after completing the specified number of steps
                    ((Timer) e.getSource()).stop();
                }
            }
        });

        // Add ActionListener to the Start button
        sButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Start the Timer when the button is pressed
                timer.start();
            }
        });

        // Add the text field and the button to the JPanel (our GUI).
        frame.add(rootpanel);
        rootpanel.add(controls);
        rootpanel.add(gui);
        rootpanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        
        // Add legend text
        controls.add(infected);
        controls.add(recovered);
        controls.add(susceptible);
        controls.add(empty);
        
        // Add buttons and sliders
        controls.add(sButton);
        controls.add(densSlider1);
        controls.add(transSlider1);
        controls.add(sahSlider1);
        
        // Layouting
        rootpanel.setLayout(new BoxLayout(rootpanel, BoxLayout.X_AXIS));
        controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size, arrange components, and display the frame.
        frame.pack();
        frame.setVisible(true);
    }
}