package ca.mcmaster.se2aa4.mazerunner;

public class MazeRunner {
    // Attributes
    private int PositionY; // X-coordinate of the MazeRunner
    private int PositionX; // Y-coordinate of the MazeRunner
    private char direction; // Direction the MazeRunner is facing ('N', 'E', 'S', 'W')

    // Constructor
    public MazeRunner(int PositionY, int PositionX, char direction) {
        this.PositionY = PositionY;
        this.PositionX = PositionX;
        this.direction = direction;
    }

    // Getters
    public int getPositionY() {
        return PositionY;
    }

    public int getPositionX() {
        return PositionX;
    }

    public char getDirection() {
        return direction;
    }

    // Setters
    public void setPositionY(int PositionY) {
        this.PositionY = PositionY;
    }

    public void setPositionX(int PositionX) {
        this.PositionX = PositionX;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }


    // Move the MazeRunner forward based on its current direction
    public boolean moveForward(char[][] grid) {
        int newY = PositionY;
        int newX = PositionX;

        // Determine the next position based on the current direction
        if (direction == 'N') { // North
            newY = PositionY - 1;
        } else if (direction == 'E') { // East
            newX = PositionX + 1;
        } else if (direction == 'S') { // South
            newY = PositionY + 1;
        } else if (direction == 'W') { // West
            newX = PositionX - 1;
        } else {
            return false;
        }

        // Check if the next position is within bounds and open
        if (newY >= 0 && newY < grid.length && newX >= 0 && newX < grid[0].length && grid[newY][newX] == ' ') {
            // Update position
            PositionY = newY;
            PositionX = newX;
            return true;
        } else {
            //System.out.println("Cannot move forward! Path is blocked.");
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
        return "Position: (" + PositionY + ", " + PositionX + "), Direction: " + direction;
    }
}