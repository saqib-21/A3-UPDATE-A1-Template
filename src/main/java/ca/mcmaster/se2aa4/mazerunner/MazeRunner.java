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
    public boolean moveForward(char[][] grid) {
        int newX = positionX;
        int newY = positionY;

        // Determine the next position based on the current direction
        if (direction == 'N') { // North
            newX = positionX - 1;
        } else if (direction == 'E') { // East
            newY = positionY + 1;
        } else if (direction == 'S') { // South
            newX = positionX + 1;
        } else if (direction == 'W') { // West
            newY = positionY - 1;
        } else {
            System.out.println("Invalid direction!");
            return false;
        }

        // Check if the next position is within bounds and open
        if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] == ' ') {
            // Update position
            positionX = newX;
            positionY = newY;
            return true;
        } else {
            System.out.println("Cannot move forward! Path is blocked.");
            return false;
        }
    }
    // Turn the MazeRunner to the left
    public void turnLeft(){
        if (direction == 'N') {
            direction = 'W';
        } else if (direction == 'E') {
            direction = 'N';
        } else if (direction == 'S') {
            direction = 'E';
        } else if (direction == 'W') {
            direction = 'S';
        }
    }

    // Turn the MazeRunner to the right
    public void turnRight() {
        if (direction == 'N') {
            direction = 'E';
        } else if (direction == 'E') {
            direction = 'S';
        } else if (direction == 'S') {
            direction = 'W';
        } else if (direction == 'W') {
            direction = 'N';
        }

    }

    // For debugging: Get the MazeRunner's current state
    public String getState() {
        return "Position: (" + positionX + ", " + positionY + "), Direction: " + direction;
    }
}