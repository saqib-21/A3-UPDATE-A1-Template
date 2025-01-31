package ca.mcmaster.se2aa4.mazerunner;

public class PathValidator extends MazeRunner {

    private Maze maze;

    public PathValidator(Maze maze) {
        // Call MazeRunner constructor with initial position & facing direction
        super(maze.findEntrance()[0], maze.findEntrance()[1], 'E'); // Assume starts facing East
        this.maze = maze;
    }

    public boolean isValidPath(String path) {
        char[][] grid = maze.getGrid();

        for (char move : path.toCharArray()) {
            if (move == 'F') {
                moveForward(grid);
            } else if (move == 'L') {
                turnLeft();
            } else if (move == 'R') {
                turnRight();
            } else {
                return false; // Invalid character in path
            }
        }

        return isAtExit();
    }

    private boolean isAtExit() {
        int[] exit = maze.findExit();
        return getPositionX() == exit[0] && getPositionY() == exit[1];
    }
}
