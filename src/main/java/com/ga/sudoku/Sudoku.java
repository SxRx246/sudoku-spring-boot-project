package com.ga.sudoku;

import com.ga.sudoku.exception.InvalidCharacterException;
import com.ga.sudoku.exception.SudokuFileNotFoundException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Sudoku {

    private static SudokuCell[][] board = new SudokuCell[9][9];

    public static InputStream getPuzzle(String filePath) throws SudokuFileNotFoundException {
        InputStream puzzle1Stream = Sudoku.class.getResourceAsStream(filePath);

        if (puzzle1Stream == null) {
            throw new SudokuFileNotFoundException("Puzzle file not found: " + filePath);
        } else {
            return puzzle1Stream;
        }
    }

    public static void displayPuzzle(InputStream puzzle1Stream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(puzzle1Stream))) {

            System.out.println("\t\t\tPuzzle 1");

            String line;
            int row = 0;

            while ((line = reader.readLine()) != null) {
                String[] numbers = line.trim().split("\\s+");

                for (int column = 0; column < numbers.length; column++) {

                    if (column > 0) {
                        System.out.print("  ");
                    }

                    if (column == 3 || column == 6) {
                        System.out.print("|  ");
                    }

                    int number = Integer.parseInt(numbers[column]);

                    if (number >= 0 && number <= 9) {
                        System.out.print(board[row][column].getValue());
                    } else {
                        throw new InvalidCharacterException(
                                "Invalid Character: " + numbers[column]);
                    }

                    board[row][column] = new SudokuCell(number);

                }

                System.out.println();
                row++;

                if (row % 3 == 0 && row < 9) {
                    System.out.println("----------------------------------");
                }
            }
        } catch (
                IOException e) {
            System.out.println("Error reading puzzle1.txt: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String puzzle1FilePath = "/puzzle/puzzle1.txt";
        try {
            InputStream puzzle = getPuzzle(puzzle1FilePath);
            displayPuzzle(puzzle);
        } catch (SudokuFileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InvalidCharacterException e) {
            System.out.println(e.getMessage());
        }

    }
}
