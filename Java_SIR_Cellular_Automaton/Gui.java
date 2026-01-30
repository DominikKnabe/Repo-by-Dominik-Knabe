package mycompany.com.abschlussprojekt;

// Import all necessary packages
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Gui extends JPanel {

    private int cellCount;
    private int suscells;
    private int startpos;
    private densSlider densSlider1;
    private transSlider transSlider1;
    private sahSlider sahSlider1;
    private int dens;
    private float trans;
    private float sah;
    private ArrayList<Agents> agentslist;
   
    // Construct GUI
    public Gui(densSlider densSlider1, transSlider transSlider1, sahSlider sahSlider1) {

        // Set the (preferred) size of the panel
        setPreferredSize(new java.awt.Dimension(620, 600));

        this.densSlider1 = densSlider1;
        this.transSlider1 = transSlider1;
        this.sahSlider1 = sahSlider1;

        agentslist = new ArrayList<>();

        dens = densSlider1.getValue();
        trans = transSlider1.getScaledValue();
        sah = sahSlider1.getScaledValue();
        cellCount = 100;
        suscells = cellCount * cellCount * dens / 100;
        startpos = 15;

        for (int i = 0; i < suscells; i++) {
            Agents agent = new Agents();
            agent.setState(1);
            agentslist.add(agent);
        }

        for (int i = 0; i < startpos; i++) {
            Agents agent = new Agents();
            agent.setState(2);
            agentslist.add(agent);
        }

        densSlider1.getSlider().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                agentslist.clear();
                // Update the density value and repaint the panel
                dens = densSlider1.getValue();
                suscells = cellCount * cellCount * dens / 100;

                for (int i = 0; i < suscells; i++) {
                    Agents agent = new Agents();
                    agent.setState(1);
                    agentslist.add(agent);
                }
                for (int i = 0; i < startpos; i++) {
                    Agents agent = new Agents();
                    agent.setState(2);
                    agentslist.add(agent);
                }

                repaint();
            }
        });

    }

    public void moveAgents() {
        Random rand = new Random();

        for (Agents agent : agentslist) {
            int move = rand.nextInt(4) + 1; // Random number between 1 and 4

            if (rand.nextDouble() > sahSlider1.getScaledValue()) {
                // Move only if the random value is greater than the stay-at-home slider value
                switch (move) {
                    case 1:
                        agent.moveNorth(); // Implement moveNorth method in Agents class
                        break;
                    case 2:
                        agent.moveEast(); // Implement moveEast method in Agents class
                        break;
                    case 3:
                        agent.moveSouth(); // Implement moveSouth method in Agents class
                        break;
                    case 4:
                        agent.moveWest(); // Implement moveWest method in Agents class
                        break;
                }
            }
        }
    }

    public void infectNeighbors() {
        for (Agents agent : agentslist) {
            agent.infectNeighbors(agentslist, transSlider1.getScaledValue());
        }
    }

    public void updateAgents() {
        for (Agents agent : agentslist) {
            agent.update();
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cellSize = getWidth() / cellCount;

        for (int i = 0; i < cellCount; i++) {
            for (int j = 0; j < cellCount; j++) {
                final int currentI = i;
                final int currentJ = j;

               boolean isAgentAtPosition = agentslist.stream().anyMatch(agent ->
                        agent.getX() == currentI && agent.getY() == currentJ);

                if (isAgentAtPosition) {
                    // Color for cells with an agent
                    if (agentslist.stream().anyMatch(agent ->
                            agent.getX() == currentI && agent.getY() == currentJ && agent.getState() == 2)) {
                        g.setColor(Color.RED); // Color for cells with agent and state=2
                    } else if (agentslist.stream().anyMatch(agent ->
                            agent.getX() == currentI && agent.getY() == currentJ && agent.getState() == 3)) {
                        g.setColor(Color.ORANGE); // Color for cells with agent and state=3 (recovered)
                    } else {
                        g.setColor(Color.GREEN); // Color for cells with an agent and state=1
                    }
                } else {
                    g.setColor(Color.WHITE); // Color for empty cells
                }

                g.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
            }
        }
    }

    public ArrayList<Agents> getAgentsList() {
        return agentslist;
    }
}

