package com.ga.sudoku.service;

import com.ga.sudoku.Sudoku;
import com.ga.sudoku.SudokuCell;
import com.ga.sudoku.SudokuSolver;
import com.ga.sudoku.exception.InformationNotFoundException;
import com.ga.sudoku.model.Puzzle;
import com.ga.sudoku.repository.PuzzleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PuzzleService {

    private final PuzzleRepository puzzleRepository;

    public Puzzle getPuzzle(Long id){
        return puzzleRepository.findById(id)
                .orElseThrow(() -> new InformationNotFoundException(
                "Puzzle with id "+ id + " not found"
        ));
    }

    public String solvePuzzle(Long id){
        Puzzle puzzle = puzzleRepository.findById(id)
                .orElseThrow(() -> new InformationNotFoundException(
                        "Puzzle with id "+ id + " not found"
                ));

        String initialBoard = puzzle.getInitialBoard();

        if (initialBoard == null || initialBoard.length() != 81) {
            throw new IllegalArgumentException(
                    "Puzzle must contain exactly 81 characters");
        }

        SudokuCell[][] board = new SudokuCell[9][9];

        for (int row=0; row<9; row++){
            for (int column =0; column<9; column++){
                int index = row * 9 + column;
                char character = initialBoard.charAt(index);

                if (character < '0' || character > '9') {
                    throw new IllegalArgumentException(
                            "Invalid character in puzzle: " + character);
                }

                int number = Character.getNumericValue(character);

                board[row][column] = new SudokuCell(number);
            }
        }

        SudokuSolver solver = new SudokuSolver(board);

        if (!solver.solveSudoku()) {
            throw new IllegalStateException("No solution exists");
        }

        StringBuilder solvedBoard = new StringBuilder();

        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                solvedBoard.append(board[row][column].getValue());
            }
        }

        return solvedBoard.toString();

    }

//            puzzle.getInitialBoard().length()
}
