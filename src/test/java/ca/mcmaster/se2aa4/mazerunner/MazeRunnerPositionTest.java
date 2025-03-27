package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class MazeRunnerPositionTest {
    @Test
    public void testMazeLoading() {
        String filePath = "examples\\tiny.maz.txt";
        Maze maze = new Maze(filePath);
        assertNotNull(maze);
        assertNotNull(maze.getGrid());
    }

    @Test
    public void testMazeEntrance() {
        String filePath = "examples\\tiny.maz.txt";
        Maze maze = new Maze(filePath);
        int[] entrance = maze.findEntrance();
        assertNotNull(entrance);
        assertEquals(2, entrance.length);
        assertTrue(entrance[0] >= 0 && entrance[1] == 0); // Valid entrance coordinates
    }

    @Test
    public void testMazeExit() {
        String filePath = "examples\\tiny.maz.txt";
        Maze maze = new Maze(filePath);
        int[] exit = maze.findExit();
        assertNotNull(exit);
        assertEquals(2, exit.length);
        assertTrue(exit[0] >= 0 && exit[1] == ((maze.getGrid().length)-1)); // Valid exit coordinates
    }

    @Test
    public void testRunnerStartsAtEntrance() {
        String filePath = "examples\\tiny.maz.txt";
        Maze maze = new Maze(filePath);
        int[] entrance = maze.findEntrance();
        MazeRunner runner = new MazeRunner(entrance[0], entrance[1], 'E');
        assertEquals(entrance[0], runner.getPositionY());
        assertEquals(entrance[1], runner.getPositionX());
    }



    @Test
    public void testMoveForwardSuccess() {

        String filePath = "examples\\tiny.maz.txt";
        Maze maze = new Maze(filePath);
        int[] entrance = maze.findEntrance();

        MazeRunner runner = new MazeRunner(entrance[0], entrance[1], 'E');
        boolean moved = runner.moveForward(maze.getGrid());
        assertTrue(moved);
        assertEquals(entrance[0], runner.getPositionY());
        assertEquals(entrance[1]+1, runner.getPositionX());
    }

    @Test
    public void testMoveBlockedByWall() {
        String filePath = "examples\\tiny.maz.txt";
        Maze maze = new Maze(filePath);
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


    // might change this test
    @Test
    public void testOutOfBoundsMovement() {
        String filePath = "examples\\tiny.maz.txt";
        Maze maze = new Maze(filePath);
        MazeRunner runner = new MazeRunner(5, 0, 'W'); // Outside map

        boolean moved = runner.moveForward(maze.getGrid());

        assertFalse(moved);
        assertEquals(5, runner.getPositionY());
        assertEquals(0, runner.getPositionX());
    }

}
