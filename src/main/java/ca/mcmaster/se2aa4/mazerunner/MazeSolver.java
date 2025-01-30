package ca.mcmaster.se2aa4.mazerunner;

public abstract class MazeSolver {
    protected Maze maze;
    protected MazeRunner runner;
    protected StringBuilder path; // Tracks movement 

    

    // Constructor
    public MazeSolver(Maze maze, MazeRunner runner) {
        this.maze = maze;
        this.runner = runner;
        this.path = new StringBuilder();
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

    // Adds moves to path in canonical form
    protected void recordMove(String move) {
        path.append(move); // No factorization
    }
}
