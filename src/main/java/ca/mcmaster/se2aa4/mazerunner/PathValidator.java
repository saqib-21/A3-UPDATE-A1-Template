package ca.mcmaster.se2aa4.mazerunner;

public class PathValidator extends MazeRunner {

    private final Maze maze;

    public PathValidator(Maze maze) {
        super(maze.findEntrance()[0], maze.findEntrance()[1], 'E'); // Default: start at entrance, facing East
        this.maze = maze;
    }

    public boolean isValidPath(String path) {
        char[][] grid = maze.getGrid();

        // Try validating from the default entrance
        if (validatePath(path, getPositionY(), getPositionX(), getDirection(), grid)) {
            return true;
        }

        // If invalid, reverse path and try from the exit
        int[] exit = maze.findExit();
        if (exit != null) {
            resetPosition(exit[0], exit[1], 'W'); // Start at exit, facing West
            String reversedPath = reversePath(path);
            return validatePath(reversedPath, getPositionY(), getPositionX(), getDirection(), grid);
        }

        return false; // Path is invalid from both entry and exit
    }

    private boolean validatePath(String path, int startX, int startY, char startDirection, char[][] grid) {
        int i = 0;
        setPositionY(startX);
        setPositionX(startY);
        setDirection(startDirection);

        while (i < path.length()) {
            int repeat = 1; // Default if no number is provided

            // Extract number before move (if present)
            if (Character.isDigit(path.charAt(i))) {
                repeat = 0;
                while (i < path.length() && Character.isDigit(path.charAt(i))) {
                    repeat = repeat * 10 + (path.charAt(i) - '0');
                    i++;
                }
            }

            if (i < path.length()) {
                char move = path.charAt(i);

                if (move == 'F') {
                    for (int j = 0; j < repeat; j++) {
                        moveForward(grid);
                    }
                } else if (move == 'L') {
                    for (int j = 0; j < repeat; j++) turnLeft();
                } else if (move == 'R') {
                    for (int j = 0; j < repeat; j++) turnRight();
                } else {
                    return false; // Invalid move character
                }

                i++;
            }
        }

        return isAtExit();
    }
    private String reversePath(String path) {
        StringBuilder reversed = new StringBuilder();
        int i = path.length() - 1;
    
        while (i >= 0) {
            char move = path.charAt(i);
    
            // Extract number if present
            StringBuilder num = new StringBuilder();
            while (i >= 0 && Character.isDigit(path.charAt(i))) {
                num.insert(0, path.charAt(i));
                i--;
            }
    
            if (move == 'F') {
                reversed.insert(0, num + "F"); // Keep F unchanged
            } else if (move == 'L') {
                reversed.insert(0, "R"); // Swap L -> R
            } else if (move == 'R') {
                reversed.insert(0, "L"); // Swap R -> L
            }
    
            i--;
        }
    
        return reversed.toString();
    }
    

    private void resetPosition(int x, int y, char direction) {
        setPositionY(x);
        setPositionX(y);
        setDirection(direction);
    }

    private boolean isAtExit() {
        int[] exit = maze.findExit();
        return getPositionY() == exit[0] && getPositionX() == exit[1];
    }
}
