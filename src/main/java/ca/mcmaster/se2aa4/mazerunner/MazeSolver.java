package ca.mcmaster.se2aa4.mazerunner;

public abstract class MazeSolver {
    protected Maze maze;
    protected MazeRunner runner;

    // Constructor
    public MazeSolver(Maze maze, MazeRunner runner) {
        this.maze = maze;
        this.runner = runner;
    }

    // Abstract method to solve the maze
    public abstract void solveMaze();

    // Check if the MazeRunner is at the exit
    protected boolean isAtExit() {
        int x = runner.getPositionX();
        int y = runner.getPositionY();
        int[] exit = maze.findExit();

        return exit != null && x == exit[0] && y == exit[1];
    }
}
