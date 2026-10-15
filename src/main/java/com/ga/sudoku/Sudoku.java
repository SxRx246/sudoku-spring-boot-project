package com.ga.sudoku;

import com.ga.sudoku.exception.InvalidCharacterException;
import com.ga.sudoku.exception.FileNotFoundException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Sudoku {

    private final SudokuCell[][] board = new SudokuCell[9][9];

    public SudokuCell[][] getBoard() {
        return board;
    }

    public InputStream getPuzzleFile(String filePath) throws FileNotFoundException {
        InputStream puzzle1Stream = Sudoku.class.getResourceAsStream(filePath);

        if (puzzle1Stream == null) {
            throw new FileNotFoundException("Puzzle file not found: " + filePath);
        } else {
            return puzzle1Stream;
        }
    }

    public void readPuzzle(InputStream puzzleStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(puzzleStream))) {

            String line;
            int row = 0;

            while ((line = reader.readLine()) != null) {

                if (row >= 9) {
                    throw new InvalidCharacterException(
                            "Invalid puzzle: more than 9 rows.");
                }

                String[] numbers = line.trim().split("\\s+");

                if (numbers.length != 9) {
                    throw new InvalidCharacterException(
                            "Invalid number of values in row " + (row + 1));
                }

                for (int column = 0; column < numbers.length; column++) {

                    int number;

                    try {
                        number = Integer.parseInt(numbers[column]);
                    } catch (NumberFormatException e) {
                        throw new InvalidCharacterException(
                                "Invalid character in puzzle: " + numbers[column]
                        );
                    }

                    if (number >= 0 && number <= 9) {
                        board[row][column] = new SudokuCell(number);
                    } else {
                        throw new InvalidCharacterException(
                                "Invalid Character: " + number);
                    }
                }

                row++;

            }
        } catch (
                IOException e) {
            System.out.println("Error reading puzzle1.txt: " + e.getMessage());
        }
    }

    public void displayBoard() {
        SudokuCell[][] board = getBoard();

        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (column > 0) {
                    System.out.print("  ");
                }

                if (column == 3 || column == 6) {
                    System.out.print("|  ");
                }

                System.out.print(board[row][column].getValue());
            }

            System.out.println();

            if ((row + 1) % 3 == 0 && row < 8) {
                System.out.println("----------------------------------");
            }
        }

    }

    public String getSolutionName(String puzzleName) {
        return puzzleName.replace(".txt", ".solution.txt");
    }

    public void saveSolution(String fileName) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(fileName))) {
            for (int row = 0; row < 9; row++) {
                for (int column = 0; column < 9; column++) {
                    writer.write(String.valueOf(board[row][column].getValue()));

                    if (column < 8) {
                        writer.write(" ");
                    }
                }
                writer.newLine();
            }
        }

        System.out.println("\nSolution saved: " + fileName);
    }
}
