package ca.mcmaster.se2aa4.mazerunner;

public class MazeSolverFactory {

    public static MazeSolver createSolver(String strategy, Maze maze, MazeRunner runner) {
        if ("RightHand".equals(strategy)) {
            return new RightHandMazeSolver(maze, runner);
        } else if ("Straight".equals(strategy)) { // Placeholder for another type of solver
            return new StraightMazeSolver(maze, runner);
            // You can add more strategies here as needed
        } else {
            return new RightHandMazeSolver(maze, runner); // defaults to RightHandMazeSolver
        }
    }
}