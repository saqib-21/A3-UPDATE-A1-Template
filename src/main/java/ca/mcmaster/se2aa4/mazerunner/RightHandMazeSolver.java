package ca.mcmaster.se2aa4.mazerunner;

public class RightHandMazeSolver extends MazeSolver {

    public RightHandMazeSolver(Maze maze, MazeRunner runner) {
        super(maze, runner);
    }

    @Override
    public String solveMaze() {
        while (!isAtExit()) {
            // 1. If there’s an open path to the right, turn right and move
            runner.turnRight();
            if (runner.moveForward(maze.getGrid())) {
                recordMove("R");
                recordMove("F");// Turn right and move forward
                continue;
            }

            // 2. If the right is blocked but forward is open, move forward
            runner.turnLeft(); // Reset to original direction
            if (runner.moveForward(maze.getGrid())) {
                recordMove("F");// Move forward
                continue;
            }

            // 3. If both are blocked, turn left until movement is possible
            runner.turnLeft();
            recordMove("L"); // Turn left
        }


        return finalizePath(); 
    }
}
