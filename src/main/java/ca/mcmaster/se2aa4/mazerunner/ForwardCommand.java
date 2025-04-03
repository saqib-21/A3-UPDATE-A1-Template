package ca.mcmaster.se2aa4.mazerunner;

public class ForwardCommand implements MovementCommand {
    private MazeRunner runner;
    private Maze maze;
    private int previousX;
    private int previousY;

    public ForwardCommand(MazeRunner runner,  Maze maze) {
        this.runner = runner;
        this.maze = maze;        
    }


    @Override
    public boolean execute() {
        // Save state for undo
        previousY = runner.getPositionY();
        previousX = runner.getPositionX();
        
        return runner.moveForward(maze.getGrid());
    }

    @Override
    public void undo() {
        runner.setPositionY(previousY);
        runner.setPositionX(previousX);
    }
}

