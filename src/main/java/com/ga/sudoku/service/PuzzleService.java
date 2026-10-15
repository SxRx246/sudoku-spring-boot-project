package com.ga.sudoku.service;

import com.ga.sudoku.Sudoku;
import com.ga.sudoku.SudokuCell;
import com.ga.sudoku.SudokuSolver;
import com.ga.sudoku.dto.request.PuzzleRequest;
import com.ga.sudoku.exception.InformationNotFoundException;
import com.ga.sudoku.model.Puzzle;
import com.ga.sudoku.repository.PuzzleRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PuzzleService {

    private final PuzzleRepository puzzleRepository;

    private static final Logger logger =
            LoggerFactory.getLogger(PuzzleService.class);

    public Puzzle createPuzzle(PuzzleRequest request) {
        logger.info("Creating Sudoku puzzle with level: {}",
                request.getLevel());

        String initialBoard = request.getInitialBoard();

        if (initialBoard == null || initialBoard.length() != 81) {
            throw new IllegalArgumentException(
                    "Initial board must contain exactly 81 characters");
        }

        String solution = request.getSolution();

        if (solution == null || solution.length() != 81) {
            throw new IllegalArgumentException(
                    "Solution must contain exactly 81 characters");
        }

        if (!initialBoard.matches("[0-9]{81}")) {
            throw new IllegalArgumentException(
                    "Initial board must contain only digits from 0 to 9");
        }

        if (!solution.matches("[1-9]{81}")) {
            throw new IllegalArgumentException(
                    "Solution must contain only digits from 1 to 9");
        }

        if (request.getLevel() == null) {
            throw new IllegalArgumentException(
                    "Puzzle level is required");
        }

        Puzzle puzzle = new Puzzle();
        puzzle.setLevel(request.getLevel());
        puzzle.setInitialBoard(initialBoard);
        puzzle.setSolution(solution);
        puzzle.setHint(request.getHint());

        Puzzle savedPuzzle = puzzleRepository.save(puzzle);

        logger.info("Successfully created Sudoku puzzle with ID: {}",
                savedPuzzle.getId());

        return savedPuzzle;
    }


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
