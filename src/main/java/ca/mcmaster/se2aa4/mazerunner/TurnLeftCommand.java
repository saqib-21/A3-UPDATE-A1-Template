package ca.mcmaster.se2aa4.mazerunner;

public class TurnLeftCommand implements MovementCommand {
    private MazeRunner runner;
    private char prevDirection;

    public TurnLeftCommand(MazeRunner runner) {
        this.runner = runner;
    }


    @Override
    public boolean execute() {
        // Save state for undo
        prevDirection = runner.getDirection();  // Save current runner.setDirection(
        if (prevDirection == 'N') {
            runner.setDirection( 'W');
        } else if (prevDirection == 'E') {
            runner.setDirection( 'N');
        } else if (prevDirection == 'S') {
            runner.setDirection( 'E');
        } else if (prevDirection == 'W') {
            runner.setDirection( 'S');
        }
    
        return true; // Assuming turning left always succeeds
    }

    @Override
    public void undo() {
        runner.setDirection(prevDirection);
    }
    
}
