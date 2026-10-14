
package com.ga.sudoku;

import com.ga.sudoku.exception.InvalidCharacterException;
import com.ga.sudoku.exception.SudokuFileNotFoundException;

import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) {
        String puzzleFilePath = "/puzzle/puzzle2.txt";
        String solution = "src/main/resources/puzzle.solution/" + Sudoku.getSolutionName("puzzle2.txt");

        try {
            InputStream puzzle = Sudoku.getPuzzleFile(puzzleFilePath);

            Sudoku.readPuzzle(puzzle);

            System.out.println("\t\tOriginal Puzzle");
            Sudoku.displayBoard();

            SudokuSolver solver = new SudokuSolver();

            if (solver.solveSudoku()) {
                System.out.println("\n\t\tSolved Puzzle");
                Sudoku.displayBoard();
                Sudoku.saveSolution(solution);
            } else {
                System.out.println("No solution exists.");
            }

        } catch (SudokuFileNotFoundException
                | InvalidCharacterException
                | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
