package ca.mcmaster.se2aa4.mazerunner;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MazeStarterTest {

    private String[] mazeFiles;

    @BeforeEach
    public void setUp() {
        // Load all maze files from the examples directory
        File examplesDir = new File("examples");
        mazeFiles = examplesDir.list((dir, name) -> name.endsWith(".maz.txt"));
    }

    @Test
    public void testMazeLoadingForAllMazes() {
        for (String mazeFile : mazeFiles) {
            Maze maze = new Maze("examples\\" + mazeFile);
            assertNotNull(maze, "Maze should not be null for file: " + mazeFile);
            assertNotNull(maze.getGrid(), "Maze grid should not be null for file: " + mazeFile);
        }
    }

    @Test
    public void testMazeEntranceForAllMazes() {
        for (String mazeFile : mazeFiles) {
            Maze maze = new Maze("examples\\" + mazeFile);
            int[] entrance = maze.findEntrance();
            assertNotNull(entrance, "Entrance should not be null for file: " + mazeFile);
            assertEquals(2, entrance.length, "Entrance should have 2 coordinates for file: " + mazeFile);
            assertTrue(entrance[0] >= 0 && entrance[1] == 0, "Entrance coordinates should be valid for file: " + mazeFile);
            // entrance[0] >= 0 since entrance can be any row, and entrance[1] == 0 since entrance is always at the first column.
        }
    }

    @Test
    public void testMazeExitForAllMazes() {
        for (String mazeFile : mazeFiles) {
            Maze maze = new Maze("examples\\" + mazeFile);
            int[] exit = maze.findExit();
            assertNotNull(exit, "Exit should not be null for file: " + mazeFile);
            assertEquals(2, exit.length, "Exit should have 2 coordinates for file: " + mazeFile);
            assertTrue(exit[0] >= 0 && exit[1] == (maze.getGrid()[0].length - 1), "Exit coordinates should be valid for file: " + mazeFile);
            //exit[0] >= 0  since exit can be any row, and exit[1] == (maze.getGrid()[0].length - 1) since exit is always at the last column, and 0 index of the first row is always the top border)
        }
    }

    @Test
    public void testRunnerStartsAtEntranceForAllMazes() {
        for (String mazeFile : mazeFiles) {
            Maze maze = new Maze("examples\\" + mazeFile);
            int[] entrance = maze.findEntrance();
            MazeRunner runner = new MazeRunner(entrance[0], entrance[1], 'E');
            assertEquals(entrance[0], runner.getPositionY(), "Runner Y position should match entrance for file: " + mazeFile);
            assertEquals(entrance[1], runner.getPositionX(), "Runner X position should match entrance for file: " + mazeFile);
        }
    }
}

