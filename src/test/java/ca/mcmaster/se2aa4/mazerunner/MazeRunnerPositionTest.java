package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class MazeRunnerPositionTest {


    @Test
    public void testInitialPositionAndDirection() {
        MazeRunner runner = new MazeRunner(1, 1, 'N');
        assertEquals(1, runner.getPositionY());
        assertEquals(1, runner.getPositionX());
        assertEquals('N', runner.getDirection());
    }

    @Test
    public void testMoveForwardSuccess() {

        String filePath = "examples\\tiny.maz.txt";
        Maze maze = new Maze(filePath);


        MazeRunner runner = new MazeRunner(5, 0, 'E');
        boolean moved = runner.moveForward(maze.getGrid());
        assertTrue(moved);
        assertEquals(5, runner.getPositionY());
        assertEquals(1, runner.getPositionX());
    }

        @Test
    public void testMoveBlockedByWall() {
        String filePath = "examples\\tiny.maz.txt";
        Maze maze = new Maze(filePath);
        MazeRunner runner = new MazeRunner(5, 0, 'S'); // wall to the right

        boolean moved = runner.moveForward(maze.getGrid());

        assertFalse(moved);
        assertEquals(5, runner.getPositionY());
        assertEquals(0, runner.getPositionX());
    }

    
    @Test
    public void testTurnLeft() {
        MazeRunner runner = new MazeRunner(5, 0, 'E');
        runner.turnLeft(); // Should become 'W'
        assertEquals('N', runner.getDirection());

        runner.turnLeft(); // Should become 'S'
        assertEquals('W', runner.getDirection());
    }

    @Test
    public void testTurnRight() {
        MazeRunner runner = new MazeRunner(5, 0, 'N');
        runner.turnRight(); // Should become 'E'
        assertEquals('E', runner.getDirection());

        runner.turnRight(); // Should become 'S'
        assertEquals('S', runner.getDirection());
    }


    @Test
    public void testOutOfBoundsMovement() {
        String filePath = "examples\\tiny.maz.txt";
        Maze maze = new Maze(filePath);
        MazeRunner runner = new MazeRunner(5, 0, 'W'); // Outside top border

        boolean moved = runner.moveForward(maze.getGrid());

        assertFalse(moved);
        assertEquals(5, runner.getPositionY());
        assertEquals(0, runner.getPositionX());
    }

}
