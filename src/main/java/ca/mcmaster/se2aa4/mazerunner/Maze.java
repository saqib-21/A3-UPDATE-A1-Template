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
            int maxCols = 0;

            // First pass: determine the number of rows and the maximum number of columns
            while ((line = br.readLine()) != null) {
                rowCount++;
                maxCols = Math.max(maxCols, line.length());//to make each row have the same length 
            }

            // Reset the reader to the start of the file
            br.close();

            // Second pass: initialize the grid and fill it
            BufferedReader brAgain = new BufferedReader(new FileReader(filePath));
            grid = new char[rowCount][maxCols]; // Fixed column width for consistency
            int row = 0;

            while ((line = brAgain.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    // Blank lines are filled with spaces
                    for (int col = 0; col < maxCols; col++) {
                        grid[row][col] = ' ';
                    }
                } else {
                    // Non-blank lines are converted to char arrays
                    char[] chars = line.toCharArray();
                    for (int col = 0; col < maxCols; col++) {
                        // Fill remaining columns with spaces if the line is shorter
                        grid[row][col] = (col < chars.length) ? chars[col] : ' ';
                    }
                }
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

    //method to get entrance of the maze
    public int[] findEntrance() {
        for (int i = 0; i < grid.length; i++) { // Loop through all rows
            if (grid[i][0] == ' ') { // Check the first column of each row
                return new int[] { i, 0 }; // Return the row index and column index (always 0 for entrance)
            }
        }
        return null; // Return null if no entrance is found
    }

    //method to get exit of the maze
    public int[] findExit() {
        for (int i = 0; i < grid.length; i++) { // Loop through all rows
            if (grid[i][grid[i].length - 1] == ' ') { // Check the last column of each row
                return new int[] { i, grid[i].length - 1 }; // Return the row index and column index of the exit
            }
        }
        return null; // Return null if no exit is found
    }
    
}
