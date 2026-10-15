
package com.ga.sudoku;

import com.ga.sudoku.exception.InvalidCharacterException;
import com.ga.sudoku.exception.FileNotFoundException;

import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();

        String puzzleFilePath = "/puzzle/puzzle2.txt";
        String solution = "src/main/resources/puzzle.solution/" + sudoku.getSolutionName("puzzle2.txt");

        try {

            InputStream puzzle = sudoku.getPuzzleFile(puzzleFilePath);

            sudoku.readPuzzle(puzzle);

            System.out.println("\t\tOriginal Puzzle");
            sudoku.displayBoard();

            SudokuCell[][] board = sudoku.getBoard();

            SudokuSolver solver = new SudokuSolver(board);

            if (solver.solveSudoku()) {
                System.out.println("\n\t\tSolved Puzzle");
                sudoku.displayBoard();
                sudoku.saveSolution(solution);
            } else {
                System.out.println("No solution exists.");
            }

        } catch (FileNotFoundException
                | InvalidCharacterException
                | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
