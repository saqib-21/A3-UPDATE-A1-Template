package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("i", true, "input file");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
            if (!cmd.hasOption("i")) {
                logger.error("Input file not provided. Use -i option to specify the input file.");
                return;
            }

            System.out.println("Logger test");

            String inputFile = cmd.getOptionValue("i");
            logger.info("** Starting Maze Runner");
            logger.info("**** Reading the maze from file " + inputFile);
            
            // Use the Maze class to load and print the maze
            Maze maze = new Maze(inputFile);
            logger.info("**** Maze Loaded Successfully:");
            maze.printMaze();

            // Find and log the entrance
            int[] entrance = maze.findEntrance();
            if (entrance != null) {
                logger.info("Maze entrance found at index  [" + entrance[0] + "," + entrance[1]+ "]");
            } else {
                logger.warn("No entrance found in the maze.");
            }

            // Find and log the exit
            int[] exit = maze.findExit();
            if (exit != null) {
                logger.info("Maze exit found at index [" + exit[0] + "," + exit[1] + "]");
            } else {
                logger.warn("No exit found in the maze.");
            }

            // Create a MazeRunner object at the entrance 
            MazeRunner runner = new MazeRunner(entrance[0], entrance[1], 'E');
            logger.info("**** Maze Runner Created at entrance [" + entrance[0] + "," + entrance[1] + "] facing East");
            //runner.moveForward(maze.getGrid());
            //logger.info("Current State: " + runner.getState());
            //logger.info("**** Maze Runner Moved Forward");
            //runner.moveForward(maze.getGrid());
            //logger.info("**** Maze Runner Moved Forward");
            //logger.info("Current State: " + runner.getState());

            // Create a MazeSolver object and solve the maze
            MazeSolver solver = new MazeSolver(maze, runner);
            solver.solveMaze();
            logger.info("** End of Maze Runner");

        } catch (ParseException e) {
            logger.error("Failed to parse command line arguments", e);
        } catch (Exception e) {
            logger.error("/!\\ An error has occurred /!\\", e);
        }

        logger.info("**** Computing path");
        logger.warn("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
