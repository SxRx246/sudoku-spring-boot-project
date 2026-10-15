
package com.ga.sudoku.controller;

import com.ga.sudoku.Sudoku;
import com.ga.sudoku.SudokuCell;
import com.ga.sudoku.SudokuSolver;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/sudoku")
public class SudokuController {

    private Path outputFile;

    @PostMapping("/solve")
    public ResponseEntity<?> solvePuzzle(
            @RequestParam("file") MultipartFile file)
            throws IOException {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Please upload a puzzle file.");
        }

        String fileName = Path.of(file.getOriginalFilename())
                .getFileName().toString();

        String solutionFileName = new Sudoku().getSolutionName(fileName);

        outputFile = Path.of(
                "src/main/resources/puzzle.solution",
                solutionFileName
        );

        Sudoku sudoku = new Sudoku();
        sudoku.readPuzzle(file.getInputStream());

        SudokuCell[][] board = sudoku.getBoard();
        SudokuSolver solver = new SudokuSolver(board);

        if (!solver.solveSudoku()) {
            return ResponseEntity.badRequest()
                    .body("This puzzle has no solution.");
        }

        Files.createDirectories(outputFile.getParent());
        sudoku.saveSolution(outputFile.toString());

        StringBuilder solution = new StringBuilder();

        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                solution.append(board[row][column].getValue());

                if (column < 8) {
                    solution.append(" ");
                }
            }

            solution.append(System.lineSeparator());
        }

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(solution.toString());
    }

    @GetMapping("/solution")
    public ResponseEntity<String> getSolution() throws IOException {

        if (outputFile == null || !Files.exists(outputFile)) {
            throw new FileNotFoundException(
                    "No solution file found. Solve a puzzle first."
            );
        }

        return ResponseEntity.ok(
                "Solution file location: "
                        + outputFile.toAbsolutePath()
        );
    }
}
