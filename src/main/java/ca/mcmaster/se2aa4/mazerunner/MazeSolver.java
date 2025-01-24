package ca.mcmaster.se2aa4.mazerunner;

public class MazeSolver {
    private Maze maze;
    private MazeRunner runner;

    // Constructor
    public MazeSolver(Maze maze, MazeRunner runner) {
        this.maze = maze;
        this.runner = runner;
    }

    // Solve the maze and print the path
    public void solveMaze() {
        char[][] grid = maze.getGrid();
        StringBuilder path = new StringBuilder();

        while (true) {
            // Check if the MazeRunner is at the exit
            if (isAtExit()) {
                System.out.println("Maze solved! Path: " + path.toString());
                break;
            }

            // Move forward if possible
            if (runner.moveForward(grid)) {
                path.append("F"); // Record the forward movement
            } else {
                // If no forward movement is possible, stop
                System.out.println("MazeRunner is blocked. Path so far: " + path.toString());
                break;
            }
        }
    }


    // Check if the MazeRunner is at the exit
    private boolean isAtExit() {
        int x = runner.getPositionX();
        int y = runner.getPositionY();
        int[] exit = maze.findExit();

        return exit != null && x == exit[0] && y == exit[1];
    }
}
