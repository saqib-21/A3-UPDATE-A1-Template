package ca.mcmaster.se2aa4.mazerunner;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MazeSolutionTest {

    private String[] mazeFiles;

    @BeforeEach
    public void setUp() {
        // Load all maze files from the examples directory
        File examplesDir = new File("examples");
        mazeFiles = examplesDir.list((dir, name) -> name.endsWith(".maz.txt"));
    }

    @Test
    public void testAllMazesWithRightHandSolver() {
        for (String mazeFile : mazeFiles) {
            Maze maze = new Maze("examples" + File.separator + mazeFile);

            int[] entrance = maze.findEntrance();
            int[] exit = maze.findExit();

            // Initialize the MazeRunner at the entrance
            MazeRunner runner = new MazeRunner(entrance[0], entrance[1], 'E');
            // Create the RightHandMazeSolver
            RightHandMazeSolver solver = new RightHandMazeSolver(maze, runner);

            //solve the maze
            solver.solveMaze();

            // Check if the runner has reached the exit
            assertEquals(exit[0], runner.getPositionY(), "Failed for maze: " + mazeFile);
            assertEquals(exit[1], runner.getPositionX(), "Failed for maze: " + mazeFile);
        }
    }

    @Test
    public void testTinyPathValidation() {
        String filePath = "examples" + File.separator + "tiny.maz.txt";
        Maze maze = new Maze(filePath);

        // Test a path with invalid characters
        PathValidator validator1 = new PathValidator(maze);
        String invalidCharPath = "FFXFF";
        assertFalse(validator1.isValidPath(invalidCharPath), "The path should be invalid due to invalid characters.");

        // Test a path that's too long
        PathValidator validator2 = new PathValidator(maze);
        String invalidPath = "FFFFFFFFFFFFF";
        assertFalse(validator2.isValidPath(invalidPath), "The path should be invalid because it goes out of bounds.");

        // Test an empty path
        PathValidator validator3 = new PathValidator(maze);
        String emptyPath = "";
        assertFalse(validator3.isValidPath(emptyPath), "The path should be invalid as it doesn't lead to the exit.");

        // Test valid canonical path
        PathValidator validator4 = new PathValidator(maze);
        String canonicalValidPath = "FFFFFLLFFRFFRFF LLFFRFFRFFF";
        assertTrue(validator4.isValidPath(canonicalValidPath), "The path should be valid.");

        // Test a valid compressed path
        PathValidator validator5 = new PathValidator(maze);
        String validPath = "5F2L2FR2FR2F2L2FR2FR3F";
        assertTrue(validator5.isValidPath(validPath), "The path should be valid.");

        PathValidator validator6 = new PathValidator(maze);
        String validPath2 = "5F 2L 2F R 2F R 2F 2L 2F R 2F R 3F";
        assertTrue(validator6.isValidPath(validPath2), "The path should be valid.");
    }


    @Test
    public void testRHSolverOutputWithPathValidator() {
    for (String mazeFile : mazeFiles) {
        Maze maze = new Maze("examples" + File.separator + mazeFile);

        int[] entrance = maze.findEntrance();
        int[] exit = maze.findExit();

        // Initialize the MazeRunner at the entrance
        MazeRunner runner = new MazeRunner(entrance[0], entrance[1], 'E');
        // Create the RightHandMazeSolver
        RightHandMazeSolver solver = new RightHandMazeSolver(maze, runner);

        // Solve the maze and get the path
        String solverPath = solver.solveMaze();

        // Validate the solver's path using PathValidator
        PathValidator validator = new PathValidator(maze);
        assertTrue(validator.isValidPath(solverPath), "The solver's path should be valid for maze: " + mazeFile);

        // Ensure the runner ends at the exit
        assertEquals(exit[0], runner.getPositionY(), "Runner Y position should match exit for maze: " + mazeFile);
        assertEquals(exit[1], runner.getPositionX(), "Runner X position should match exit for maze: " + mazeFile);
    }
}

}


