package ca.mcmaster.se2aa4.mazerunner;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MazeRunnerNavigationTest {

    private Maze maze;

    @BeforeEach
    public void setUp() {
        // Initialize shared objects before each test
        maze = new Maze("examples" + File.separator + "tiny.maz.txt");
    }

    @Test
    public void testMoveEastForwardSuccess() {
        int[] entrance = maze.findEntrance();
        MazeRunner runner = new MazeRunner(entrance[0], entrance[1], 'E');
        boolean moved = runner.moveForward(maze.getGrid());
        assertTrue(moved);
        assertEquals(entrance[0], runner.getPositionY());
        assertEquals(entrance[1] + 1, runner.getPositionX());
    }

    @Test
    public void testMoveBlockedByWall() {
        int[] entrance = maze.findEntrance();
        MazeRunner runner = new MazeRunner(entrance[0], entrance[1], 'S'); // wall to the right
        boolean moved = runner.moveForward(maze.getGrid());
        assertFalse(moved);
        assertEquals(entrance[0], runner.getPositionY());
        assertEquals(entrance[1], runner.getPositionX());
    }

    @Test
    public void testTurnLeftAndRight() {
        MazeRunner runner = new MazeRunner(5, 0, 'E');
        runner.turnLeft(); // Should become 'N'
        assertEquals('N', runner.getDirection());
        runner.turnLeft(); // Should become 'W'
        assertEquals('W', runner.getDirection());
        runner.turnRight(); // Should become 'N'
        assertEquals('N', runner.getDirection());
        runner.turnRight(); // Should become 'E'
        assertEquals('E', runner.getDirection());
        runner.turnRight(); // Should become 'S'
        assertEquals('S', runner.getDirection());
    }

    @Test
    public void testOutOfBoundsMovement() {
        MazeRunner runner = new MazeRunner(5, 0, 'W'); // Outside map
        boolean moved = runner.moveForward(maze.getGrid());
        assertFalse(moved);
        assertEquals(5, runner.getPositionY());
        assertEquals(0, runner.getPositionX());
    }
}