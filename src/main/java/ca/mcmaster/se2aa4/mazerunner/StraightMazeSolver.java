package ca.mcmaster.se2aa4.mazerunner;

public class StraightMazeSolver extends MazeSolver {

    public StraightMazeSolver(Maze maze, MazeRunner runner) {
        super(maze, runner);
    }

    @Override
    public void solveMaze() {
        char[][] grid = maze.getGrid();
        StringBuilder path = new StringBuilder();

        while (!isAtExit()) {
            // Move forward if possible
            if (runner.moveForward(grid)) {
                path.append("F"); // Record the forward movement
            } else {
                // If no forward movement is possible, stop
                System.out.println("MazeRunner is blocked. Path so far: " + path.toString());
                break;
            }
        }
        System.out.println("Maze solved! Path: " + path.toString());
    }
}