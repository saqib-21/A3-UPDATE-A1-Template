package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;

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

            
            String inputFile = cmd.getOptionValue("i");
            logger.info("** Starting Maze Runner");
            logger.info("**** Reading the maze from file " + inputFile);

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line;
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        logger.debug("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        logger.debug("PASS ");
                    }
                }
                logger.debug(System.lineSeparator());
            }
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