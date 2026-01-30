package mycompany.com.abschlussprojekt;

// Import all necessary packages
import java.util.ArrayList;
import java.util.Random;


public class Agents {
    private int i;
    private int j;
    private int state;
    private int recoveryTime = 3; // Number of timesteps needed for recovery
    private int timeSinceInfection = 0; // Counter to track the time since infection
    
    // Constructor for Agents class
    public Agents(){
        Random Rand = new Random();
        i = Rand.nextInt(0,101);
        j = Rand.nextInt(0,101);
                
    }
    
    // Setters
    public void setState(int state){
    this.state=state;
    }
    
    public int getState(){
        return state;
    }
    
    // Getters
    public int getX(){
        return i;
    }
    
    public int getY(){
        return j;
    }
    
    // Moving methods
    public void moveNorth() {
        if (i > 0) {
            i--;
        }
    }

    public void moveEast() {
        if (j < 100) {
            j++;
        }
    }

    public void moveSouth() {
        if (i < 100) {
            i++;
        }
    }

    public void moveWest() {
        if (j > 0) {
            j--;
        }
    }

    // Infection Method
    public void infectNeighbors(ArrayList<Agents> agentsList, float transmissionRate) {
        for (Agents otherAgent : agentsList) {
            // Check if the other agent is a direct neighbor
            if (isNeighbor(otherAgent)) {
                // Check if the other agent is infected (state=2)
                if (otherAgent.getState() == 2) {
                    // Determine whether the current agent gets infected
                    double infectionChance = Math.random();
                    if (infectionChance <= transmissionRate) {
                        // Agent gets infected
                        setState(2);
                        break; // Stop checking other neighbors after infection
                    }
                }
            }
        }
    }

    // Method to check for Neighbors
    private boolean isNeighbor(Agents otherAgent) {
        return Math.abs(this.i - otherAgent.i) <= 1 && Math.abs(this.j - otherAgent.j) <= 1;
    }
   
    // Method to update infection status
    public void update() {
        if (state == 2) { // If agent is infected
            timeSinceInfection++;
            if (timeSinceInfection >= recoveryTime) {
                state = 3; // Agent recovers after three timesteps
            }
        }
    }
}
