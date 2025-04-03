package ca.mcmaster.se2aa4.mazerunner;

public class StraightMazeSolver extends MazeSolver {

    public StraightMazeSolver(Maze maze, MazeRunner runner) {
        super(maze, runner);
    }

    @Override
    public String solveMaze() {
        while (!isAtExit()) {
            // Move forward if possible
            if (runner.moveForward(maze.getGrid())) {
                recordMove("F"); // Record the forward movement
            } else {
                // If no forward movement is possible, stop
                //ONLY FOR MVP
                //System.out.println("MazeRunner is blocked. Path so far: " + path.toString());
                break;
            }
        }
        return finalizePath(); // Ensure last moves are processed

    }
}