package ca.mcmaster.se2aa4.mazerunner;

public class TurnRightCommand implements MovementCommand {
    private MazeRunner runner;
    private char prevDirection;

    public TurnRightCommand(MazeRunner runner) {
        this.runner = runner;
    }

    @Override
    public boolean execute() {
        // Save state for undo
        prevDirection = runner.getDirection();  // Save current direction
        // Turn the MazeRunner to the right
        if (prevDirection == 'N') {
            runner.setDirection( 'E');
        } else if (prevDirection == 'E') {
            runner.setDirection( 'S');
        } else if (prevDirection == 'S') {
            runner.setDirection( 'W');
        } else if (prevDirection  == 'W') {
            runner.setDirection(  'N');
        }

        return true; // Assuming turning right always succeeds
    }

    @Override
    public void undo() {
        runner.setDirection(prevDirection);
    }
    
}
