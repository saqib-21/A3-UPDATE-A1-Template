package ca.mcmaster.se2aa4.mazerunner;

public abstract class MazeSolver {
    protected Maze maze;
    protected MazeRunner runner;
    protected StringBuilder path; // Tracks movement 
    private String lastMove = "";
    private int moveCount = 0;

    

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
        int y = runner.getPositionY();
        int x = runner.getPositionX();
        int[] exit = maze.findExit();

        return exit != null && y == exit[0] && x == exit[1];
    }


    // Factorized move recording
    protected void recordMove(String move) {
        if (move.equals(lastMove)) {
            moveCount++; // Continue counting consecutive moves
        } else {
            StoreMove(); // Store the previous move if a new one starts
            lastMove = move;
            moveCount = 1;
        }
    }

    // Append the stored move sequence to the path
    private void StoreMove() {
        if (moveCount > 0) {
            if (moveCount == 1) {
                path.append(lastMove).append("");
            } else {
                path.append(moveCount).append(lastMove).append("");
            }
        }
    }

    // Ensures the last move is properly recorded at the end
    protected void finalizePath() {
        StoreMove();
        System.out.println(path.toString().trim());
    }
}