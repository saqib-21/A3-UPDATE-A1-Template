package ca.mcmaster.se2aa4.mazerunner;

public class RightHandMazeSolver extends MazeSolver {

    public RightHandMazeSolver(Maze maze, MazeRunner runner) {
        super(maze, runner);
    }

    @Override
    public String solveMaze() {


        MovementCommand rightTurn = new TurnRightCommand(runner);
        MovementCommand leftTurn = new TurnLeftCommand(runner);
        MovementCommand moveForward = new ForwardCommand(runner, maze);

        while (!isAtExit()) {
            // 1. If there’s an open path to the right, turn right and move
            runner.executeCommand(rightTurn);
            if (runner.executeCommand(moveForward)) {
                recordMove("R");
                recordMove("F");// Turn right and move forward
                continue;
            }

            // 2. If the right is blocked but forward is open, move forward
            runner.undoCommand(rightTurn); // Undo the right turn
            if (runner.moveForward(maze.getGrid())) {
                recordMove("F");// Move forward
                continue;
            }

            // 3. If both are blocked, turn left until movement is possible
            runner.executeCommand(leftTurn);
            recordMove("L"); // Turn left
        }


        return finalizePath(); 
    }
}
