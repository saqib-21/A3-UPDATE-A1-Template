package ca.mcmaster.se2aa4.mazerunner;

public class PathValidator extends MazeRunner {

    private final Maze maze;

    public PathValidator(Maze maze) {
        // Call MazeRunner constructor with initial position & facing direction
        super(maze.findEntrance()[0], maze.findEntrance()[1], 'E'); // Assume starts facing East
        this.maze = maze;
    }

    
    public boolean isValidPath(String path) {
        path = path.replaceAll("\\s+", ""); // Remove all spaces
        int i = 0;
    
        while (i < path.length()) {
            int repeat = 1; // Default to 1 if no number is provided
    
            // Check if there's a number before the move character
            if (Character.isDigit(path.charAt(i))) {
                repeat = 0;
                while (i < path.length() && Character.isDigit(path.charAt(i))) {
                    repeat = repeat * 10 + (path.charAt(i) - '0');
                    i++;
                }
            }
    
            if (i < path.length()) {
                char move = path.charAt(i);
                MovementCommand command = null;

                if (move == 'F') {
                    command = new ForwardCommand(this, maze);
                } else if (move == 'L') {
                    command = new TurnLeftCommand(this);
                } else if (move == 'R') {
                    command = new TurnRightCommand(this);
                } else {
                    return false;
                }

                for (int j = 0; j < repeat; j++) {
                    if (!executeCommand(command)) {
                        return false;
                    }
                }
                i++; // Move to next character
            }
        }
    
        return isAtExit();
    }
    
    
    private boolean isAtExit() {
        int[] exit = maze.findExit();
        return getPositionY() == exit[0] && getPositionX() == exit[1];
    }
}