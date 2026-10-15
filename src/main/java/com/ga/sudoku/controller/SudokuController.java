
package com.ga.sudoku.controller;

import com.ga.sudoku.Sudoku;
import com.ga.sudoku.SudokuCell;
import com.ga.sudoku.SudokuSolver;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/sudoku")
public class SudokuController {

    @PostMapping("/solve")
    public ResponseEntity<?> solvePuzzle(
            @RequestParam("file") MultipartFile file)
            throws IOException {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Please upload a puzzle file.");
        }

        Sudoku sudoku = new Sudoku();

        sudoku.readPuzzle(file.getInputStream());

        SudokuCell[][] board = sudoku.getBoard();
        SudokuSolver solver = new SudokuSolver(board);

        if (!solver.solveSudoku()) {
            return ResponseEntity.badRequest()
                    .body("This puzzle has no solution.");
        }

        Path outputDirectory = Path.of("solved-puzzles");
        Files.createDirectories(outputDirectory);

        Path outputFile = Files.createTempFile(
                outputDirectory, "solved-", ".txt");

        sudoku.saveSolution(outputFile.toString());

        Resource resource = new FileSystemResource(outputFile);

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"solved-puzzle.txt\"")
                .body(resource);
    }
}
