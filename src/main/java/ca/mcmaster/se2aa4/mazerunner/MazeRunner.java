package ca.mcmaster.se2aa4.mazerunner;

public class MazeRunner {
    // Attributes
    private int positionX; // X-coordinate of the MazeRunner
    private int positionY; // Y-coordinate of the MazeRunner
    private char direction; // Direction the MazeRunner is facing ('N', 'E', 'S', 'W')

    // Constructor
    public MazeRunner(int positionX, int positionY, char direction) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.direction = direction;
    }

    // Getters
    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public char getDirection() {
        return direction;
    }

    // Setters
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    // Methods

    // Move the MazeRunner forward based on its current direction
    public void moveForward() {
        // Code to move the MazeRunner forward will go here
    }

    // Turn the MazeRunner to the left
    public void turnLeft(){
        // Code to turn the MazeRunner left will go here
    }

    // Turn the MazeRunner to the right
    public void turnRight() {
        // Code to turn the MazeRunner right will go here

    }

    // For debugging: Get the MazeRunner's current state
    public String getState() {
        return "Position: (" + positionX + ", " + positionY + "), Direction: " + direction;
    }
}