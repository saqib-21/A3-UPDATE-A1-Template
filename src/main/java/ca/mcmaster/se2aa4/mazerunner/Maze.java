package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Maze {
    private char[][] grid;

    // Constructor to initialize the maze grid
    public Maze(String filePath) {
        loadMaze(filePath);
    }

    public char[][] getGrid() {
        return grid;
    }

    // Private method to load the maze from a file
    private void loadMaze(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int rowCount = 0;

            // First, count the number of rows and columns in the file
            while ((line = br.readLine()) != null) {
                rowCount++;
            }

            // Reset the reader to the start of the file
            br.close();

            // Reinitialize reader and create the grid array
            BufferedReader brAgain = new BufferedReader(new FileReader(filePath));
            grid = new char[rowCount][];
            int row = 0;

            while ((line = brAgain.readLine()) != null) {
                grid[row] = line.toCharArray();
                row++;
            }
            brAgain.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load the maze from file: " + filePath, e);
        }
    }

    // Method to print the maze grid
    public void printMaze() {
        for (char[] row : grid) {
            for (char cell : row) {
                System.out.print(cell);
            }
            System.out.println(); // Move to the next line after printing a row
        }
    }
}
